package cn.edu.zucc.echo.service.impl;

import cn.edu.zucc.echo.entity.*;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.*;
import cn.edu.zucc.echo.repository.*;
import cn.edu.zucc.echo.service.FeedbackService;
import cn.edu.zucc.echo.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private TpModelEntityRepository ModelEntityRepository;

    @Autowired
    private EchoQuestionireEntityRepository echoQuestionireEntityRepository;
    @Autowired
    private EchoQuestionEntityRepository echoQuestionEntityRepository;
    @Autowired
    private BasicClassEntityRepository classEntityRepository;
    @Autowired
    private BasicUserEntityRepository userEntityRepository;
    @Autowired
    private EchoAnswerSheetEntityRepository answerSheetEntityRepository;
    @Override
    public EchoQuestionireDto publishFeedback(QuestionireSeedDto dto) throws EchoServiceException {
        Integer questioniresid = copyQuestionireFromModel(dto);
        return queryQuestionireDetail(questioniresid);
    }

    @Override
    public EchoQuestionireDto queryQuestionireDetail(Integer questioniresid) throws EchoServiceException {
        EchoQuestionireEntity questionire = this.echoQuestionireEntityRepository.getOne(questioniresid);
        if (questionire == null) {
            throw new EchoServiceException("没有找到反馈卷:" + questioniresid);
        }

        EchoQuestionireDto EchoQuestionireDto = new EchoQuestionireDto(
                questionire.get_class().getsid(), questionire.getName(),questionire.getStatus(), questionire.getMemo(),
                questionire.getPublishTime(),
                questionire.getDeadLine(),
                questionire.getEchoQuestions().stream().map(
                        EchoQuestionEntity -> {
                            return new EchoQuestionDto(
                                    EchoQuestionEntity.getId(), EchoQuestionEntity.getCategory(),
                                    EchoQuestionEntity.getTitle(), EchoQuestionEntity.getIsRequired(),
                                    EchoQuestionEntity.getRemark(),
                                    EchoQuestionEntity.getEchoQuestionOptions().stream()
                                            .sorted(Comparator.comparing(EchoQuestionOptionEntity::getId))
                                            .map(
                                            EchoQuestionOptionEntity -> {
                                                return new EchoQuestionOptionDto(EchoQuestionOptionEntity.getId(), EchoQuestionOptionEntity.getTitle());
                                            }).collect(Collectors.toList()));
                        }
                ).collect(Collectors.toList())
        );

        return EchoQuestionireDto;
    }

    public Integer answerWorkSheet(EchoAnswerSheetDto echoAnswerSheetDto){
        /**
         * 1.获取答卷对应的问卷
         * 2.检查必填项是否已经填写
         * 3.填充缺失的项（比如问题title等）
         * 4.构造方便用于显示的answerContentView字段
         * 5.构造AnswerSheet对应的Entity然后入库
         * 6.返回入库后对应的AnswerSheet的sid
         */
        EchoQuestionireEntity questionire = this.echoQuestionireEntityRepository.getOne(echoAnswerSheetDto.getQuestionireId());
        if (questionire == null) {
            throw new EchoServiceException("没有找到问卷:" + echoAnswerSheetDto.getQuestionireId());
        }

        echoAnswerSheetDto.getAnswerDetail().stream().forEach(
                answer -> {
                    if(answer.getAnswerContent() == null)
                        throw new EchoServiceException("没有回答id为"+ answer.getQuestionId() + "的问题");
                }
        );

//        BeanUtils.copyProperties(echoAnswerSheetDto,questionire);
        EchoAnswerSheetEntity echoAnswerSheetEntity = new EchoAnswerSheetEntity();
        BeanUtils.copyProperties(echoAnswerSheetDto,echoAnswerSheetEntity);

        for (EchoAnswerSheetDetailDto answerSheetDetailDto : echoAnswerSheetDto.getAnswerDetail()) {
            EchoAnswerSheetDetailEntity answerDetailEntity = new EchoAnswerSheetDetailEntity();
            BeanUtils.copyProperties(answerSheetDetailDto, answerDetailEntity);
            answerSheetDetailDto.setAnswerContentView(answerDetailEntity.getQuestionTitle()+answerDetailEntity.getAnswerContent());
            echoAnswerSheetEntity.getEchoAnswerSheetDetail().add(answerDetailEntity);

        }
        this.answerSheetEntityRepository.save(echoAnswerSheetEntity);
        return echoAnswerSheetEntity.getsid();
//        for (TpQuestionOptionDto questionOptionDto : questiondto.getOptions()) {
//            TpQuestionOptionEntity questionOptionEntity = new TpQuestionOptionEntity();
//            BeanUtils.copyProperties(questionOptionDto, questionOptionEntity);
//            questionEntity.getTpQuestionOptions().add(questionOptionEntity);
//        }
//        this.QuestionEntityRepository.save(questionEntity);
//        return questionEntity.getId();

    }

    private Integer copyQuestionireFromModel(QuestionireSeedDto dto) {
        TpModelEntity ModelEntity = this.ModelEntityRepository.getOne(dto.getModelid());
        if (ModelEntity == null) {
            throw new EchoServiceException("没有找到模板:" + dto.getModelid());
        }

        BasicClassEntity classEntity = this.classEntityRepository.getOne(dto.getClassid());
        if (classEntity == null) {
            throw new EchoServiceException("没有找到班级:" + dto.getClassid());
        }

        BasicUserEntity teacherEntity = this.userEntityRepository.getOne(dto.getTeacherid());
        if (teacherEntity == null) {
            throw new EchoServiceException("没有找到教师:" + dto.getTeacherid());
        }

        EchoQuestionireEntity questionireEntity = new EchoQuestionireEntity();
        questionireEntity.setName(ModelEntity.getName());
        questionireEntity.setMemo(ModelEntity.getMemo());
        questionireEntity.set_class(classEntity);
        questionireEntity.setStatus(Constants.Questionire_STATUS_PUBLISHED);
        questionireEntity.setCreatorId(dto.getTeacherid());
        questionireEntity.setCreateTime(Instant.now());
        questionireEntity.setPublishTime(Instant.now());
        questionireEntity.setDeadLine(dto.getDeadLine());

        questionireEntity.setEchoQuestions(
                ModelEntity.getTpQuestions().stream().map(QuestionEntity -> {
                    EchoQuestionEntity question = new EchoQuestionEntity();
                    question.setQuestionire(questionireEntity);
                    question.setCategory(QuestionEntity.getCategory());
                    question.setRemark(QuestionEntity.getRemark());
                    question.setTitle(QuestionEntity.getTitle());
                    question.setIsRequired(QuestionEntity.getIsRequired());
                    question.setEchoQuestionOptions(
                            QuestionEntity.getTpQuestionOptions().stream()
                                    .sorted(Comparator.comparing(TpQuestionOptionEntity::getId))
                                    .map(tpQuestionOptionEntity -> {
                                        EchoQuestionOptionEntity EchoQuestionOptionEntity = new EchoQuestionOptionEntity();
                                        EchoQuestionOptionEntity.setQuestion(question);
                                        EchoQuestionOptionEntity.setTitle(tpQuestionOptionEntity.getTitle());
                                        return EchoQuestionOptionEntity;
                                    }).collect(Collectors.toSet())
                    );
                    return question;
                }).collect(Collectors.toSet())
        );
        this.echoQuestionireEntityRepository.save(questionireEntity);

        return questionireEntity.getId();
    }

}

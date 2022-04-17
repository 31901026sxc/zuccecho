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
    private EchoQuestionnaireEntityRepository echoquestionnaireEntityRepository;
    @Autowired
    private EchoQuestionEntityRepository echoQuestionEntityRepository;
    @Autowired
    private BasicClassEntityRepository classEntityRepository;
    @Autowired
    private BasicUserEntityRepository userEntityRepository;
    @Autowired
    private EchoAnswerSheetEntityRepository answerSheetEntityRepository;
    @Override
    public EchoQuestionnaireDto publishFeedback(QuestionnaireSeedDto dto) throws EchoServiceException {
        Integer QuestionnaireSid = copyquestionnaireFromModel(dto);
        return queryQuestionnaireDetail(QuestionnaireSid);
    }

    @Override
    public EchoQuestionnaireDto queryQuestionnaireDetail(Integer questionnairesid) throws EchoServiceException {
        EchoQuestionnaireEntity questionnaire = this.echoquestionnaireEntityRepository.getOne(questionnairesid);
        if (questionnaire == null) {
            throw new EchoServiceException("没有找到反馈卷:" + questionnairesid);
        }

        EchoQuestionnaireDto EchoQuestionnaireDto = new EchoQuestionnaireDto(
                questionnaire.get_class().getSid(), questionnaire.getName(),questionnaire.getStatus(), questionnaire.getMemo(),
                questionnaire.getPublishTime(),
                questionnaire.getDeadLine(),
                questionnaire.getEchoQuestions().stream().map(
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

        return EchoQuestionnaireDto;
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
        EchoQuestionnaireEntity questionnaire = this.echoquestionnaireEntityRepository.getOne(echoAnswerSheetDto.getQuestionnaireId());
        if (questionnaire == null) {
            throw new EchoServiceException("没有找到问卷:" + echoAnswerSheetDto.getQuestionnaireId());
        }

        echoAnswerSheetDto.getAnswerDetail().stream().forEach(
                answer -> {
                    if(answer.getAnswerContent() == null)
                        throw new EchoServiceException("没有回答id为"+ answer.getQuestionId() + "的问题");
                }
        );

//        BeanUtils.copyProperties(echoAnswerSheetDto,questionnaire);
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

    private Integer copyquestionnaireFromModel(QuestionnaireSeedDto dto) {
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

        EchoQuestionnaireEntity questionnaireEntity = new EchoQuestionnaireEntity();
        questionnaireEntity.setName(ModelEntity.getName());
        questionnaireEntity.setMemo(ModelEntity.getMemo());
        questionnaireEntity.set_class(classEntity);
        questionnaireEntity.setStatus(Constants.Questionnaire_STATUS_PUBLISHED);
        questionnaireEntity.setCreatorId(dto.getTeacherid());
        questionnaireEntity.setCreateTime(Instant.now());
        questionnaireEntity.setPublishTime(Instant.now());
        questionnaireEntity.setDeadLine(dto.getDeadLine());

        questionnaireEntity.setEchoQuestions(
                ModelEntity.getTpQuestions().stream().map(QuestionEntity -> {
                    EchoQuestionEntity question = new EchoQuestionEntity();
                    question.setquestionnaire(questionnaireEntity);
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
        this.echoquestionnaireEntityRepository.save(questionnaireEntity);

        return questionnaireEntity.getId();
    }

}

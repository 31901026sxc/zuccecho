package cn.edu.zucc.echo.service.impl;

import cn.edu.zucc.echo.entity.TpQuestionEntity;
import cn.edu.zucc.echo.entity.TpQuestionOptionEntity;
import cn.edu.zucc.echo.entity.TpModelEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.TpQuestionDto;
import cn.edu.zucc.echo.form.TpQuestionOptionDto;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.repository.TpModelEntityRepository;
import cn.edu.zucc.echo.repository.TpQuestionEntityRepository;
import cn.edu.zucc.echo.repository.TpQuestionOptionEntityRepository;
import cn.edu.zucc.echo.service.ModelService;
import cn.edu.zucc.echo.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private TpModelEntityRepository ModelEntityRepository;
    @Autowired
    private TpQuestionEntityRepository QuestionEntityRepository;

    @Override
    public Integer createModel(TpModelDto modeldto) throws EchoServiceException {
        //检查
        if (modeldto.getId() != 0) {
            throw new EchoServiceException("新建模板不能上传id");
        }

        if (modeldto.getQuestions() != null) {
            modeldto.getQuestions().stream().forEach(
                    question -> {
                        if (!Constants.Q_CATEGORY_SINGLE_CHOICE.equals(question.getCategory())
                                && !Constants.Q_CATEGORY_MULTI_CHOICE.equals(question.getCategory())
                                && !Constants.Q_CATEGORY_SUBJECTIVE.equals(question.getCategory())
                        ) {
                            throw new EchoServiceException(question.getTitle() + "的类型不支持");
                        }
                        //提升可读性，分为两级判断
                        if (Constants.Q_CATEGORY_SINGLE_CHOICE.equals(question.getCategory())
                                || Constants.Q_CATEGORY_MULTI_CHOICE.equals(question.getCategory())) {
                            if (question.getOptions() == null || question.getOptions().size() == 0) {
                                throw new EchoServiceException(question.getTitle() + "的选项不能为空");
                            }
                        }
                    }
            );
        }

        //保存到数据库
        TpModelEntity ModelEntity = new TpModelEntity();
        BeanUtils.copyProperties(modeldto, ModelEntity);
        ModelEntity.setStatus(Constants.Model_STATUS_NORMAL);
        //处理题目
        for (TpQuestionDto questionDto : modeldto.getQuestions()) {
            TpQuestionEntity questionEntity = new TpQuestionEntity();
            BeanUtils.copyProperties(questionDto, questionEntity);
            questionEntity.setModel(ModelEntity);
            ModelEntity.getTpQuestions().add(questionEntity);
            for (TpQuestionOptionDto optionDto : questionDto.getOptions()) {
                TpQuestionOptionEntity optionEntity = new TpQuestionOptionEntity();
                BeanUtils.copyProperties(optionDto, optionEntity);
                optionEntity.setQuestion(questionEntity);
                questionEntity.getTpQuestionOptions().add(optionEntity);
            }
        }
        this.ModelEntityRepository.save(ModelEntity);
        return ModelEntity.getId();
    }

    @Override
    public TpModelDto queryModelDetail(Integer Modelsid) throws EchoServiceException {
        TpModelEntity ModelEntity = this.ModelEntityRepository.getOne(Modelsid);
        if (ModelEntity == null) {
            throw new EchoServiceException("模板不存在.");
        }

        TpModelDto retObj = new TpModelDto(ModelEntity.getId(), ModelEntity.getName(),
                ModelEntity.getStatus(), ModelEntity.getMemo(),
                ModelEntity.getTpQuestions().stream().map(
                        question -> new TpQuestionDto(question.getId(),question.getCategory(),
                                question.getTitle(), question.getIsRequired(), question.getRemark(),
                                question.getTpQuestionOptions().stream()
                                        .sorted(Comparator.comparing(TpQuestionOptionEntity::getId))
                                        .map(option -> new TpQuestionOptionDto(option.getId(),
                                                option.getTitle())).collect(Collectors.toList()))
                        ).collect(Collectors.toList()));

        return retObj;
    }

    @Override
    public String addAQuestion(TpQuestionDto questionDto) throws EchoServiceException {
        TpModelEntity ModelEntity = this.ModelEntityRepository.getOne(questionDto.getModelId());
        if (ModelEntity == null) {
            throw new EchoServiceException("模板不存在.");
        }
        TpQuestionEntity question = new TpQuestionEntity();
        BeanUtils.copyProperties(questionDto,question);
        question.setModel(ModelEntityRepository.getOne(questionDto.getModelId()));
        QuestionEntityRepository.save(question);
        return "success";
    }
}

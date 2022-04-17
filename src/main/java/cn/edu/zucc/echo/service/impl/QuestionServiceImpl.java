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
import cn.edu.zucc.echo.service.ModelService;
import cn.edu.zucc.echo.service.QuestionService;
import cn.edu.zucc.echo.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private TpQuestionEntityRepository QuestionEntityRepository;

    @Override
    public Integer createQuestion(TpQuestionDto questiondto) throws EchoServiceException {
        //检查
        if (questiondto.getId() != 0) {
            throw new EchoServiceException("新建问题不能上传id");
        }

        //保存到数据库
        TpQuestionEntity questionEntity = new TpQuestionEntity();
        BeanUtils.copyProperties(questiondto, questionEntity);

        //处理题目
        for (TpQuestionOptionDto questionOptionDto : questiondto.getOptions()) {
            TpQuestionOptionEntity questionOptionEntity = new TpQuestionOptionEntity();
            BeanUtils.copyProperties(questionOptionDto, questionOptionEntity);
            questionEntity.getTpQuestionOptions().add(questionOptionEntity);
        }

        this.QuestionEntityRepository.save(questionEntity);
        return questionEntity.getId();
    }

    @Override
    public TpQuestionDto queryQuestionDetail(Integer Questionid) throws EchoServiceException {
        TpQuestionEntity QuestionEntity = this.QuestionEntityRepository.getOne(Questionid);
        if (QuestionEntity == null) {
            throw new EchoServiceException("问题不存在.");
        }

        TpQuestionDto retObj = new TpQuestionDto(QuestionEntity.getId(), QuestionEntity.getCategory(),
                QuestionEntity.getTitle(),QuestionEntity.getIsRequired(),QuestionEntity.getRemark(),
                QuestionEntity.getTpQuestionOptions().stream().map(
                        option -> new TpQuestionOptionDto(option.getId(),option.getTitle())
                ).collect(Collectors.toList()));

        return retObj;
    }
}

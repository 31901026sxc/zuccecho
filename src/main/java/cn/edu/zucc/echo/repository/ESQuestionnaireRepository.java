package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.ESQuestionnaireEntity;
import cn.edu.zucc.echo.entity.EchoAnswerSheetEntity;
import cn.edu.zucc.echo.entity.EchoQuestionnaireEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


@Component
public interface ESQuestionnaireRepository extends ElasticsearchRepository<ESQuestionnaireEntity,Long> {
    EchoQuestionnaireEntity findById(long id);
    EchoQuestionnaireEntity findByName(String name);
}

package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.ESAnswerSheetEntity;
import cn.edu.zucc.echo.entity.EchoAnswerSheetEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ESAnswerSheetRepository extends ElasticsearchRepository<ESAnswerSheetEntity,Long> {
    EchoAnswerSheetEntity findById(long id);
    EchoAnswerSheetEntity findByStudent(String studentName);
    List<EchoAnswerSheetEntity> findBySidBetween(double sid1, double sid2);
}

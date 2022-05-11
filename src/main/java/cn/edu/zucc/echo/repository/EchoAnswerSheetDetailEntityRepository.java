package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.EchoAnswerSheetDetailEntity;
import cn.edu.zucc.echo.entity.EchoAnswerSheetEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EchoAnswerSheetDetailEntityRepository extends JpaRepository<EchoAnswerSheetEntity,Long> {
    EchoAnswerSheetDetailEntity findById(long id);
    EchoAnswerSheetDetailEntity findByStudent(String name);
}
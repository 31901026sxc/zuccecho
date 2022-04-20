package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.entity.EchoAnswerSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EchoAnswerSheetEntityRepository extends JpaRepository<EchoAnswerSheetEntity, Integer> {
    List<Integer> findAllByQuestionnaireId(int id);
}
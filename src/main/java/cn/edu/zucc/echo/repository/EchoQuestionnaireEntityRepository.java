package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.EchoQuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EchoQuestionnaireEntityRepository extends JpaRepository<EchoQuestionnaireEntity, Integer> {
}
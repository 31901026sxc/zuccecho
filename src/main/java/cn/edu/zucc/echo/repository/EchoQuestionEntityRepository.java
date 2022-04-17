package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.EchoQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EchoQuestionEntityRepository extends JpaRepository<EchoQuestionEntity, Integer> {
}
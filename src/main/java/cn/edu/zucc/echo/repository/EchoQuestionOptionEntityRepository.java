package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.EchoQuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EchoQuestionOptionEntityRepository extends JpaRepository<EchoQuestionOptionEntity, Integer> {
}
package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.BasicClassStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicClassStudentEntityRepository extends JpaRepository<BasicClassStudentEntity, Integer> {
}
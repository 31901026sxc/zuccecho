package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.BasicClassStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasicClassStudentEntityRepository extends JpaRepository<BasicClassStudentEntity, Integer> {
    List<Integer> findAllBy_class_Sid(int classId);
}
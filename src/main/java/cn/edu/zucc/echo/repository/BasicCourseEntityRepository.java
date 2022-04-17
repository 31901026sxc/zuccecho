package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.BasicCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicCourseEntityRepository extends JpaRepository<BasicCourseEntity, Integer> {
}
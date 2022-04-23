package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.BasicClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicClassEntityRepository extends JpaRepository<BasicClassEntity, Integer> {
    BasicClassEntity findByCode(String code);
}
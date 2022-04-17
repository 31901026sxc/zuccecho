package cn.edu.zucc.echo.repository;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicUserEntityRepository extends JpaRepository<BasicUserEntity, Integer> {
    BasicUserEntity findByUserCode(String userCode);
}
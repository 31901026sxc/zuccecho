package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    List<BasicUserEntity> findAllStudent(int classId);

}

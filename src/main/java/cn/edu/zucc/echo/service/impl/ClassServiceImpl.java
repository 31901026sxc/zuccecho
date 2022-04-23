package cn.edu.zucc.echo.service.impl;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.form.BasicClassStudentDto;
import cn.edu.zucc.echo.quartz.SetTrigger;
import cn.edu.zucc.echo.repository.BasicClassStudentEntityRepository;
import cn.edu.zucc.echo.repository.BasicUserEntityRepository;
import cn.edu.zucc.echo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private BasicClassStudentEntityRepository studentEntityRepository;
    @Autowired
    private BasicUserEntityRepository userEntityRepository;

    @Override
    public List<BasicUserEntity> findAllStudent(int classId) {
        List<BasicUserEntity> list = new ArrayList();
        for (int e: studentEntityRepository.findAllBy_class_Sid(classId)
             ) {
            list.add(userEntityRepository.getOne(e));
        }
        return list;
    }

    @Override
    public Integer addStudents(List<BasicClassStudentDto> classStudentDtos) {
        //TODO 将指定学生课程信息序列存入BasicClassStudent表
        return null;
    }


}

package cn.edu.zucc.echo.service.impl;

import cn.edu.zucc.echo.entity.BasicClassEntity;
import cn.edu.zucc.echo.entity.BasicClassStudentEntity;
import cn.edu.zucc.echo.entity.BasicCourseEntity;
import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicClassDto;
import cn.edu.zucc.echo.form.BasicClassStudentDto;
import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.quartz.SetTrigger;
import cn.edu.zucc.echo.repository.BasicClassEntityRepository;
import cn.edu.zucc.echo.repository.BasicClassStudentEntityRepository;
import cn.edu.zucc.echo.repository.BasicUserEntityRepository;
import cn.edu.zucc.echo.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private BasicClassStudentEntityRepository studentEntityRepository;
    @Autowired
    private BasicUserEntityRepository userEntityRepository;
    @Autowired
    private BasicClassEntityRepository classEntityRepository;
    @Autowired
    private BasicClassStudentEntityRepository classStudentEntityRepository;

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
    public List<Integer> addStudents(List<BasicClassStudentDto> classStudentDto) {
        List<Integer> ids = new ArrayList<Integer>();
        classStudentDto.forEach(e ->{
            BasicClassStudentEntity classStudentEntity = new BasicClassStudentEntity();
            BeanUtils.copyProperties(e, classStudentEntity);
            classStudentEntity.setCreateTime(Instant.ofEpochMilli(System.currentTimeMillis()));
            classStudentEntityRepository.save(classStudentEntity);
            ids.add(classStudentEntity.getsid());
        });
        return ids;
    }

    @Override
    public Integer addClass(BasicClassDto classDto) throws EchoServiceException {
        if (classDto.getCode().equals("") || classDto.getCode() == null) {
            throw new EchoServiceException("新建班级不能上传code");
        }

        BasicClassEntity classEntity = new BasicClassEntity();
        BeanUtils.copyProperties(classDto, classEntity);
        classEntityRepository.save(classEntity);
        return classEntity.getSid();
    }

    @Override
    public String deleteClass(BasicClassDto classDto) throws EchoServiceException{
        if (classDto.getCode().equals("") || classDto.getCode() == null) {
            throw new EchoServiceException("班级code不能为空");
        }

        BasicClassEntity classEntity = classEntityRepository.findByCode(classDto.getCode());
        classEntityRepository.delete(classEntity);
        return classEntity.getCode();
    }

    @Override
    public String modifyClass(BasicClassDto classDto) throws EchoServiceException{
        if (classDto.getCode().equals("") || classDto.getCode() == null) {
            throw new EchoServiceException("班级code不能为空");
        }

        BasicClassEntity classEntity = new BasicClassEntity();
        BeanUtils.copyProperties(classDto, classEntity);
        classEntityRepository.save(classEntity);
        return classEntity.getCode();
    }

    @Override
    public BasicClassDto searchClass(Integer classId) throws EchoServiceException{
        if (classId == 0) {
            throw new EchoServiceException("班级id不能为空");
        }

        BasicClassEntity classEntity = classEntityRepository.getOne(classId);
        BasicClassDto basicClassDto = new BasicClassDto(classEntity.getCode(),classEntity.getName(),
                classEntity.getSid(),classEntity.getTeacher().getsid(),classEntity.getYear(),
                classEntity.getSemester(),classEntity.getIsClose(),classEntity.getCreateTime());
        return basicClassDto;
    }
}

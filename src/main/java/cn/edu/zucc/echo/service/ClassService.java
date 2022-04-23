package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.entity.BasicClassEntity;
import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicClassDto;
import cn.edu.zucc.echo.form.BasicClassStudentDto;
import cn.edu.zucc.echo.form.BasicCourseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {
    List<BasicUserEntity> findAllStudent(int classId);
    //TODO 未使用
    List<Integer> addStudents(List<BasicClassStudentDto> classStudentDto);

    Integer addClass(BasicClassDto classDto) throws EchoServiceException;//返回id

    String deleteClass(BasicClassDto classDto) throws EchoServiceException;//同上

    String modifyClass(BasicClassDto classDto) throws EchoServiceException;

    BasicClassDto searchClass(Integer classId) throws EchoServiceException;//这是个样例，为以后可能的控制反转做铺垫
}

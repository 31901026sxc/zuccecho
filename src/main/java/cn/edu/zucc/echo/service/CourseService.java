package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicCourseDto;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    Integer addCourse(BasicCourseDto courseDto) throws EchoServiceException;//返回id

    String deleteCourse(BasicCourseDto courseDto) throws EchoServiceException;//同上

    String modifyCourse(BasicCourseDto courseDto) throws EchoServiceException;

    BasicCourseDto searchCourse(Integer courseId) throws EchoServiceException;//这是个样例，为以后可能的控制反转做铺垫
}
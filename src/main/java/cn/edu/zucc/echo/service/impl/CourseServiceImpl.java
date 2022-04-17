package cn.edu.zucc.echo.service.impl;

import cn.edu.zucc.echo.entity.BasicCourseEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.repository.BasicCourseEntityRepository;
import cn.edu.zucc.echo.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseService {
    @Autowired
    private BasicCourseEntityRepository courseEntityRepository;

    @Override
    public Integer addCourse(BasicCourseDto courseDto) throws EchoServiceException{
        if (courseDto.getCode().equals("") || courseDto.getCode() == null) {
            throw new EchoServiceException("新建课程不能上传code");
        }

        BasicCourseEntity courseEntity = new BasicCourseEntity();
        BeanUtils.copyProperties(courseDto, courseEntity);
        courseEntityRepository.save(courseEntity);
        return courseEntity.getsid();
    }

    @Override
    public String deleteCourse(BasicCourseDto courseDto) throws EchoServiceException{
        if (courseDto.getCode().equals("") || courseDto.getCode() == null) {
            throw new EchoServiceException("课程code不能为空");
        }

        BasicCourseEntity courseEntity = courseEntityRepository.findByCode(courseDto.getCode());
        courseEntityRepository.delete(courseEntity);
        return courseEntity.getCode();
    }

    @Override
    public String modifyCourse(BasicCourseDto courseDto) throws EchoServiceException{
        if (courseDto.getCode().equals("") || courseDto.getCode() == null) {
            throw new EchoServiceException("课程code不能为空");
        }

        BasicCourseEntity courseEntity = new BasicCourseEntity();
        BeanUtils.copyProperties(courseDto, courseEntity);
        courseEntityRepository.save(courseEntity);
        return courseEntity.getCode();
    }

    @Override
    public BasicCourseDto searchCourse(Integer courseId) throws EchoServiceException{
        if (courseId == 0) {
            throw new EchoServiceException("课程id不能为空");
        }

        BasicCourseEntity courseEntity = courseEntityRepository.getOne(courseId);
        BasicCourseDto basicCourseDto = new BasicCourseDto(courseEntity.getCode(),courseEntity.getName(),
                courseEntity.getCreateTime(),courseEntity.getModifyTime());
        return basicCourseDto;
    }
}

package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(ModelApiController.class);

    @Autowired
    private CourseService courseService;

    @CachePut(key = "viewCourse")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseData viewCourse(Integer sid) {
        BasicCourseDto courseDto = courseService.searchCourse(sid);
        logger.warn("query Course:{}", courseDto);
        return new ResponseData(ResponseMsg.SUCCESS, courseDto);
    }

    @CachePut(key = "#course.getCode()")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createModel(@RequestBody BasicCourseDto course) {
        Integer courseId = courseService.addCourse(course);
        logger.warn("Course {} created", courseId);
        return new ResponseData(ResponseMsg.SUCCESS, courseId);
    }

    @CacheEvict(key = "#course.getCode()")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteCourse(@RequestBody BasicCourseDto course) {
        String result = courseService.deleteCourse(course);
        logger.warn("Course {} delete", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }

    @CachePut(key = "#course.getCode()")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData modifyCourse(@RequestBody BasicCourseDto course) {
        String result = courseService.modifyCourse(course);
        logger.warn("Course {} modify", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }
}

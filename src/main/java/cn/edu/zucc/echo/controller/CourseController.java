package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(ModelApiController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseData viewMode(Integer sid) {
        BasicCourseDto modelDto = courseService.searchCourse(sid);
        logger.warn("query Model:{}", modelDto);
        return new ResponseData(ResponseMsg.SUCCESS, modelDto);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createModel(@RequestBody BasicCourseDto course) {
        Integer courseId = courseService.addCourse(course);
        logger.warn("Model {} created", courseId);
        return new ResponseData(ResponseMsg.SUCCESS, courseId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteCourse(@RequestBody BasicCourseDto course) {
        String result = courseService.deleteCourse(course);
        logger.warn("Model {} created", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData modifyCourse(@RequestBody BasicCourseDto course) {
        String result = courseService.modifyCourse(course);
        logger.warn("Model {} created", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }
}

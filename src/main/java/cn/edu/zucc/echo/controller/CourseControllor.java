package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.CourseService;
import cn.edu.zucc.echo.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseControllor {
    private final Logger logger = LoggerFactory.getLogger(ModelApiController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/view/{sid}", method = RequestMethod.GET)
    public ResponseData viewMode(@PathVariable("sid") Integer sid) {
        BasicCourseDto modeldto = courseService.searchCourse(sid);
        logger.warn("query Model:{}", modeldto);
        return new ResponseData(ResponseMsg.SUCCESS, modeldto);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createModel(@RequestBody BasicCourseDto course){
        Integer courseid = courseService.addCourse(course);
        logger.warn("Model {} created", courseid);
        return new ResponseData(ResponseMsg.SUCCESS, courseid);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deletecourse(@RequestBody BasicCourseDto course){
        String result = courseService.deleteCourse(course);
        logger.warn("Model {} created", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData modifyfcourse(@RequestBody BasicCourseDto course){
        String result = courseService.modifyCourse(course);
        logger.warn("Model {} created", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }
}

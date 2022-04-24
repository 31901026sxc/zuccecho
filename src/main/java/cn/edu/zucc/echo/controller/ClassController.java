package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.BasicClassDto;
import cn.edu.zucc.echo.form.BasicClassStudentDto;
import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.ClassService;
import cn.edu.zucc.echo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    private final Logger logger = LoggerFactory.getLogger(ModelApiController.class);
    @Autowired
    private ClassService classService;

    @CachePut(key = "viewClass")
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseData viewClass(Integer sid) {
        BasicClassDto classDto = classService.searchClass(sid);
        logger.warn("query Class:{}", classDto);
        return new ResponseData(ResponseMsg.SUCCESS, classDto);
    }

    @CachePut(key = "#classId")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createModel(@RequestBody BasicClassDto classDto) {
        Integer classId = classService.addClass(classDto);
        logger.warn("Class {} created", classId);
        return new ResponseData(ResponseMsg.SUCCESS, classId);
    }

    @CacheEvict(key = "#classDto.getCode()")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteCourse(@RequestBody BasicClassDto classDto) {
        String result = classService.deleteClass(classDto);
        logger.warn("Class {} delete", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }

    @CachePut(key = "#classDto.getCode()")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData modifyCourse(@RequestBody BasicClassDto classDto) {
        String result = classService.modifyClass(classDto);
        logger.warn("Class {} modify", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addStudents(@RequestBody List<BasicClassStudentDto> classStudentDto) {
        List<Integer> result = classService.addStudents(classStudentDto);
        logger.warn("student {} add", result) ;
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }
}

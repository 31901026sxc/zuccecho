package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.ClassService;
import cn.edu.zucc.echo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class ClassController {
    private final Logger logger = LoggerFactory.getLogger(ModelApiController.class);
    @Autowired
    private ClassService classService;
    //TODO 自己写

}

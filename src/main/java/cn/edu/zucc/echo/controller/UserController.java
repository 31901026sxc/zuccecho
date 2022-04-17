package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.BasicCourseDto;
import cn.edu.zucc.echo.form.BasicUserDto;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.form.TpQuestionDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.CourseService;
import cn.edu.zucc.echo.service.ModelService;
import cn.edu.zucc.echo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/view/{sid}", method = RequestMethod.GET)
//    public ResponseData viewMode(@PathVariable("sid") Integer sid) {
//        BasicUserDto userdto = UserService.searchUser(sid);
//        logger.warn("query Model:{}", userdto);
//        return new ResponseData(ResponseMsg.SUCCESS, userdto);
//    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addUser(@RequestBody BasicUserDto user){
        Integer userid = userService.addUser(user);
        logger.warn("User {} created", userid);
        return new ResponseData(ResponseMsg.SUCCESS, userid);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteUser(@RequestBody BasicUserDto user){
        String result = userService.deleteUser(user);
        logger.warn("User {} created", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData modifyUser(@RequestBody BasicUserDto user){
        String result = userService.modifyUser(user);
        logger.warn("User {} created", result);
        return new ResponseData(ResponseMsg.SUCCESS, result);
    }

    @RequestMapping(value = "/view/{usercode}", method = RequestMethod.GET)
    public ResponseData viewMode(@PathVariable("usercode") String usercode) {
        BasicUserDto userdto = userService.searchUser(usercode);
        logger.warn("query User:{}", userdto);
        return new ResponseData(ResponseMsg.SUCCESS, userdto);
    }

}
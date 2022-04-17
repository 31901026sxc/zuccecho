package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.exception.EchoServiceException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EchoErrorController{
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleGlobleError(HttpServletRequest request, Exception ex) {
        //用Map容器返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("msg", "应用程序错误:" + ex.getMessage());
        ex.printStackTrace();
        return map;
    }

    @ExceptionHandler(EchoServiceException.class)
    @ResponseBody
    public Map<String, Object> handleEchoError(HttpServletRequest request, EchoServiceException ex) {
        //用Map容器返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("msg", ex.getMessage());
        ex.printStackTrace();
        return map;
    }
}

package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.EchoQuestionnaireDto;
import cn.edu.zucc.echo.form.QuestionnaireSeedDto;
import cn.edu.zucc.echo.form.RemindDto;
import cn.edu.zucc.echo.quartz.SetTrigger;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.EchoService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("echo")
public class EchoController {
    private final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @Autowired
    private EchoService echoService;
    @Autowired
    private RedisTemplate redisTemplate;

    @CachePut(key = "#questionnaireId"+"publish")
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData publishFeedback(@RequestBody QuestionnaireSeedDto seed) throws SchedulerException, InterruptedException {
        EchoQuestionnaireDto questionnaire = echoService.publishQuestionnaire(seed);

        return new ResponseData(ResponseMsg.SUCCESS, questionnaire);
    }

    @RequestMapping(value = "/view/{sid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData publishFeedback(@PathVariable Integer sid){
        EchoQuestionnaireDto EchoQuestionnaireDto = echoService.queryQuestionnaireDetail(sid);
        return new ResponseData(ResponseMsg.SUCCESS, EchoQuestionnaireDto);
    }
    @RequestMapping(value = "/remind", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData remindUndone(@PathVariable Integer questionnaireId){
        Object read = redisTemplate.opsForValue().get(questionnaireId+"remind");
        List<RemindDto> undone = (List<RemindDto>)read;
        return new ResponseData(ResponseMsg.SUCCESS, undone);
    }

}

package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.EchoQuestionnaireDto;
import cn.edu.zucc.echo.form.QuestionnaireSeedDto;
import cn.edu.zucc.echo.quartz.SetTrigger;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.EchoService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("echo")
public class EchoController {
    private final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @Autowired
    private EchoService echoService;


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

}

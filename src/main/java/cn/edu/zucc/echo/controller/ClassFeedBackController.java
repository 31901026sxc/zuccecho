package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.EchoQuestionnaireDto;
import cn.edu.zucc.echo.form.QuestionnaireSeedDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class ClassFeedBackController {
    private final Logger logger = LoggerFactory.getLogger(ClassFeedBackController.class);

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData publishFeedback(@RequestBody QuestionnaireSeedDto questionire1){
        EchoQuestionnaireDto questionire = feedbackService.publishFeedback(questionire1);
        return new ResponseData(ResponseMsg.SUCCESS, questionire);
    }

    @RequestMapping(value = "/view/{sid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData publishFeedback(@PathVariable Integer sid){
        EchoQuestionnaireDto EchoQuestionnaireDto = feedbackService.queryQuestionnaireDetail(sid);
        return new ResponseData(ResponseMsg.SUCCESS, EchoQuestionnaireDto);
    }
}

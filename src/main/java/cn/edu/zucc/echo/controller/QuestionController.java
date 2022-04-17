package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.TpQuestionDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/view/{sid}", method = RequestMethod.GET)
    public ResponseData viewMode(@PathVariable("sid") Integer sid) {
        TpQuestionDto questiondto = questionService.queryQuestionDetail(sid);
        logger.warn("query Question:{}", questiondto);
        return new ResponseData(ResponseMsg.SUCCESS, questiondto);
    }

    /**
     * 添加问题，设想前端提供一个问卷编辑界面，提交对应的json结构
     * {
     *     "category": "",
     *     "title": "",
     *     "isRequired":""
     *     "remark":""
     *     "options":[
     *         {
     *             "questionID":1,
     *             "title":"subjective",
     *
     *         },
     *         {
     *             "questionID":2,
     *      *      "title":"subjective",
     *         }
     *     ]
     * }
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createQuestion(@RequestBody TpQuestionDto Question){
        Integer Questionsid = questionService.createQuestion(Question);
        logger.warn("Question {} created", Questionsid);

        return new ResponseData(ResponseMsg.SUCCESS, Question.getCategory());
    }
}
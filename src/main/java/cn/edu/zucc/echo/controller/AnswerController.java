package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.EchoAnswerSheetDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 用户提交答卷内容
     * {
     *     "questionireid": 10,
     *     "userid": 2,
     *     "answerDetail":[
     *         {
     *             "questionid":11,
     *             "answerContent":"学会了好好编程"
     *         },
     *                 {
     *             "questionid":12,
     *             "answerContent":"13"
     *         }
     *     ]
     * }
     * @param EchoAnswerSheetDto
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData submitAnswer(@RequestBody EchoAnswerSheetDto EchoAnswerSheetDto){
        logger.warn(EchoAnswerSheetDto.toString());
        Integer answerSheetid = feedbackService.answerWorkSheet(EchoAnswerSheetDto);
        if(answerSheetid > 0){
            return new ResponseData(ResponseMsg.SUCCESS, answerSheetid);
        } else {
            return new ResponseData(ResponseMsg.FAILED, EchoAnswerSheetDto);
        }
    }
}

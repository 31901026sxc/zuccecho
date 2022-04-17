package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.form.TpQuestionDto;
import cn.edu.zucc.echo.result.ResponseData;
import cn.edu.zucc.echo.result.ResponseMsg;
import cn.edu.zucc.echo.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model")
public class ModelApiController {
    private final Logger logger = LoggerFactory.getLogger(ModelApiController.class);

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/view/{sid}", method = RequestMethod.GET)
    public ResponseData viewMode(@PathVariable("sid") Integer sid) {
        TpModelDto modelDto = modelService.queryModelDetail(sid);
        logger.warn("query Model:{}", modelDto);
        return new ResponseData(ResponseMsg.SUCCESS, modelDto);
    }

    /**
     * 添加模板，设想前端提供一个问卷编辑界面，提交对应的json结构
     * {
     *     "name": "日常反馈看板",
     *     "memo": "用于日常的课堂反馈用",
     *     "questions":[
     *         {
     *             "title":"本次课程最大的收获是什么?",
     *             "category":"subjective",
     *             "isRequired":"true",
     *             "remark":"",
     *             "options":[]
     *         },
     *         {
     *             "title":"本次课程获得感?",
     *             "category":"single",
     *             "isRequired":"true",
     *             "remark":"",
     *             "options":[
     *                 {"title":"高"},
     *                 {"title":"还不错"},
     *                 {"title":"比较低"}
     *             ]
     *         }
     *     ]
     * }
     * @param Model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createModel(@RequestBody TpModelDto Model){
        Integer ModelSid = modelService.createModel(Model);
        logger.warn("Model {} created", ModelSid);

        return new ResponseData(ResponseMsg.SUCCESS, ModelSid);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData AddAQuestion(@RequestBody TpQuestionDto Model){
        String result = modelService.addAQuestion(Model);
        logger.warn("Model {} created", result);

        return new ResponseData(ResponseMsg.SUCCESS, result);
    }
}
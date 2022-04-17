package cn.edu.zucc.echo.controller;

import cn.edu.zucc.echo.form.TpModelDto;
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
        TpModelDto modeldto = modelService.queryModelDetail(sid);
        logger.warn("query Model:{}", modeldto);
        return new ResponseData(ResponseMsg.SUCCESS, modeldto);
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
        Integer Modelsid = modelService.createModel(Model);
        logger.warn("Model {} created", Modelsid);

        return new ResponseData(ResponseMsg.SUCCESS, Modelsid);
    }
}
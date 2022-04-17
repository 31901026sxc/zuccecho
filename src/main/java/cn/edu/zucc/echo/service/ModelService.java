package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.form.TpQuestionDto;


public interface ModelService {
    /**
     * 创建一个模板
     * @param modeldto
     * @return
     * @throws EchoServiceException
     */
    Integer createModel(TpModelDto modeldto) throws EchoServiceException;

    /**
     * 查询模板明细
     * @param
     * @return
     * @throws EchoServiceException
     */
    TpModelDto queryModelDetail(Integer modelsid) throws EchoServiceException;



    String addAQuestion(TpQuestionDto questionDto , Integer modelid) throws EchoServiceException;
}

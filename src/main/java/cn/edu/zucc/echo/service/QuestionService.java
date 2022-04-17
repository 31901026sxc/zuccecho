package cn.edu.zucc.echo.service;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.TpModelDto;
import cn.edu.zucc.echo.form.TpQuestionDto;
import cn.edu.zucc.echo.form.TpQuestionOptionDto;
public interface QuestionService {
    Integer createQuestion(TpQuestionDto questiondto) throws EchoServiceException;

    TpQuestionDto queryQuestionDetail(Integer questionid) throws EchoServiceException;
}

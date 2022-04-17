package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.EchoAnswerSheetDto;
import cn.edu.zucc.echo.form.EchoQuestionnaireDto;
import cn.edu.zucc.echo.form.QuestionnaireSeedDto;
import org.springframework.stereotype.Service;


@Service
public interface FeedbackService {
    /**
     * 教师发布一个反馈问卷
     * @param dto
     * @return
     * @throws EchoServiceException
     */
    EchoQuestionnaireDto publishFeedback(QuestionnaireSeedDto dto) throws EchoServiceException;

    /**
     * 返回给定的反馈问卷
     * @param questioniresid
     * @return
     * @throws EchoServiceException
     */
    EchoQuestionnaireDto queryQuestionnaireDetail(Integer questioniresid) throws EchoServiceException;

    /**
     * 用户回答问卷
     * @param EchoAnswerSheetDto
     * @return
     */
    Integer answerWorkSheet(EchoAnswerSheetDto EchoAnswerSheetDto) throws EchoServiceException;

}

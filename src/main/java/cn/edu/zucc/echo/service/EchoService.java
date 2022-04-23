package cn.edu.zucc.echo.service;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.entity.EchoQuestionnaireEntity;
import cn.edu.zucc.echo.exception.EchoServiceException;
import cn.edu.zucc.echo.form.EchoAnswerSheetDto;
import cn.edu.zucc.echo.form.EchoQuestionnaireDto;
import cn.edu.zucc.echo.form.QuestionnaireSeedDto;
import cn.edu.zucc.echo.form.RemindDto;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EchoService {
    /**
     * 教师发布一个反馈问卷
     * @param dto
     * @return
     * @throws EchoServiceException
     */
    EchoQuestionnaireDto publishQuestionnaire(QuestionnaireSeedDto dto) throws EchoServiceException, SchedulerException, InterruptedException;

    /**
     * 返回给定的反馈问卷
     * @param questionnaireSid
     * @return
     * @throws EchoServiceException
     */
    EchoQuestionnaireDto queryQuestionnaireDetail(Integer questionnaireSid) throws EchoServiceException;

    /**
     * 用户回答问卷
     * @param EchoAnswerSheetDto
     * @return
     */
    Integer answerWorkSheet(EchoAnswerSheetDto EchoAnswerSheetDto) throws EchoServiceException;


    //    获得尚未答题的学生
    List<BasicUserEntity> getNotAnswered(Integer questionnaireId);


    //    获得已答题的学生
    List<BasicUserEntity> getAnswered(Integer questionnaireId);

    //TODO 未使用
    List<RemindDto> remind(List<BasicUserEntity> undone, EchoQuestionnaireEntity questionnaire);
}

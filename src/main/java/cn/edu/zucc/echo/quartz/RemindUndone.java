package cn.edu.zucc.echo.quartz;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.service.EchoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public class RemindUndone implements Job {
    @Autowired
    private EchoService echoService;

    @CachePut(key = "#questionnaireId"+"remind")
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer questionnaireId = jobExecutionContext.getJobDetail()
                .getJobDataMap()
                .getIntValue("questionnaireId");
        List<BasicUserEntity> undone = echoService
                .getNotAnswered(questionnaireId);
        System.out.println(undone.toString());
    }


}

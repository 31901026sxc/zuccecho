package cn.edu.zucc.echo.quartz;

import cn.edu.zucc.echo.entity.BasicUserEntity;
import cn.edu.zucc.echo.repository.BasicCourseEntityRepository;
import cn.edu.zucc.echo.service.EchoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RemindUndone implements Job {
    @Autowired
    private EchoService echoService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<BasicUserEntity> undone = echoService
                .getNotAnswered(jobExecutionContext.getJobDetail()
                .getJobDataMap()
                .getIntValue("questionnaireId"));

    }


}

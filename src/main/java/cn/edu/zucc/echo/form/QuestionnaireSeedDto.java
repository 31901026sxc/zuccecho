package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class QuestionnaireSeedDto implements Serializable {
    private final Integer classId;
    private final String name;
    private final Integer modelid;
    private final Integer teacherid;
    private final Instant publishTime;
    private final Instant deadLine;
}

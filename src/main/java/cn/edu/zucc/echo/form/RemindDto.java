package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class RemindDto implements Serializable {
    private final Integer studentId;
    private final String StudentName;
    private final String questionnaireName;
    private final Instant deadLine;
}

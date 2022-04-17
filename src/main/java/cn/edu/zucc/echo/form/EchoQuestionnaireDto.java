package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class EchoQuestionnaireDto implements Serializable {
    private final Integer classid;
    private final String name;
    private final String status;
    private final String memo;
    private final Instant publishTime;
    private final Instant deadLine;
    private final List<EchoQuestionDto> questions;
}

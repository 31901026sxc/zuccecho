package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
@Data
public class QuestionireSeedDto implements Serializable {
    private final Integer classid;
    private final String name;
    private final Integer modelid;
    private final Integer teacherid;
    private final Instant publishTime;
    private final Instant deadLine;
}

package cn.edu.zucc.echo.form;

import cn.edu.zucc.echo.utils.ZuccEchoUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class RemindDto implements Serializable {
    public static final String KEY_PREFIX = "remind";
    private final Integer studentId;
    private final String StudentName;
    private final String questionnaireName;
    private final Instant deadLine;

}

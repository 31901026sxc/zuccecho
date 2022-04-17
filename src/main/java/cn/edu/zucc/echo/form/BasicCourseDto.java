package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BasicCourseDto implements Serializable {
    private final String code;
    private final String name;
    private final Instant createTime;
    private final Instant modifyTime;
}

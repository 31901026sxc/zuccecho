package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BasicClassDto implements Serializable {
    private final String code;
    private final String name;
    private final Integer coursesId;
    private final Integer teachersId;
    private final Integer year;
    private final Integer semester;
    private final Integer isClose;
    private final Instant createTime;
}

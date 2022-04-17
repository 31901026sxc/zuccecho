package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BasicClassDto implements Serializable {
    private final String code;
    private final String name;
    private final Integer coursesid;
    private final Integer teachersid;
    private final Integer year;
    private final Integer semester;
    private final Integer isClose;
    private final Instant createTime;
}

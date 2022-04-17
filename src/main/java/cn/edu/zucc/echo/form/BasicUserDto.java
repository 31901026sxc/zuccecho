package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BasicUserDto implements Serializable {
    private final String userCode;
    private final String name;
    private final String passwd;
    private final Instant createTime;
    private final Instant modifyTime;
    private final String userType;
}

package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicClassStudentDto implements Serializable {
    private final Integer classSid;
    private final Integer studentSid;
}

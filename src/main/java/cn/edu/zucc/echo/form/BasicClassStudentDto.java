package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicClassStudentDto implements Serializable {
    private final Integer classsid;
    private final Integer studentsid;
}

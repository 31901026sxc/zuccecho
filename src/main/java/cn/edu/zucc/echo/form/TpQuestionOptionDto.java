package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;


@Data
public class TpQuestionOptionDto implements Serializable {
    private final Integer questionid;
    private final String title;
}

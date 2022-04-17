package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class EchoQuestionOptionDto implements Serializable {
    private final Integer questionsid;
    private final String title;
}

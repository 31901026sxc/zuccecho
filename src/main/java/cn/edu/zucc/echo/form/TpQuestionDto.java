package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TpQuestionDto implements Serializable {
    private final int id;
    private final String category;
    private final String title;
    private final String isRequired;
    private final String remark;

    private final List<TpQuestionOptionDto> options;
}

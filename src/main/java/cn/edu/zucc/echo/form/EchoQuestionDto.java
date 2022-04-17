package cn.edu.zucc.echo.form;

import cn.edu.zucc.echo.form.EchoQuestionOptionDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EchoQuestionDto implements Serializable {
    private final Integer questioniresid;
    private final String category;
    private final String title;
    private final String isRequired;
    private final String remark;

    private final List<EchoQuestionOptionDto> options;
}

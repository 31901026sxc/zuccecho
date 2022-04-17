package cn.edu.zucc.echo.form;
import lombok.Data;
import java.io.Serializable;
@Data
public class EchoAnswerSheetDetailDto implements Serializable {
    private Integer id;
    private Integer questionId;
    private String questionTitle;
    private String questionCategory;
    private String answerContent;
    private String answerContentView;
    private String memo;
}

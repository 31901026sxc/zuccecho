package cn.edu.zucc.echo.form;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
@Data
public class EchoAnswerSheetDto implements Serializable {
    private Integer id;
    private Integer questionnaireId;
    private Integer userId;
    private String startTime;
    private String submitTime;
    private String memo;
    private List<EchoAnswerSheetDetailDto> answerDetail;
}

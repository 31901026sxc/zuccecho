package cn.edu.zucc.echo.form;

import cn.edu.zucc.echo.utils.ZuccEchoUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class EchoQuestionnaireDto implements Serializable {
    public static final String KEY_PREFIX = "EchoQuestionnaire";
    private final Integer id;
    private final Integer classId;
    private final String name;
    private final String status;
    private final String memo;
    private final Instant publishTime;
    private final Instant deadLine;
    private final List<EchoQuestionDto> questions;

    public static String cacheKey(Integer paperId){
        return ZuccEchoUtils.generateCacheKey(KEY_PREFIX, paperId.toString());
    }
}

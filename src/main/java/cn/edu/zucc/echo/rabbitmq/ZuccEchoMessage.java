package cn.edu.zucc.echo.rabbitmq;

import cn.edu.zucc.echo.utils.ZuccEchoUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengbin
 * @version 1.0
 */
public class ZuccEchoMessage implements Serializable {
    public static final String CATEGORY_MODEL_PUB = "m_pub";
    public static final String CATEGORY_ANSWER = "m_answer";
    public static final String CATEGORY_PAPER = "m_paper";
    public static final String CATEGORY_ERROR= "m_error";
    public static final String CATEGORY_STAT= "m_stat";
    public static final String CATEGORY_RPC= "m_rpc";
    private String category;
    private Map<String, Object> content = new HashMap<>();

    public ZuccEchoMessage() {}

    public ZuccEchoMessage(String category) {
        this.category = category;
    }

    public void appendContent(String key, Object value){
        content.put(key, value);
    }

    public String stringfy(){
        return ZuccEchoUtils.beanToJson(this);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}

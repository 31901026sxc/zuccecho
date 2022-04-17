package cn.edu.zucc.echo.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TpModelDto implements Serializable {
    private final int id;
    private final String name;
    private final String status;
    private final String memo;
    private final List<TpQuestionDto> questions;
    public String toString() {
        return "TpModelDto{" +
                "id='" + id + '\'' +
                ",name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}

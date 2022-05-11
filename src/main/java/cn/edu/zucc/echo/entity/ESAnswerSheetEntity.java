package cn.edu.zucc.echo.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Document(indexName = "ea1", replicas = 0, shards = 5)
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
@Data
public class ESAnswerSheetEntity implements Serializable {

    private Integer id;
    @Field(type = FieldType.Keyword)//存储数据时候，不会分词建立索引
    private String student;


    private Instant startTime;


    private Instant submitTime;

    private String memo;
    @Field(type = FieldType.Keyword)//存储数据时候，不会分词建立索引
    private ESQuestionnaireEntity questionnaire;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")//ik_max_word使用ik分词器
    private Set<EchoAnswerSheetDetailEntity> echoAnswerSheetDetail = new LinkedHashSet<>();

    public Set<EchoAnswerSheetDetailEntity> getEchoAnswerSheetDetail() {
        return echoAnswerSheetDetail;
    }

    public void setEchoAnswerSheetDetail(Set<EchoAnswerSheetDetailEntity> echoAnswerSheetDetail) {
        this.echoAnswerSheetDetail = echoAnswerSheetDetail;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Instant getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Instant submitTime) {
        this.submitTime = submitTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public ESQuestionnaireEntity getquestionnaire() {
        return questionnaire;
    }

    public void setquestionnaire(ESQuestionnaireEntity questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Integer getsid() {
        return id;
    }

    public void setsid(Integer id) {
        this.id = id;
    }

}
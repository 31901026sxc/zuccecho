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

@Document(indexName = "eq1", replicas = 0, shards = 5)
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
@Data
public class ESQuestionnaireEntity implements Serializable {
    private Integer id;
    @Field(type = FieldType.Keyword)//存储数据时候，不会分词建立索引
    private BasicClassEntity _class;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")//ik_max_word使用ik分词器
    private String name;
    @Field(type = FieldType.Keyword)//存储数据时候，不会分词建立索引
    private String status;

    private String memo;

    private Instant publishTime;

    private Instant deadLine;

    private Integer creatorId;

    private Instant createTime;

    private Set<EchoQuestionEntity> echoQuestions = new LinkedHashSet<>();

    private Set<EchoAnswerSheetEntity> echoAnswerSheets = new LinkedHashSet<>();



    public Set<EchoAnswerSheetEntity> getEchoAnswerSheets() {
        return echoAnswerSheets;
    }

    public void setEchoAnswerSheets(Set<EchoAnswerSheetEntity> echoAnswerSheets) {
        this.echoAnswerSheets = echoAnswerSheets;
    }

    public Set<EchoQuestionEntity> getEchoQuestions() {
        return echoQuestions;
    }

    public void setEchoQuestions(Set<EchoQuestionEntity> echoQuestions) {
        this.echoQuestions = echoQuestions;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Instant getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Instant deadLine) {
        this.deadLine = deadLine;
    }

    public Instant getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Instant publishTime) {
        this.publishTime = publishTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicClassEntity get_class() {
        return _class;
    }

    public void set_class(BasicClassEntity _class) {
        this._class = _class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
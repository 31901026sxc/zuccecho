package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "echo_questionnaire")
public class EchoQuestionnaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private BasicClassEntity _class;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "memo", length = 500)
    private String memo;

    @Column(name = "publish_time")
    private Instant publishTime;

    @Column(name = "dead_line")
    private Instant deadLine;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "create_time")
    private Instant createTime;

    @OneToMany(mappedBy = "questionnaire")
    private Set<EchoQuestionEntity> echoQuestions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "questionnaire")
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
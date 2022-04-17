package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "echo_answer_sheet")
public class EchoAnswerSheetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_sid")
    private BasicUserEntity student;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "submit_time")
    private Instant submitTime;

    @Column(name = "memo")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    private EchoQuestionnaireEntity questionnaire;

    @OneToMany(mappedBy = "sheet")
    private Set<EchoAnswerSheetDetailEntity> echoAnswerSheetDetail = new LinkedHashSet<>();

    public Set<EchoAnswerSheetDetailEntity> getEchoAnswerSheetDetail() {
        return echoAnswerSheetDetail;
    }

    public void setEchoAnswerSheetDetail(Set<EchoAnswerSheetDetailEntity> echoAnswerSheetDetail) {
        this.echoAnswerSheetDetail = echoAnswerSheetDetail;
    }

    /*public EchoQuestionnaireEntity getquestionnaire() {
        return questionnaire;
    }

    public void setquestionnaire(EchoQuestionnaireEntity questionnaire) {
        this.questionnaire = questionnaire;
    }*/

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

    public BasicUserEntity getStudent() {
        return student;
    }

    public void setStudent(BasicUserEntity student) {
        this.student = student;
    }

    public EchoQuestionnaireEntity getquestionnaire() {
        return questionnaire;
    }

    public void setquestionnaire(EchoQuestionnaireEntity questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Integer getsid() {
        return sid;
    }

    public void setsid(Integer sid) {
        this.sid = sid;
    }

}
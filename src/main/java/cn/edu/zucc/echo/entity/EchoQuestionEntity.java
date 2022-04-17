package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "echo_question")
public class EchoQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionire_id")
    private EchoQuestionireEntity questionire;

    @Column(name = "category")
    private String category;

    @Column(name = "title", length = 600)
    private String title;

    @Column(name = "is_required")
    private String isRequired;

    @Column(name = "remark", length = 600)
    private String remark;

    @OneToMany(mappedBy = "question")
    private Set<EchoQuestionOptionEntity> echoQuestionOptions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "question")
    private Set<EchoAnswerSheetDetailEntity> echoAnswerSheetDetails = new LinkedHashSet<>();

    public Set<EchoAnswerSheetDetailEntity> getEchoAnswerSheetDetails() {
        return echoAnswerSheetDetails;
    }

    public void setEchoAnswerSheetDetails(Set<EchoAnswerSheetDetailEntity> echoAnswerSheetDetails) {
        this.echoAnswerSheetDetails = echoAnswerSheetDetails;
    }

    public Set<EchoQuestionOptionEntity> getEchoQuestionOptions() {
        return echoQuestionOptions;
    }

    public void setEchoQuestionOptions(Set<EchoQuestionOptionEntity> echoQuestionOptions) {
        this.echoQuestionOptions = echoQuestionOptions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public EchoQuestionireEntity getQuestionire() {
        return questionire;
    }

    public void setQuestionire(EchoQuestionireEntity questionire) {
        this.questionire = questionire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
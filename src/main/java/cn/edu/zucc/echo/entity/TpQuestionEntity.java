package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tp_question")
public class TpQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private TpModelEntity model;

    @Column(name = "category")
    private String category;

    @Column(name = "title", length = 600)
    private String title;

    @Column(name = "is_required")
    private String isRequired;

    @Column(name = "remark", length = 600)
    private String remark;

    @OneToMany(mappedBy = "question")
    private Set<TpQuestionOptionEntity> tpQuestionOptions = new LinkedHashSet<>();

    public Set<TpQuestionOptionEntity> getTpQuestionOptions() {
        return tpQuestionOptions;
    }

    public void setTpQuestionOptions(Set<TpQuestionOptionEntity> tpQuestionOptions) {
        this.tpQuestionOptions = tpQuestionOptions;
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

    public TpModelEntity getModel() {
        return model;
    }

    public void setModel(TpModelEntity model) {
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
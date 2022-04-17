package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tp_model")
public class TpModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "memo", length = 500)
    private String memo;

    @OneToMany(mappedBy = "model")
    private Set<TpQuestionEntity> tpQuestions = new LinkedHashSet<>();

    public Set<TpQuestionEntity> getTpQuestions() {
        return tpQuestions;
    }

    public void setTpQuestions(Set<TpQuestionEntity> tpQuestions) {
        this.tpQuestions = tpQuestions;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
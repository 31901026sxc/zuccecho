package cn.edu.zucc.echo.entity;

import javax.persistence.*;

@Entity
@Table(name = "tp_question_option")
public class TpQuestionOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private TpQuestionEntity question;

    @Column(name = "title", length = 600)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TpQuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(TpQuestionEntity question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
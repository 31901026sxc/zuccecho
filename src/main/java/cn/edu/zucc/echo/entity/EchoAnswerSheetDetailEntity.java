package cn.edu.zucc.echo.entity;

import javax.persistence.*;

@Entity
@Table(name = "echo_answer_sheet_detail")
public class EchoAnswerSheetDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sheet_sid")
    private EchoAnswerSheetEntity sheet;


    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_category")
    private String questionCategory;

    @Column(name = "answer_content", length = 2048)
    private String answerContent;

    @Column(name = "answer_content_view")
    private String answerContentView;

    @Column(name = "memo")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private EchoQuestionEntity question;

/*    public EchoQuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(EchoQuestionEntity question) {
        this.question = question;
    }*/

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAnswerContentView() {
        return answerContentView;
    }

    public void setAnswerContentView(String answerContentView) {
        this.answerContentView = answerContentView;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public EchoQuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(EchoQuestionEntity question) {
        this.question = question;
    }

    public EchoAnswerSheetEntity getSheet() {
        return sheet;
    }

    public void setSheet(EchoAnswerSheetEntity sheet) {
        this.sheet = sheet;
    }

    public Integer getsid() {
        return sid;
    }

    public void setsid(Integer sid) {
        this.sid = sid;
    }
}
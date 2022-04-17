package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "basic_class_student")
public class BasicClassStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_sid")
    private BasicClassEntity _class;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_sid")
    private BasicUserEntity student;

    @Column(name = "create_time")
    private Instant createTime;

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public BasicUserEntity getStudent() {
        return student;
    }

    public void setStudent(BasicUserEntity student) {
        this.student = student;
    }

    public BasicClassEntity get_class() {
        return _class;
    }

    public void set_class(BasicClassEntity _class) {
        this._class = _class;
    }

    public Integer getsid() {
        return sid;
    }

    public void setsid(Integer sid) {
        this.sid = sid;
    }
}
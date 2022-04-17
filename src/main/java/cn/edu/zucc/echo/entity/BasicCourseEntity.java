package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "basic_course")
public class BasicCourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "modify_time")
    private Instant modifyTime;

    public Instant getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Instant modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getsid() {
        return sid;
    }

    public void setsid(Integer sid) {
        this.sid = sid;
    }

}
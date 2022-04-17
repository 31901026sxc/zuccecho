package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "basic_class")
public class BasicClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_sid")
    private BasicCourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_sid")
    private BasicUserEntity teacher;

    @Column(name = "year")
    private Integer year;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "is_close")
    private Integer isClose;

    @Column(name = "create_time")
    private Instant createTime;

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BasicUserEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(BasicUserEntity teacher) {
        this.teacher = teacher;
    }

    public BasicCourseEntity getCourse() {
        return course;
    }

    public void setCourse(BasicCourseEntity course) {
        this.course = course;
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
package cn.edu.zucc.echo.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "basic_user")
public class BasicUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @Column(name = "user_code", length = 20)
    private String userCode;

    @Column(name = "name")
    private String name;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "modify_time")
    private Instant modifyTime;

    @Column(name = "user_type")
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getsid() {
        return sid;
    }

    public void setsid(Integer sid) {
        this.sid = sid;
    }

}
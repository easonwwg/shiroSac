package com.sac.pojo.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by EAISON on 2017/9/28.
 */
public class User implements Serializable {

    private Long id;

    private  String nickName;

    private  String email;

    /**
     * 使用transient 不参加序列化过程
     */
    private  transient String pswd;

    private Date createTime;

    private Date lastLoginTime;

    /**
     * 一个用户有多个角色
     */
    private List<Role> roles;


    /**
     * 1有效
     * 0禁止登陆
     */
    private  int status;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

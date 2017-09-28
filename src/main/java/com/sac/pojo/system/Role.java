package com.sac.pojo.system;

import java.io.Serializable;

/**
 * Created by EAISON on 2017/9/28.
 */
public class Role implements Serializable {

    private Long id;
    /**角色名称*/
    private String name;
    /**角色与组织机构挂钩*/
    private String orgnazationCode;

    /**
     * 一个角色对应多资源权限
     *//*
    private List<Resource> resources;


    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgnazationCode() {
        return orgnazationCode;
    }

    public void setOrgnazationCode(String orgnazationCode) {
        this.orgnazationCode = orgnazationCode;
    }
}

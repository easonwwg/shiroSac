package com.sac.pojo.system;

/**
 * Created by EAISON on 2017/9/28.
 */
public class Resource {

    private int id;
    private  String name;
    private  String code;
    private  String url;

    public Resource(){}

    public Resource(int id, String name, String code, String url) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

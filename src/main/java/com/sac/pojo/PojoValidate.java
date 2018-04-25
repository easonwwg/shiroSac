package com.sac.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author:eason
 * @Description
 * @Date: 20:35,2018/4/25
 * @ModifiedBy
 */
public class PojoValidate {

    @Size(min = 3, max = 9, message = "最大长度为9，最小为3")
    private String userName;

    @Min(value = 40, message = "最小40")
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

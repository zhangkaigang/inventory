package com.inventory.entity;


import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

/**
 * @author zkaigang
 */
public class UserDTO {
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String loginName;
    private String mobile;
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "图片验证码不能为空，请您先填写验证码")
    @MatchPattern(pattern = "\\w{4}$", message = "图片验证码有误，请您重新填写")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

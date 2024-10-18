package com.shadsluiter.ordersapp.models;

// UserModel is used in the controller.  Translated to UserEntity in the service layer.
public class UserModel {

    private String id;
    private String loginName;
    private String password;

    public UserModel() {
    }

    public UserModel(String id, String loginName, String password) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

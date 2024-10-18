package com.shadsluiter.ordersapp.models;

import jakarta.persistence.*;
    
// this app uses jpa (Java Persistence API) to interact with the database. No SQL statements are used in the code.
// the @Entity annotation is used to mark this class as the interface between the database and the application. Property names are mapped to column names in the database.
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginName;

    @Column(nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

package com.shadsluiter.ordersapp.models; 

import jakarta.persistence.*; 
import java.util.Date;

// this app uses jpa (Java Persistence API) to interact with the database. No SQL statements are used in the code.
// the @Entity annotation is used to mark this class as the interface between the database and the application. Property names are mapped to column names in the database.
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long customerid;

    @Column(nullable = false)
    private String notes;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
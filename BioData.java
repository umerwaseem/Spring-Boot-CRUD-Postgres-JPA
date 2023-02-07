package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "biotable")
public class BioData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    public BioData() {

    }

    public BioData(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Data [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }
}

package com.myApp.LibraryManagementSystem.Entities;

import jakarta.persistence.*;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rollId;

    @Column(nullable = false)
    private String name;

    private String branch;

    @Column(nullable = false)
    private Double cgpa;

    @Column(unique = true)
    private String email;


    public Student() {
    }

    public Student(int rollId, String name, String branch, double cgpa, String email) {
        this.rollId = rollId;
        this.name = name;
        this.branch = branch;
        this.cgpa = cgpa;
        this.email = email;
    }

    public int getRollId() {
        return rollId;
    }

    public void setRollId(int rollId) {
        this.rollId = rollId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

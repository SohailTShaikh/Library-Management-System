package com.myApp.LibraryManagementSystem.Entities;


import com.myApp.LibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;


@Entity
@Table
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private Integer noOfBooksIssued;
    private Date validity;

    @JoinColumn
    @OneToOne
    private Student student;
    public LibraryCard() {
    }

    public LibraryCard(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LibraryCard(int cardNo, CardStatus cardStatus, int noOfBooksIssued, Date validity) {
        this.cardNo = cardNo;
        this.cardStatus = cardStatus;
        this.noOfBooksIssued = noOfBooksIssued;
        this.validity = validity;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getNoOfBooksIssued() {
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(int noOfBooksIssued) {
        this.noOfBooksIssued = noOfBooksIssued;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

}

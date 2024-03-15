package com.myApp.LibraryManagementSystem.Services;


import com.myApp.LibraryManagementSystem.Enums.CardStatus;
import com.myApp.LibraryManagementSystem.Entities.LibraryCard;
import com.myApp.LibraryManagementSystem.Entities.Student;
import com.myApp.LibraryManagementSystem.Repositories.CardRepository;
import com.myApp.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepository studentRepository;
    public String generateCard(){
        LibraryCard card=new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card.setNoOfBooksIssued(0);
        Date expiryDate=new Date(128,6,1);
        card.setValidity(expiryDate);
        card=cardRepository.save(card);

        return "The card has been generated with cardId "+card.getCardNo();
    }
    public String associateCardAndStudent(Integer cardId,Integer studentId){
        LibraryCard libraryCard=cardRepository.findById(cardId).get();
        Student student=studentRepository.findById(studentId).get();
        libraryCard.setCardStatus(CardStatus.ISSUED);
        libraryCard.setStudent(student);
        cardRepository.save(libraryCard);

        return "The card and student has been associated";
    }
}

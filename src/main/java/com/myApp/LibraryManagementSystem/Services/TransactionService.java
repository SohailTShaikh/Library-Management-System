package com.myApp.LibraryManagementSystem.Services;


import com.myApp.LibraryManagementSystem.Entities.Book;
import com.myApp.LibraryManagementSystem.Entities.LibraryCard;
import com.myApp.LibraryManagementSystem.Entities.Transaction;
import com.myApp.LibraryManagementSystem.Enums.TransactionStatus;
import com.myApp.LibraryManagementSystem.Repositories.BookRepository;
import com.myApp.LibraryManagementSystem.Repositories.CardRepository;
import com.myApp.LibraryManagementSystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static final Integer NAX_NO_OF_BOOKS_ISSUED=3;

    public static final Integer FINE_PER_DAY=5;

    public String issueBook(Integer cardId,Integer bookId) throws Exception{

        //Book book =bookRepository.findById(bookId).get();

        Optional<Book> bookOptional=bookRepository.findById(bookId);

        if(bookOptional.isEmpty()){
              throw new Exception("BookId entered is incorrect");
          }

        Book book=bookOptional.get();

        //LibraryCard card=cardRepository.findById(cardId).get();

        Optional<LibraryCard> libraryCardOptional=cardRepository.findById(cardId);

        if(libraryCardOptional.isEmpty()){
            throw new Exception("CardId entered is incorrect");
        }

        LibraryCard card=libraryCardOptional.get();

        Transaction transaction=new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        if (book.getIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return ("Book is already issued to cardId "+card.getCardNo());
        }

        if (card.getNoOfBooksIssued()>NAX_NO_OF_BOOKS_ISSUED){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Max limit of this card has exceeded";
        }

        Long timeInMsOfCardValidity=card.getValidity().getTime();
        Long currentTimeInMs=System.currentTimeMillis();
        if(currentTimeInMs>timeInMsOfCardValidity){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Card is expired";
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        cardRepository.save(card);
        bookRepository.save(book);
        transaction=transactionRepository.save(transaction);

        return "The transaction has been completed with transactionId"+transaction.getTransactionId();
    }


    public String returnBook(Integer cardId,Integer bookId){

        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();

         Transaction transaction=transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);


         Long timeDifferenceInMs=System.currentTimeMillis()-transaction.getIssueDate().getTime();

         Long days= TimeUnit.DAYS.convert(timeDifferenceInMs,TimeUnit.MILLISECONDS);

         Integer fineAmt=0;

         if (days>15){
             fineAmt=Math.toIntExact((days-15)*FINE_PER_DAY);
         }

         transaction.setFineAmount(fineAmt);
         transaction.setTransactionStatus(TransactionStatus.COMPLETED);
         transaction.setReturnDate(new Date());
         book.setIssued(Boolean.FALSE);
         card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        transactionRepository.save(transaction);
        bookRepository.save(book);
        cardRepository.save(card);

        return "Book has been successfully returned";
    }

}

package com.myApp.LibraryManagementSystem.Controllers;


import com.myApp.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PutMapping("/issueBook")
    public String issueBook(@RequestParam("cardId") Integer cardId,@RequestParam("bookId") Integer bookId){
        try{
           String result= transactionService.issueBook(cardId,bookId);
           return result;
       }
       catch (Exception e){
           return e.getMessage();
       }
    }
    @PutMapping("/return")
    public String returnBook(@RequestParam("cardId") Integer cardId,@RequestParam("bookId") Integer bookId){
          String result=transactionService.returnBook(cardId, bookId);
          return result;
    }
}

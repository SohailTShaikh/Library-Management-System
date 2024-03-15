package com.myApp.LibraryManagementSystem.Repositories;

import com.myApp.LibraryManagementSystem.Entities.Book;
import com.myApp.LibraryManagementSystem.Entities.LibraryCard;
import com.myApp.LibraryManagementSystem.Entities.Transaction;
import com.myApp.LibraryManagementSystem.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {

    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}

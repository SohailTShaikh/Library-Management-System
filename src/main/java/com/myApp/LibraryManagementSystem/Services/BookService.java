package com.myApp.LibraryManagementSystem.Services;


import com.myApp.LibraryManagementSystem.Entities.Author;
import com.myApp.LibraryManagementSystem.Entities.Book;
import com.myApp.LibraryManagementSystem.Exceptions.InvalidPageCountException;
import com.myApp.LibraryManagementSystem.Repositories.AuthorRepository;
import com.myApp.LibraryManagementSystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book) throws Exception{

        if (book.getNoOfPages()<=0){
            throw new InvalidPageCountException("Page count entered is incorrect");
        }

        book.setIssued(Boolean.FALSE);
        bookRepository.save(book);
        return "Book has been saved to the db";
    }

    public String associateBookAndAuthor(Integer bookId,Integer authorId) throws Exception{


        Optional<Book> bookOptional=bookRepository.findById(bookId);

        if(bookOptional.isEmpty()){
            throw new Exception("BookId entered is incorrect");
        }

        Book book=bookOptional.get();


        //Book book=bookRepository.findById(bookId).get();



        Optional<Author> optionalAuthor=authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty()){
            throw new Exception("AuthorId entered is incorrect");
        }

        Author author=optionalAuthor.get();

        //Author author=authorRepository.findById(authorId).get();

        book.setAuthor(author);
        author.setNoOfBooks(author.getNoOfBooks()+1);

        bookRepository.save(book);
        authorRepository.save(author);
        return "Book and Author have been associated";
    }

    public List<Book> findBooksByAuthor(Integer authorId){
        List<Book> allBooks=bookRepository.findAll();

        List<Book> ansList=new ArrayList<>();

        for (Book book:allBooks){
            if(book.getAuthor().getAuthorId().equals(authorId)){
                ansList.add(book);
            }
        }
        return ansList;
    }
}

package com.myApp.LibraryManagementSystem.Services;


import com.myApp.LibraryManagementSystem.Entities.Author;
import com.myApp.LibraryManagementSystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {


    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author){
        author.setNoOfBooks(0);
        authorRepository.save(author);
        return "Author has been saved to the db";
    }

    public Author getAuthorWithMaxBooks(){
        List<Author> authorList=authorRepository.findAll();
        Author ansAuthor=null;
        int maxBooks=0;

        for (Author author:authorList){
            if(author.getNoOfBooks()>maxBooks){
                maxBooks=author.getNoOfBooks();
                ansAuthor=author;
            }
        }
        return ansAuthor;
    }
}

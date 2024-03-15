package com.myApp.LibraryManagementSystem.Controllers;


import com.myApp.LibraryManagementSystem.Entities.Author;
import com.myApp.LibraryManagementSystem.Entities.Book;
import com.myApp.LibraryManagementSystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @PostMapping("add")
    public String addAuthor(@RequestBody Author author){
        String result=authorService.addAuthor(author);
        return result;
    }
    @GetMapping("/getAuthorWithMaxBooks")
    public Author getAuthor(){
        Author ansAuthor=authorService.getAuthorWithMaxBooks();
        return ansAuthor;
    }
}

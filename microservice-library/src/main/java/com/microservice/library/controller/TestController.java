package com.microservice.library.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library/method")
public class TestController {

    @DeleteMapping("/delete")
    public String callDelete(){
        return "Method Called With DELETE";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student")
    public String callStudent(){
        return "Method Called With Student";
    }

    @PreAuthorize("hasRole('DIRECTOR')")
    @GetMapping("/director")
    public String callDirector(){
        return "Method Called With Director";
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/librarian")
    public String callLibrarian(){
        return "Method Called With Librarian";
    }
}
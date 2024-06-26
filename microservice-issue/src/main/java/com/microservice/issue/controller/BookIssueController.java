package com.microservice.issue.controller;

import com.microservice.issue.service.mapped.BookIssueService;
import com.microservice.issue.util.dto.BookIssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book-issue")
public class BookIssueController {
    @Autowired
    private BookIssueService bookIssueService;

    @GetMapping("/search/{issue}")
    public ResponseEntity<Map<String, List<String>>> searchBookByIssue(@PathVariable String issue){
        return new ResponseEntity<>(bookIssueService.searchBookByIndexByStream(issue), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createBook(@RequestBody BookIssueDTO book){
        try {
            return new ResponseEntity<>(bookIssueService.createEntity(book), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

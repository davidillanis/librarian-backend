package com.microservice.library.util.http.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookIssueRequest {
    private String tituLibr;
    private boolean librHabi;
    private String isbnLibr;
    private String descLibr;
    private LocalDate fechPublLibr;
    private String editLibr;
    private String autoLibr;
    private List<String> listIssue;
}

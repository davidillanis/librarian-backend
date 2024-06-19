package com.microservice.library.client;

import com.microservice.library.util.dto.BookIssueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-issue", url = "localhost:8082/api/book-issue")
public interface BookIssueClient {

    @PostMapping("/create")
    Boolean createBookIssue(@RequestBody BookIssueDTO bookIssueDTO) ;
}

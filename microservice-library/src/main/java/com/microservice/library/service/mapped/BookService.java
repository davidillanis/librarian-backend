package com.microservice.library.service.mapped;

import com.microservice.library.model.entity.BookEntity;
import com.microservice.library.model.entity.CopyBookEntity;
import com.microservice.library.util.http.request.BookIssueRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {
    //create, read, update, delete
    boolean createEntity(BookEntity obj);
    List<BookEntity> getListEntity();
    BookEntity getEntity(Integer id);
    boolean updateEntity(BookEntity obj);
    boolean deleteEntity(Integer id);
    Optional<BookEntity> findBookEntityByIsbnLibr(String isbn);

    //others
    Boolean createBookIssue(BookIssueRequest obj);
    List<BookEntity> getListPopularCopyBook();
    CopyBookEntity searchCopyBookAvailable(String isbn);
}

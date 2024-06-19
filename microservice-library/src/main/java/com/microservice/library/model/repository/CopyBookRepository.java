package com.microservice.library.model.repository;

import com.microservice.library.model.entity.CopyBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyBookRepository extends JpaRepository<CopyBookEntity, String> {
}

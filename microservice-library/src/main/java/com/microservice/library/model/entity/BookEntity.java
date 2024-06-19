package com.microservice.library.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibr;

    @Column(length = 95, nullable = false)
    private String tituLibr;

    @Column(columnDefinition = "CHAR(13)", nullable = false, unique = true)
    private String isbnLibr;

    @Column(length = 95)
    private String descLibr;

    @Column(nullable = false)
    private LocalDate fechPublLibr;

    @Column(nullable = false, length = 45)
    private String editLibr;

    @Column(nullable = false, length = 45)
    private String autoLibr;

    @OneToMany(targetEntity = CopyBookEntity.class, cascade = CascadeType.ALL, mappedBy = "bookEntity")
    private List<CopyBookEntity> copyBookEntities;
}

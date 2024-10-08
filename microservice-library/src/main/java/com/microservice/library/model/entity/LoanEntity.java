package com.microservice.library.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrest;

    @Column(nullable = false)
    private LocalDate fechPres;

    @Column(nullable = false)
    private LocalDate fechDevoPres;

    @Column(nullable = true, length = 95)
    private String obsePres;

    @Column(nullable = false)
    private int estaPres;

    @ManyToOne
    @JoinColumn(name = "codiEjem", nullable = false)
    private CopyBookEntity copyBookEntity;

    @ManyToOne
    @JoinColumn(name = "idBibl", nullable = false)
    private LibrarianEntity librarianEntity;

    @ManyToOne
    @JoinColumn(name = "idAlumn", nullable = false)
    private StudentEntity studentEntity;
}

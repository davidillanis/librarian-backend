package com.microservice.library.model.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlumn;

    @Column(nullable = false)
    private LocalDate fechRegiAlum;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsua", nullable = false)
    private UserEntity userEntity;

    @OneToMany(targetEntity = RequestEntity.class, cascade = CascadeType.ALL, mappedBy = "studentEntity")
    private List<RequestEntity> requestEntities;

    @OneToMany(targetEntity = LoanEntity.class, cascade = CascadeType.ALL, mappedBy = "studentEntity")
    private List<LoanEntity> loanEntities;
}


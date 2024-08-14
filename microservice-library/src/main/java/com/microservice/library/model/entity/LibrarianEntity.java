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
public class LibrarianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBibl;

    @Column(nullable = false)
    private LocalDate fechContBibl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsua", nullable = false)
    private UserEntity userEntity;

    @OneToMany(targetEntity = LoanEntity.class, cascade = CascadeType.ALL, mappedBy = "librarianEntity")
    private List<LoanEntity> loanEntities;
}

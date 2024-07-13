package com.microservice.library.model.entity;

import com.microservice.library.util.other.ERole;
import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Rol")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false, unique = true, length = 50)
    @Column(columnDefinition = "CHAR(25)", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;
}

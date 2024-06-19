package com.microservice.library.model.repository;

import com.microservice.library.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //@Query("SELECT u FROM UserEntity u WHERE u.username=?")
    Optional<UserEntity> findUserEntityByUsername(String username);
}

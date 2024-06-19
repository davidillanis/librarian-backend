package com.microservice.library.service.mapped;


import com.microservice.library.model.entity.LibrarianEntity;
import com.microservice.library.model.entity.StudentEntity;
import com.microservice.library.util.dto.AuthCreateUserRequestDTO;
import com.microservice.library.util.dto.AuthResponseDTO;

public interface UserRoleService {
    //create, read, update, delete
    AuthResponseDTO createEntity(StudentEntity obj);
    AuthResponseDTO createEntity(LibrarianEntity obj);
    AuthResponseDTO createEntity(AuthCreateUserRequestDTO obj);
    StudentEntity getStudentById(Integer id);
    LibrarianEntity getLibrarianById(Integer id);

    /*
    boolean createEntityAll(Iterable<UserEntity> objIterable);
    List<UserEntity> getListEntity();
    UserEntity getEntity(Integer id);
    boolean updateEntity(UserEntity obj);
    boolean deleteEntity(Integer id);*/
}

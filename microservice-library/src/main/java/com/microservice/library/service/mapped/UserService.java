package com.microservice.library.service.mapped;


import com.microservice.library.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    //create, read, update, delete
    boolean createEntity(UserEntity obj);
    boolean createEntityAll(Iterable<UserEntity> objIterable);
    List<UserEntity> getListEntity();
    UserEntity getEntity(Integer id);
    boolean updateEntity(UserEntity obj);
    boolean deleteEntity(Integer id);
}

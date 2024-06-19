package com.microservice.library.service.mapped.implementation;

import com.microservice.library.model.entity.UserEntity;
import com.microservice.library.model.repository.UserRepository;
import com.microservice.library.service.mapped.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createEntity(UserEntity obj) {
        if(obj!=null){
            userRepository.save(obj);
            return true;
        }
        return false;
    }

    @Override
    public boolean createEntityAll(Iterable<UserEntity> objIterable) {
        if(objIterable!=null){
            userRepository.saveAll(objIterable);
            return true;
        }
        return false;
    }

    @Override
    public List<UserEntity> getListEntity() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getEntity(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateEntity(UserEntity obj) {
        if(obj!=null){
            /*.username("santiago")
					.password("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.roles(Set.of(roleStudent))*/
            UserEntity userEntity = userRepository.findById(obj.getIdUsua()).orElse(null);
            if(userEntity!=null){
                userEntity.setUsername(obj.getUsername());
                userEntity.setPassword(obj.getPassword());
                userEntity.setEnabled(obj.isEnabled());
                userEntity.setRoles(obj.getRoles());
                userRepository.save(userEntity);
            }
        }
        return false;
    }

    @Override
    public boolean deleteEntity(Integer id) {
        if(id!=null && userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

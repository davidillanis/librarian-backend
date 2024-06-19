package com.microservice.library.service.mapped.implementation;

import com.microservice.library.configuration.other.UserDetailServiceImpl;
import com.microservice.library.model.entity.LibrarianEntity;
import com.microservice.library.model.entity.StudentEntity;
import com.microservice.library.model.entity.UserEntity;
import com.microservice.library.model.repository.LibrarianRepository;
import com.microservice.library.model.repository.StudentRepository;
import com.microservice.library.model.repository.UserRepository;
import com.microservice.library.service.mapped.UserRoleService;
import com.microservice.library.service.other.UtilsServiceImpl;
import com.microservice.library.util.dto.AuthCreateUserRequestDTO;
import com.microservice.library.util.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UtilsServiceImpl utilsService;

    @Override
    public AuthResponseDTO createEntity(StudentEntity obj) {
        UserEntity entityStudent = obj.getUserEntity();
        if(entityStudent != null){
            AuthCreateUserRequestDTO userRequestDTO=new AuthCreateUserRequestDTO(
                    entityStudent.getUsername(),
                    entityStudent.getPassword(),
                    entityStudent.getNombUsua(),
                    entityStudent.getApelPaternoUsua(),
                    entityStudent.getApelMaternoUsua(),
                    entityStudent.getDNIUsua(),
                    entityStudent.getTeleUsua(),
                    entityStudent.getRoles().stream().map(t->t.getRole().name()).collect(Collectors.toList())
            );

            AuthResponseDTO authResponseDTO = userDetailService.createUser(userRequestDTO);
            obj.setUserEntity(userRepository.findUserEntityByUsername(entityStudent.getUsername()).orElse(null));
            studentRepository.save(obj);
            return authResponseDTO;
        }

        return null;
    }

    @Override
    public AuthResponseDTO createEntity(LibrarianEntity obj) {
        UserEntity entityLibrarian = obj.getUserEntity();

        if(entityLibrarian != null){
            AuthCreateUserRequestDTO userRequestDTO=new AuthCreateUserRequestDTO(
                    entityLibrarian.getUsername(),
                    entityLibrarian.getPassword(),
                    entityLibrarian.getNombUsua(),
                    entityLibrarian.getApelPaternoUsua(),
                    entityLibrarian.getApelPaternoUsua(),
                    entityLibrarian.getDNIUsua(),
                    entityLibrarian.getTeleUsua(),
                    entityLibrarian.getRoles().stream().map(t->t.getRole().name()).collect(Collectors.toList())
            );

            AuthResponseDTO authResponseDTO = userDetailService.createUser(userRequestDTO);
            obj.setUserEntity(userRepository.findUserEntityByUsername(entityLibrarian.getUsername()).orElse(null));
            librarianRepository.save(obj);
            return authResponseDTO;
        }

        return null;
    }

    @Override
    public AuthResponseDTO createEntity(AuthCreateUserRequestDTO obj){
        AuthResponseDTO authResponseDTO=userDetailService.createUser(obj);
        UserEntity userEntity=userRepository.findUserEntityByUsername(obj.username()).orElse(null);
        userEntity.getRoles().stream().forEach(roleEntity-> {
            switch (roleEntity.getRole()){
                case LIBRARIAN -> librarianRepository.save(new LibrarianEntity(0, utilsService.localDatePeru(), userEntity, null));
                case STUDENT -> studentRepository.save(new StudentEntity(0, utilsService.localDatePeru(), userEntity, null, null));
            }
        });
        return authResponseDTO;
    }

    @Override
    public StudentEntity getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public LibrarianEntity getLibrarianById(Integer id) {
        return librarianRepository.findById(id).orElse(null);
    }
}

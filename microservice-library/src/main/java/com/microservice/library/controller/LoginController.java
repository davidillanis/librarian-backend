package com.microservice.library.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.microservice.library.configuration.jwt.JwtUtils;
import com.microservice.library.configuration.other.UserDetailServiceImpl;
import com.microservice.library.model.entity.*;
import com.microservice.library.model.repository.UserRepository;
import com.microservice.library.service.mapped.UserRoleService;
import com.microservice.library.service.other.UtilsServiceImpl;
import com.microservice.library.util.dto.AuthCreateUserRequestDTO;
import com.microservice.library.util.dto.AuthLoginRequestDTO;
import com.microservice.library.util.dto.AuthResponseDTO;
import com.microservice.library.util.other.ERole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/library/auth")
@PreAuthorize("permitAll()")
public class LoginController {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping ("/listUser")
    public ResponseEntity<List<Map<String, Object>>> listUsers(){
        List<Map<String, Object>> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        users=userEntities.stream().map(entity->{
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("id",entity.getIdUsua());
                    map.put("username",entity.getUsername());
                    map.put("enabled", entity.isEnabled());
                    map.put("roles", entity.getRoles().stream().map(role->role.getRole().name()).collect(Collectors.toList()));

                    return map;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/searchByUsername/{username}")
    public ResponseEntity<Map<String, Object>> search(@PathVariable String username){
        UserEntity userEntity=userRepository.findUserEntityByUsername(username).orElse(null);
        Map<String, Object> map = new LinkedHashMap<>();

        if(userEntity!=null){
            map.put("id",userEntity.getIdUsua());
            map.put("username",userEntity.getUsername());
            map.put("enabled", userEntity.isEnabled());
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        map.put("error","not exist");
        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/validToken")
    public ResponseEntity<Map<String, Object>> validToken(@RequestParam String token){
        Map<String, Object> map = new HashMap<>();

        try {
			jwtUtils.validateToken(token);
            map.put("valid",true);

		}catch (JWTVerificationException e){
            map.put("valid",false);
		}catch (Exception ex){
            map.put("valid",false);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/role")//DIRECTOR, LIBRARIAN, STUDENT
    public ResponseEntity<Map<String, Object>> getRoleByToken(@RequestParam String token) {
        Map<String, Object> map=new HashMap<>();
        try {
            map.put("ROLE", jwtUtils.findRoleByToken(token));
            map.put("status","successful");
        }catch (Exception e){
            map.put("ROLE", "");
            map.put("status",e.toString());
        }
        return ResponseEntity.ok(map);
    }

    @PostMapping("/create-user")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid AuthCreateUserRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/disableAccount/username={username}/isEnabled={isEnabled}")
    public ResponseEntity<Boolean> deleteByUsername(@PathVariable String username, @PathVariable boolean isEnabled){
        UserEntity userEntity=userRepository.findUserEntityByUsername(username).orElse(null);
        if(userEntity!=null){
            userEntity.setEnabled(isEnabled);
            this.userRepository.save(userEntity);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> registerTest(@RequestBody @Valid AuthCreateUserRequestDTO userRequest) {
        return new ResponseEntity<>(userRoleService.createEntity(userRequest), HttpStatus.CREATED);
    }

}
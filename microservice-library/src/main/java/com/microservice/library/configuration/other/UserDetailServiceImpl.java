package com.microservice.library.configuration.other;

import com.microservice.library.configuration.jwt.JwtUtils;
import com.microservice.library.model.entity.RoleEntity;
import com.microservice.library.model.entity.UserEntity;
import com.microservice.library.model.repository.RoleRepository;
import com.microservice.library.model.repository.UserRepository;
import com.microservice.library.util.dto.AuthCreateUserRequestDTO;
import com.microservice.library.util.dto.AuthLoginRequestDTO;
import com.microservice.library.util.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username).orElseThrow(() -> new UsernameNotFoundException("this " + username + " no exits."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(), true, true, true, authorityList);
    }


    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequest) {
        String accessToken=null;
        String message=null;
        boolean status=false;
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        if(userRepository.findUserEntityByUsername(username).orElse(null).isEnabled()){
            Authentication authentication = this.authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            accessToken = jwtUtils.createToken(authentication);
            message="You have been logged in successfully";
            status=true;
        }else{
            accessToken=null;
            message="account disable";
            status=false;
        }
        AuthResponseDTO authResponse = new AuthResponseDTO(username, message, accessToken, status);
        return authResponse;
    }

    public AuthResponseDTO createUser(AuthCreateUserRequestDTO createRoleRequest) {
        String username = createRoleRequest.username();
        String password = createRoleRequest.password();
        List<String> rolesRequest = createRoleRequest.roleListName();
        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleIn(rolesRequest).stream().collect(Collectors.toSet());

        if(!roleEntityList.isEmpty()) {
            UserEntity userEntity = UserEntity.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .nombUsua(createRoleRequest.usuaNomb())
                    .apelPaternoUsua(createRoleRequest.usuaApelPaterno())
                    .apelMaternoUsua(createRoleRequest.usuaApelMaterno())
                    .DNIUsua(createRoleRequest.usuaDNI())
                    .teleUsua(createRoleRequest.usuaTele())
                    .roles(roleEntityList)
                    .isEnabled(true).build();
            UserEntity userSaved = userRepository.save(userEntity);
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

            userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

            SecurityContext securityContextHolder = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

            String accessToken = jwtUtils.createToken(authentication);
            AuthResponseDTO authResponse = new AuthResponseDTO(username, "User created successfully", accessToken, true);
            return authResponse;
        }
        throw new IllegalArgumentException("The roles specified does not exist.");
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
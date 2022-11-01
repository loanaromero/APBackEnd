package com.portfolio.lr.security.controller;

import com.portfolio.lr.security.Dto.JwtDto;
import com.portfolio.lr.security.Dto.LoginUser;
import com.portfolio.lr.security.Dto.NewUser;
import com.portfolio.lr.security.entity.Rol;
import com.portfolio.lr.security.entity.user;
import com.portfolio.lr.security.enums.RolName;
import com.portfolio.lr.security.jwt.JwtProvider;
import com.portfolio.lr.security.service.RolService;
import com.portfolio.lr.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByNombreUsuario(newUser.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        user user = new user(newUser.getNombre(), newUser.getNombreUsuario(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        
        if(newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        
        user.setRoles(roles);
        userService.save(user);
        
        return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
       if(bindingResult.hasErrors())
           return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
       
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNombreUsuario(), loginUser.getPassword()));
       
       SecurityContextHolder.getContext().setAuthentication(authentication);
       
       String jwt = jwtProvider.generateToken(authentication);
       
       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       
       JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
       
       return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}


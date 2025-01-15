package com.example.gestion_residents.controller.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_residents.dto.AuthentificationRequest;
import com.example.gestion_residents.dto.AuthentificationResponce;
import com.example.gestion_residents.dto.SignupRequest;
import com.example.gestion_residents.dto.UserDto;
import com.example.gestion_residents.model.User;
import com.example.gestion_residents.repository.UserRepository;
import com.example.gestion_residents.service.AuthService;
import com.example.gestion_residents.service.jwt.UserService;
import com.example.gestion_residents.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private  UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
       try{
        UserDto createUser = authService.createUser(signupRequest);
        return new ResponseEntity<>(createUser, HttpStatus.OK);
       }catch(EntityExistsException e){
        return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
       }catch(Exception e){
        return new ResponseEntity<>("User creation failed", HttpStatus.BAD_REQUEST);
       }
    }  
    
    @PostMapping("/login")
    public AuthentificationResponce authenticateUser(@RequestBody AuthentificationRequest authentificationRequest) {
          try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getEmail(), authentificationRequest.getPassword()));
          }catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid credentials"); 
          }
          final UserDetails userDetalis = userService.userDetailsService().loadUserByUsername(authentificationRequest.getEmail());
          Optional<User> user = userRepository.findFirstByEmail(userDetalis.getUsername());
          final String jwt = jwtUtil.generateToken(userDetalis);
          AuthentificationResponce authentificationResponce = new AuthentificationResponce();
          if(user.isPresent()){
            authentificationResponce.setId(user.get().getId());
            authentificationResponce.setRole(user.get().getRole());
            authentificationResponce.setJwt(jwt);
          }
          return authentificationResponce;
            
    } 
    
        
    
}

package com.example.gestion_residents.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gestion_residents.dto.SignupRequest;
import com.example.gestion_residents.dto.UserDto;
import com.example.gestion_residents.model.Role;
import com.example.gestion_residents.model.User;
import com.example.gestion_residents.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @PostConstruct
    private void createAnAdminAccount() {
       Optional<User> admin = userRepository.findByRole(Role.ADMINISTRATEUR);
       if(admin.isEmpty()){
              User adminUser = new User();
              
              adminUser.setEmail("admin@test.com");
              adminUser.setNom("Admin");
              adminUser.setRole(Role.ADMINISTRATEUR);
              adminUser.setMotDePasse(new BCryptPasswordEncoder().encode("admin"));
                userRepository.save(adminUser);
              System.out.println("Admin account created");
                

       }else{
           System.out.println("Admin account already exists");
       }
           
    }

    public UserDto createUser(SignupRequest signupRequest) {
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User already exists");
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail()); 
        user.setNom(signupRequest.getNom());
        user.setMotDePasse(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(Role.RESIDENT);
        User userSaved =userRepository.save(user);
        return userSaved.getUserDto();
    }
    

}

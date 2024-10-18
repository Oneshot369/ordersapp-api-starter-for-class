package com.shadsluiter.ordersapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*; 
import com.shadsluiter.ordersapp.models.UserModel;
import com.shadsluiter.ordersapp.security.JwtTokenProvider;
import com.shadsluiter.ordersapp.service.UserService; 
import java.util.Map;
import java.util.HashMap; 


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsersController {

    @Autowired
    private UserService userService; 

    // no authentication manager yet
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
        if (userService.findByLoginName(user.getLoginName()) != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.save(user));
    }
    

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserModel loginRequest) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getLoginName(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtTokenProvider.generateToken(auth);

        Map<String, String> res = new HashMap<>();
        res.put("token", jwt);

        
        return ResponseEntity.ok(res);
    }
}


package com.gestmaint.api.controllers;

import com.gestmaint.api.dtos.UserDto;
import com.gestmaint.api.repositories.RoleRepository;
import com.gestmaint.api.requests.AuthenticationRequest;
import com.gestmaint.api.responses.AuthenticationResponse;
import com.gestmaint.api.services.UserService;
import com.gestmaint.api.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Username or password is incorrect");
        }

        final UserDetails user = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/test")
    public ResponseEntity<List<String>> test(){
        List<String> tab = new ArrayList<>();
        roleRepository.findAll().forEach(role -> tab.add(role.getName().name()));

        return ResponseEntity.ok(tab);
    }

}

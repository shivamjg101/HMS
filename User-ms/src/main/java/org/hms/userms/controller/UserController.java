package org.hms.userms.controller;

import jakarta.validation.Valid;
import org.hms.userms.dto.AdminRegisterDTO;
import org.hms.userms.dto.LoginDTO;
import org.hms.userms.dto.RegisterDTO;
import org.hms.userms.dto.RegistrationCountsDTO;
import org.hms.userms.dto.ResponseDTO;
import org.hms.userms.dto.Roles;
import org.hms.userms.exception.HmsException;
import org.hms.userms.jwt.JwtUtil;
import org.hms.userms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, UserDetailsService userDetailsService,
                          AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody RegisterDTO registerDTO) throws HmsException {
        userService.registerUser(registerDTO);
        return new ResponseEntity<>(new ResponseDTO("Account_Created"), HttpStatus.CREATED);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<ResponseDTO> registerAdmin(
            @Valid @RequestBody AdminRegisterDTO adminRegisterDTO,
            @RequestHeader(value = "X-User-Role", required = false) String requesterRole) throws HmsException {
        if (!Roles.ADMIN.name().equals(requesterRole)) {
            throw new HmsException("Access_Denied");
        }
        userService.registerAdmin(adminRegisterDTO);
        return new ResponseEntity<>(new ResponseDTO("Admin_Created"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> postMethodName(@Valid @RequestBody LoginDTO loginDTO) throws HmsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new HmsException("Invalid_Credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/getProfile/{id}")
    public ResponseEntity<Long> getProfile(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(userService.getProfile(id), HttpStatus.OK);
    }

    @GetMapping("/getRegistrationCounts")
    public ResponseEntity<RegistrationCountsDTO> getMonthlyRegistrationCounts() {
        return new ResponseEntity<>(
                userService.getMonthlyRegistrationCounts(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test", HttpStatus.OK);
    }

}

package net.rooban.springuserrolejwt.controller;

import lombok.RequiredArgsConstructor;
import net.rooban.springuserrolejwt.dao.JwtAuthenticationResponse;
import net.rooban.springuserrolejwt.dao.SigninRequest;
import net.rooban.springuserrolejwt.entity.User;
import net.rooban.springuserrolejwt.repository.UserRepository;
import net.rooban.springuserrolejwt.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomUserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public String home(){
        return "you are home <br/>" +
                "<a href='/admin'>admin</a></br/>" +
                "<a href='/user'>user</a></br/>" +
                "<a href='/'>home</a></br/>" +
                "<a href='/logout'>logout</a></br/>";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        return "admin <br/>" +
                "<a href='/'>home</a></br/>";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String user() {
        return "user <br/>" +
                "<a href='/'>home</a></br/>";
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }


    @PostMapping("/user/add")
    public User addUser(@RequestBody User user){
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

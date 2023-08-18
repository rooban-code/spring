package net.rooban.springuserrole.controller;

import net.rooban.springuserrole.entity.User;
import net.rooban.springuserrole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class CustomUserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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


    @PostMapping("/user/add")
    public User addUser(@RequestBody User user){
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

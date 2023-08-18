package net.rooban.springuserrole.service.impl;

import net.rooban.springuserrole.entity.User;
import net.rooban.springuserrole.repository.UserRepository;
import net.rooban.springuserrole.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}

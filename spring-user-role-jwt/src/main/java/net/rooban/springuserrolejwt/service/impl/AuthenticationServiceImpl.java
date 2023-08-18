package net.rooban.springuserrolejwt.service.impl;

import lombok.RequiredArgsConstructor;
import net.rooban.springuserrolejwt.dao.JwtAuthenticationResponse;
import net.rooban.springuserrolejwt.dao.SigninRequest;
import net.rooban.springuserrolejwt.repository.UserRepository;
import net.rooban.springuserrolejwt.service.AuthenticationService;
import net.rooban.springuserrolejwt.service.CustomUserDetails;
import net.rooban.springuserrolejwt.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
            var jwt = jwtService.generateToken(new CustomUserDetails(user));
            return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}

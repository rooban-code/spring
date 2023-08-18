package net.rooban.springuserrolejwt.service;

import net.rooban.springuserrolejwt.dao.JwtAuthenticationResponse;
import net.rooban.springuserrolejwt.dao.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signin(SigninRequest request);
}

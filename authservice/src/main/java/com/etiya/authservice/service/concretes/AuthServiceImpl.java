package com.etiya.authservice.service.concretes;

import com.etiya.authservice.AuthValidator;
import com.etiya.authservice.service.abstracts.AuthService;
import com.etiya.authservice.service.abstracts.UserService;
import com.etiya.authservice.service.dtos.LoginRequest;
import com.etiya.authservice.service.dtos.RegisterUserRequest;
import com.etiya.common.crosscuttingconcerns.exceptions.types.AuthException;
import com.etiya.common.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public void register(RegisterUserRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        // Email doğrulama
        if (email == null || email.isBlank()) {
            throw new AuthException("Email cannot be empty");
        }
        if (!email.matches(AuthValidator.EMAIL_REGEX)) {
            throw new AuthException("Invalid email format");
        }

        // Password doğrulama
        if (password == null || password.isBlank()) {
            throw new AuthException("Password cannot be empty");
        }
        if (!password.matches(AuthValidator.STRONG_PASSWORD_REGEX)) {
            throw new AuthException(
                    "Password must be at least 8 characters and include uppercase, lowercase, digit, and special character"
            );
        }

        userService.add(request);
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated()) {
            throw new AuthException("Invalid username and password");//RuntimeEx türü AuthanticateEx olacak.
        }
        UserDetails user = userService.loadUserByUsername(request.getEmail());
        return jwtService.generateToken(user.getUsername(),user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}

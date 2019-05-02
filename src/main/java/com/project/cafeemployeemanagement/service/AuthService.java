package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.exception.AppException;
import com.project.cafeemployeemanagement.model.Employee;
import com.project.cafeemployeemanagement.model.PasswordResetToken;
import com.project.cafeemployeemanagement.payload.LoginRequest;
import com.project.cafeemployeemanagement.repository.PasswordResetTokenRepository;
import com.project.cafeemployeemanagement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    public Authentication authenticateUser(LoginRequest loginRequest) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    public String getTokenFromAuthenticateUser(Authentication auth) {
        return tokenProvider.generateToken(auth);
    }

    public Authentication getAuthenticationFromAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getTokenFromAuthenticatedUser() {
        return tokenProvider.generateToken(getAuthenticationFromAuthenticatedUser());
    }

    public String validatePasswordResetToken(long id, String token) {
        final PasswordResetToken passwordResetToken = passwordTokenRepository.findByToken(token);

        if (passwordResetToken == null || passwordResetToken.getEmployee().getId() != id) {
            throw new AppException("Invalid token!");
        }

        final Calendar cal = Calendar.getInstance();
        if ((passwordResetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new AppException("Token is expired!");
        }

        final Employee user = passwordResetToken.getEmployee();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }

}

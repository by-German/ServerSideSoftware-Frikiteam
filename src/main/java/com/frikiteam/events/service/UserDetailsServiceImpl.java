package com.frikiteam.events.service;

import com.frikiteam.events.domain.repositories.UserRepository;
import com.frikiteam.events.domain.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements DefaultUserDetailsService {
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.frikiteam.events.domain.model.User user = userRepository.findByEmail(username);
        if (user != null) {
            String passwordEncode = passwordEncoder.encode(user.getPassword());
            return new User(user.getEmail(), passwordEncode, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException("User not found with username " + username);
    }

}
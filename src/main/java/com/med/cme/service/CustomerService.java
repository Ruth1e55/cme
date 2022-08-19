package com.med.cme.service;

import com.med.cme.model.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface CustomerService {

    String login(String username, String password);
    Optional<User> findByToken(String token);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    Customer findById(Long id);

}

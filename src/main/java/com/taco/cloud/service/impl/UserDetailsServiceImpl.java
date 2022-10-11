package com.taco.cloud.service.impl;

import com.taco.cloud.model.User;
import com.taco.cloud.repo.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private JpaUserRepository jpaUserRepository;

    @Autowired
    public UserDetailsServiceImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = jpaUserRepository.findByUsername(username);

        if(user != null)
            return user;

        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}

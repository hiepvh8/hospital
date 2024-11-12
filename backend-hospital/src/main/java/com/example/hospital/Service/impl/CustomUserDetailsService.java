package com.example.hospital.Service.impl;

import com.example.hospital.Entity.MyUserDetail;
import com.example.hospital.Entity.UserEntity;
import com.example.hospital.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = Arrays.asList(user.getRoles().split(" "));
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        MyUserDetail myUserDetail = new MyUserDetail(username,user.getPassword(),true , true,true,true,authorities);
        BeanUtils.copyProperties(user, myUserDetail);
        return myUserDetail;
    }
}
package com.blog.config;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.blog.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            Optional<com.blog.model.User> opt= userRepository.findByEmail(email);
            
            if(opt.isPresent()) {
            	
            	com.blog.model.User customer= opt.get();
                List<GrantedAuthority> authorities= new ArrayList<>(); // empty GrantedAuthority
                return new User(customer.getEmail(), customer.getPassword(), authorities);
                
            }else
            	
                throw new UsernameNotFoundException("User Details not found with this username: "+email);
        }



}

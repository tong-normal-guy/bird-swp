package com.example.birdReproductionManagement.security;

import com.example.birdReproductionManagement.entity.UserEntity;
import com.example.birdReproductionManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User Not Found!");
//        }
//
//        UserSecurity userSecurity = new UserSecurity(user);
//        return userSecurity;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }

//    private Collection<GrantedAuthority> mapRolesToAuthorities(Role roles){
//        return List.of(new SimpleGrantedAuthority(roles.name()));;
//    }
}

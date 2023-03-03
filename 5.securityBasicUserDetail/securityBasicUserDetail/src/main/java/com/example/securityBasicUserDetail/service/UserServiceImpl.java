package com.example.securityBasicUserDetail.service;

import com.example.securityBasicUserDetail.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.securityBasicUserDetail.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*
    * This method enables Registration of user with password encoding
    *
    */
    @Override
    public Integer saveUser(User user) {
        //Get the password assigned for the user while registration
        String passwd = user.getPassword();

        //encode registration password
        String encodedPasswd = passwordEncoder.encode(passwd);
        //Assign new value to the password(encoded)
        user.setPassword(encodedPasswd);
        //save the user in db
        user = userRepository.save(user);
        return user.getId();
    }

    /*
    * This method provides sign in
    */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findUserByEmail(email);
        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " + email + "not found!");
        else{
            User user = opt.get();
        }
        User user = opt.get();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role->new SimpleGrantedAuthority(role))
                        .collect(Collectors.toSet())
        );
    }
}

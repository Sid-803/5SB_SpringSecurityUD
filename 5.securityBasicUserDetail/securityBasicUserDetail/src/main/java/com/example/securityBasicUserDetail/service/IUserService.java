package com.example.securityBasicUserDetail.service;

import org.springframework.security.core.userdetails.User;

public interface IUserService {

    //public Integer saveUser(User user);

    Integer saveUser(com.example.securityBasicUserDetail.model.User user);
}

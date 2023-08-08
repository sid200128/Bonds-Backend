package com.bonds.backend.services;

import com.bonds.backend.models.User;
import com.bonds.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Boolean validateUser(String userName, String userRole) {
        List<User>userList = userRepository.findByUserNameAndUserRole(userName,userRole);
        return !userList.isEmpty();
    }
}

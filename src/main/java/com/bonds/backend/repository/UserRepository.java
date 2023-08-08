package com.bonds.backend.repository;

import com.bonds.backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findByUserNameAndUserRole(String userName,String userRole);
}

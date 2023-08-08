package com.bonds.backend.controller;

import com.bonds.backend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserLoginData{
    private String userName;
    private String userRole;
}

@RestController
public class UserController {


    @Autowired
    private UserService userService;


    // --------------------------------------------------------------------------------------
    //                                 VALIDATE_USER_BY_USERNAME_AND_ROLE
    // --------------------------------------------------------------------------------------
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST,value="/login")
    public Boolean validateUser(@RequestBody UserLoginData userLoginData){
        return userService.validateUser(userLoginData.getUserName(),userLoginData.getUserRole());
    }


}

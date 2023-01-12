package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.User;
import com.rodriguez.boardGameslibrary.client.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public LoginService(){}

    public User checkLogin(String username, String password){

        User user = userRepository.findByUsername(username);

        /*if(user!=null){
            if(isPassword(user.get(),password)){
                return user;
            }
        }*/

        return user;
    }

    private boolean isPassword(User user, String pass){
        return  user.getPassword().equals(pass);
    }

}

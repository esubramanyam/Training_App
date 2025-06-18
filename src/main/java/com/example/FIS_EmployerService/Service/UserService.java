package com.example.FIS_EmployerService.Service;

import com.example.FIS_EmployerService.Repository.UserRepo;
import com.example.FIS_EmployerService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user) {
        return userRepo.save(user);
    }
}

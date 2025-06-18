package com.example.FIS_EmployerService.Repository;

import com.example.FIS_EmployerService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}

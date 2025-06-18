package com.example.FIS_EmployerService.Repository;

import com.example.FIS_EmployerService.model.EmployerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepo extends JpaRepository<EmployerInfo , Integer> {
    EmployerInfo save(EmployerInfo empInfo);

    EmployerInfo findById(int employeeId);
}

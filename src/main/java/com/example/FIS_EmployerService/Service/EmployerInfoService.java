package com.example.FIS_EmployerService.Service;

import com.example.FIS_EmployerService.Repository.EmployerRepo;
import com.example.FIS_EmployerService.model.EmployerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerInfoService {

@Autowired
private EmployerRepo employerRepo;

    public void saveEmployerInfo(EmployerInfo employerInfo) {
        employerRepo.save(employerInfo);

    }

    public List<EmployerInfo> getEmployerInfo(int employeeIdentificationNumber) {
    return employerRepo.findById(employeeIdentificationNumber);
    }
}

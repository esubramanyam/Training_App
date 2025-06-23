package com.example.FIS_EmployerService.Controller;

import com.example.FIS_EmployerService.Service.EmployerInfoService;
import com.example.FIS_EmployerService.model.EmployerInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employer")
@PreAuthorize("hasAuthority('ADMIN')")
public class EmployerController {
@Autowired
private EmployerInfoService employerService;

@PostMapping("/saveEmployerInfo")

    public ResponseEntity saveEmployerInfo(@Valid @RequestBody EmployerInfo employerInfo, BindingResult result, Principal principal)
{

    if(result.hasErrors())
    {
        List<String> errorMsg = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorMsg);
    }

    if(getEmployerInfo(employerInfo.getEmployee_identification_number()) != null)
    {
        return ResponseEntity.ok("Employer Information Already Exists");
    }
    else {
        employerInfo.setPerformedBy(principal.getName());
        employerInfo= employerService.saveEmployerInfo(employerInfo);
        return ResponseEntity.ok("Employer Information Saved Successfully");

    }

}

@GetMapping("/getEmployerInfo/{employee_identification_number}")
@PostAuthorize("returnObject.performedBy==authentication.name")
    public EmployerInfo getEmployerInfo(@PathVariable int employee_identification_number )
{
    EmployerInfo employerInfo = employerService.getEmployerInfo(employee_identification_number);
    return employerInfo;
}

}

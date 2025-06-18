package com.example.FIS_EmployerService.model;

import com.example.FIS_EmployerService.Config.ValidateEmployeeIdNullorEmpty;
import com.example.FIS_EmployerService.Config.ValidationEmployerInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


@Entity
@Table(name="employee_dtls_tbl")
public class EmployerInfo {
    @Id

    @ValidateEmployeeIdNullorEmpty
    @ValidationEmployerInfo
    private int employee_identification_number;

    @NotEmpty(message = "Employer Company Name should not be empty")
    @NotNull(message = "Employer Company Name should not be null")
    @Size(max=32, message="Employer Company Name should not be greater than 32 character")
    private String employee_company_name;

    @Size(max=6, message="SIC be max 6 character")
    private String SIC;

    @NotEmpty(message = "Address should not be empty")
    @NotNull(message = "Address should not be null")
    @Size(max=40, message="Address should not be greater than 40 character")
    private String employee_addr_line_1;

    @Size(max=40, message="Address should not be greater than 40 character")
    private String employee_addr_line_2;

    @Size(max=40, message="Address should not be greater than 40 character")
    private String employee_addr_line_3;

    @Size(max=40, message="Address should not be greater than 40 character")
    private String employee_addr_line_4;

    @NotEmpty(message = "City should not be empty")
    @NotNull(message = "City should not be null")
    @Size(max=28, message="City should not be greater than 28 character")
    private String city;

    @NotEmpty(message = "State should not be empty")
    @NotNull(message = "State should not be null")
    private String state;

    @NotEmpty(message = "Zip Code should not be empty")
    @NotNull(message = "Zip Code should not be null")
    @Size(max=9, message="Zip Code should not be greater than 9 character")
    private String zip_code;

    private String PerformedBy;

    public String getPerformedBy() {
        return PerformedBy;
    }

    public void setPerformedBy(String performedBy) {
        PerformedBy = performedBy;
    }

    public int getEmployee_identification_number() {
        return employee_identification_number;
    }

    public void setEmployee_identification_number(int employee_identification_number) {
        this.employee_identification_number = employee_identification_number;
    }

    public String getEmployee_company_name() {
        return employee_company_name;
    }

    public void setEmployee_company_name(String employee_company_name) {
        this.employee_company_name = employee_company_name;
    }

    public String getSIC() {
        return SIC;
    }

    public void setSIC(String SIC) {
        this.SIC = SIC;
    }

    public String getEmployee_addr_line_1() {
        return employee_addr_line_1;
    }

    public void setEmployee_addr_line_1(String employee_addr_line_1) {
        this.employee_addr_line_1 = employee_addr_line_1;
    }

    public String getEmployee_addr_line_2() {
        return employee_addr_line_2;
    }

    public void setEmployee_addr_line_2(String employee_addr_line_2) {
        this.employee_addr_line_2 = employee_addr_line_2;
    }

    public String getEmployee_addr_line_3() {
        return employee_addr_line_3;
    }

    public void setEmployee_addr_line_3(String employee_addr_line_3) {
        this.employee_addr_line_3 = employee_addr_line_3;
    }

    public String getEmployee_addr_line_4() {
        return employee_addr_line_4;
    }

    public void setEmployee_addr_line_4(String employee_addr_line_4) {
        this.employee_addr_line_4 = employee_addr_line_4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    @Override
    public String toString() {
        return "EmployerInfo{" +
                "employee_identification_number=" + employee_identification_number +
                ", employee_company_name='" + employee_company_name + '\'' +
                ", SIC='" + SIC + '\'' +
                ", employee_addr_line_1='" + employee_addr_line_1 + '\'' +
                ", employee_addr_line_2='" + employee_addr_line_2 + '\'' +
                ", employee_addr_line_3='" + employee_addr_line_3 + '\'' +
                ", employee_addr_line_4='" + employee_addr_line_4 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", PerformedBy='" + PerformedBy + '\'' +
                '}';
    }
}

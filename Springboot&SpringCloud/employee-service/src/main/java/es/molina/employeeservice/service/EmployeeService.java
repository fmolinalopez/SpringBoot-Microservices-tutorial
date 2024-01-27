package es.molina.employeeservice.service;

import es.molina.employeeservice.dto.ApiResponseDto;
import es.molina.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    ApiResponseDto getEmployee(Long id);
}

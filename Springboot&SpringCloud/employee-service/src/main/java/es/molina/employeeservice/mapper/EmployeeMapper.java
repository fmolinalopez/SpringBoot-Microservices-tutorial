package es.molina.employeeservice.mapper;

import es.molina.employeeservice.dto.EmployeeDto;
import es.molina.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(),
                employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getDepartmentId());
    }

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(),
                employee.getLastName(), employee.getEmail(), employee.getDepartmentId());
    }
}

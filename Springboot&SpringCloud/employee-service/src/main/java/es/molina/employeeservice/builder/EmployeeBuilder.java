package es.molina.employeeservice.builder;

import es.molina.employeeservice.dto.EmployeeDto;
import es.molina.employeeservice.entity.Employee;

public class EmployeeBuilder {

    public static Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(),
                employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getDepartmentId());
    }

    public static EmployeeDto employeeToEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(),
                employee.getLastName(), employee.getEmail(), employee.getDepartmentId());
    }
}

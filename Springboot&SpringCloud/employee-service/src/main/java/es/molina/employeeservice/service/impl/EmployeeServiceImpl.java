package es.molina.employeeservice.service.impl;

import es.molina.employeeservice.builder.EmployeeBuilder;
import es.molina.employeeservice.dto.ApiResponseDto;
import es.molina.employeeservice.dto.DepartmentDto;
import es.molina.employeeservice.dto.EmployeeDto;
import es.molina.employeeservice.entity.Employee;
import es.molina.employeeservice.repository.EmployeeRepository;
import es.molina.employeeservice.service.APIClient;
import es.molina.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return EmployeeBuilder.employeeToEmployeeDto(
                employeeRepository.save(EmployeeBuilder.employeeDtoToEmployee(employeeDto)));
    }

    @Override
    public ApiResponseDto getEmployee(Long id) {
        EmployeeDto employeeDto = EmployeeBuilder.employeeToEmployeeDto(employeeRepository.findById(id).get());

        // RestTemplate
//        DepartmentDto departmentDto = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employeeDto.getDepartmentId(),
//                DepartmentDto.class).getBody();

        // WebClient
//        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" + employeeDto.getDepartmentId())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = apiClient.getDepartment(employeeDto.getDepartmentId());

        ApiResponseDto response = new ApiResponseDto();
        response.setEmployee(employeeDto);
        response.setDepartment(departmentDto);

        return response;
    }
}

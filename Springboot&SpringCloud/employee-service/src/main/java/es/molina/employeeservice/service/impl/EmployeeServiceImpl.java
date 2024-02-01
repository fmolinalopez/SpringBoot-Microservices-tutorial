package es.molina.employeeservice.service.impl;

import es.molina.employeeservice.dto.ApiResponseDto;
import es.molina.employeeservice.dto.DepartmentDto;
import es.molina.employeeservice.dto.EmployeeDto;
import es.molina.employeeservice.mapper.EmployeeMapper;
import es.molina.employeeservice.repository.EmployeeRepository;
import es.molina.employeeservice.service.APIClient;
import es.molina.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return EmployeeMapper.mapToEmployeeDto(
                employeeRepository.save(EmployeeMapper.mapToEmployee(employeeDto)));
    }

    @Override
//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefatulDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefatulDepartment")
    public ApiResponseDto getEmployee(Long id) {

        LOGGER.error("inside of getEmployee method");
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employeeRepository.findById(id).get());

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

    public ApiResponseDto getDefatulDepartment(Long id, Exception exception) {
        LOGGER.error("inside of getDefaultDepartment method");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employeeRepository.findById(id).get());

        DepartmentDto defaultDepartment = DepartmentDto.builder()
                .departmentDescription("DEFAULT")
                .departmentName("DEFAULT")
                .departmentCode("DEFAULT")
                .build();

        ApiResponseDto response = new ApiResponseDto();
        response.setEmployee(employeeDto);
        response.setDepartment(defaultDepartment);

        return response;
    }
}

package es.molina.employeeservice.service;

import es.molina.employeeservice.dto.DepartmentDto;
import es.molina.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationAPIClient {

    @GetMapping("api/organizations/{id}")
    OrganizationDto getOrganization(@PathVariable Long id);
}

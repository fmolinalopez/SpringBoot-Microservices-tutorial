package es.molina.organizationservice.service;

import es.molina.organizationservice.dto.OrganizationDto;
import es.molina.organizationservice.entity.Organization;
import org.springframework.stereotype.Service;

public interface OrganizationService {

    public OrganizationDto saveOrganization(OrganizationDto organizationDto);

    public OrganizationDto getOrganization(Long id);
}

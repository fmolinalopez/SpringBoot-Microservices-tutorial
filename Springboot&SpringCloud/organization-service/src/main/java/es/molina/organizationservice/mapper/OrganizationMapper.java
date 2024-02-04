package es.molina.organizationservice.mapper;

import es.molina.organizationservice.dto.OrganizationDto;
import es.molina.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        return new Organization(organizationDto.getId(), organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(), organizationDto.getOrganizationCode(),
                organizationDto.getOrganizationCreatedDate());
    }

    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        return new OrganizationDto(organization.getId(), organization.getOrganizationName(),
                organization.getOrganizationDescription(), organization.getOrganizationCode(),
                organization.getOrganizationCreatedDate());
    }
}

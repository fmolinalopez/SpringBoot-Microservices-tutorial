package es.molina.organizationservice.service.impl;

import es.molina.organizationservice.dto.OrganizationDto;
import es.molina.organizationservice.mapper.OrganizationMapper;
import es.molina.organizationservice.repository.OrganizationRepository;
import es.molina.organizationservice.service.OrganizationService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        return OrganizationMapper.mapToOrganizationDto(
                this.organizationRepository.save(OrganizationMapper.mapToOrganization(organizationDto)));
    }

    @Override
    public OrganizationDto getOrganization(Long id) {
        return OrganizationMapper.mapToOrganizationDto(this.organizationRepository.findById(id).get());
    }
}

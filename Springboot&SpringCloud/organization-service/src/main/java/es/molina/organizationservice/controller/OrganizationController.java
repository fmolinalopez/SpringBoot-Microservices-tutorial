package es.molina.organizationservice.controller;

import es.molina.organizationservice.dto.OrganizationDto;
import es.molina.organizationservice.mapper.OrganizationMapper;
import es.molina.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.ok(this.organizationService.saveOrganization(organizationDto));
    }

    @GetMapping("{id}")
    public ResponseEntity getOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(this.organizationService.getOrganization(id));
    }
}

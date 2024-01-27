package es.molina.departmentservice.service.impl;

import es.molina.departmentservice.dto.DepartmentDto;
import es.molina.departmentservice.entity.Department;
import es.molina.departmentservice.repository.DepartmentRepository;
import es.molina.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode());

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        Department department = departmentRepository.findById(id).get();

        return new DepartmentDto(department.getId(), department.getDepartmentName(),
                department.getDepartmentDescription(), department.getDepartmentCode());
    }
}

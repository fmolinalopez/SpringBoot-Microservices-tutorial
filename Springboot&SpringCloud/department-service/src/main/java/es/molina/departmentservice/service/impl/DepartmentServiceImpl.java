package es.molina.departmentservice.service.impl;

import es.molina.departmentservice.dto.DepartmentDto;
import es.molina.departmentservice.entity.Department;
import es.molina.departmentservice.mapper.DepartmentMapper;
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

        return DepartmentMapper.matToDepartmentDto(
                departmentRepository.save(DepartmentMapper.mapToDepartment(departmentDto)));
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        return DepartmentMapper.matToDepartmentDto(
                departmentRepository.findById(id).get());
    }
}

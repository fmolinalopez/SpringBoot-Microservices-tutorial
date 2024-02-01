package es.molina.departmentservice.mapper;

import es.molina.departmentservice.dto.DepartmentDto;
import es.molina.departmentservice.entity.Department;

public class DepartmentMapper {

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        return new Department(departmentDto.getId(), departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());
    }

    public static DepartmentDto matToDepartmentDto(Department department) {
        return new DepartmentDto(department.getId(), department.getDepartmentName(),
                department.getDepartmentDescription(), department.getDepartmentCode());
    }
}

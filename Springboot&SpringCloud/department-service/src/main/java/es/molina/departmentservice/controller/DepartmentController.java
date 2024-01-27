package es.molina.departmentservice.controller;

import es.molina.departmentservice.dto.DepartmentDto;
import es.molina.departmentservice.entity.Department;
import es.molina.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.saveDepartment(departmentDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }
}

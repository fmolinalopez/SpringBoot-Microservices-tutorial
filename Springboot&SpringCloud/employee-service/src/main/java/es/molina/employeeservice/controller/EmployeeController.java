package es.molina.employeeservice.controller;

import es.molina.employeeservice.dto.EmployeeDto;
import es.molina.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDto));
    }

    @GetMapping("{id}")
    public ResponseEntity getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }
}

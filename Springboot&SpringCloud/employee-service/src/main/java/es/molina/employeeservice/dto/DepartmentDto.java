package es.molina.employeeservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DepartmentDto {

    private Long id;

    private String departmentName;

    private String departmentDescription;

    private String departmentCode;
}

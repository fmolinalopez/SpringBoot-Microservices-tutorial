package es.molina.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.molina.springboot.bean.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

	// http://localhost:8080/student
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(
				1, 
				"Fran", 
				"Molina"
		);
		
		//	return new ResponseEntity<Student>(student, HttpStatus.OK);
		return ResponseEntity.ok()
				.header("custom-header", "Fran")
				.body(student);
	}
	
	// http://localhost:8080/students
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = new ArrayList<>();
		
		students.add(new Student(1, "Persona", "Primera"));
		students.add(new Student(2, "Segunda", "Persona"));
		students.add(new Student(3, "Es", "Tercera"));
		
		return ResponseEntity.ok(students);
	}
	
	// Spring Boot REST API with Path variable
	// {id} - URI Template variable
	// http://localhost:8080/students/1/fran/molina
	@GetMapping("/{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable("id") Integer studentId,
			@PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
		return ResponseEntity.ok(new Student(studentId, firstName, lastName));
	}
	
	// Spring Boot REST API with Request Params
	// http://localhost:8080/students/query?id=1&firstName=Fran&lastName=Molina
	@GetMapping("/query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam("id") Integer studentId,
			@RequestParam String firstName, @RequestParam String lastName) {
		return ResponseEntity.ok(new Student(studentId, firstName, lastName));
	}
	
	// Spring Boot REST API that handles HTTP POST request
	// @PostMapping and @RequestBody
	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println("Id: " + student.getId());
		System.out.println("First Name: " + student.getFirstName());
		System.out.println("Last Name: " + student.getLastName());
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	
	// Spring Boot REST API that handles HTTP PUT request - updating existing resource
	// @PutMapping @PathVariable and @RequestBody
	@PutMapping("/{id}/update")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		System.out.println("First Name: " + student.getFirstName());
		System.out.println("Last Name: " + student.getLastName());
		return ResponseEntity.ok(student);
	}
	
	// Spring Boot REST API that hamdles HTTP DELETE request
	// @DeleteMapping and @PathValirable
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
		return ResponseEntity.ok("Student " + id + " deleted successfully");
	}
}

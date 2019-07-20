package id.go.bps.jpa.mapping.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.go.bps.jpa.mapping.exception.CustomErrorException;
import id.go.bps.jpa.mapping.model.Student;
import id.go.bps.jpa.mapping.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	// List All Student
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}
	
	// Get By Id
	@GetMapping("students/{id}")
	public Student getStudentById(@PathVariable Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent()) {
			return student.get();
		}else {
			// Throw Error
			throw new CustomErrorException("student with id: "+ id +" not found!");
		}
	}
	
	// save
	@PostMapping("/students")
	public Student addNewStudent(@Valid @RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	// update
	@PutMapping("/students/{id}")
	public Student updateStudent(@Valid @RequestBody Student updatedStudent, 
			@PathVariable Long id) {
		
		return studentRepository.findById(id)
			.map(student -> {
				student.setName(updatedStudent.getName());
				student.setAge(updatedStudent.getAge());
				student.setEmail(updatedStudent.getEmail());
				return studentRepository.save(student);
			}).orElseThrow(()-> new CustomErrorException("Student with id: "+ id +" not found!"));
	}
	
	// delete
	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		return studentRepository.findById(id)
				.map(student -> {
					studentRepository.delete(student);
					return "Delete succesful";
				}).orElseThrow(()-> new CustomErrorException("Student with id: "+ id +" not found!"));
	}
}

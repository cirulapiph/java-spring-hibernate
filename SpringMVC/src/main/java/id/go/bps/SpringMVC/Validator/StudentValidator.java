package id.go.bps.SpringMVC.Validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import id.go.bps.SpringMVC.Model.Student;
import id.go.bps.SpringMVC.Repository.StudentRepository;

@Component
public class StudentValidator implements Validator{
	private EmailValidator emailValidator = EmailValidator.getInstance();
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Student.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Student student = (Student) target;
		ValidationUtils.rejectIfEmpty(errors, "userName", "NotEmpty.student.username");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "NotEmpty.student.firstname");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty.student.lastname");
		ValidationUtils.rejectIfEmpty(errors, "email", "NotEmpty.student.email");
		ValidationUtils.rejectIfEmpty(errors, "gender", "NotEmpty.student.gender");
		ValidationUtils.rejectIfEmpty(errors, "country", "NotEmpty.student.country");
		
		// Custom method
		
		// Check duplicate username
		if(studentRepository.findStudentUserName(student.getUserName())!=null) {
			errors.rejectValue("userName", "Duplicate.student.username");
		}
		//check valid email
		if(!this.emailValidator.isValid(student.getEmail())) {
			errors.rejectValue("email", "Pattern.student.email");
		}
		//check duplicate email
		if(studentRepository.findStudentUserName(student.getEmail())!=null) {
			errors.rejectValue("email", "Duplicate.student.email");
		}
	}
	
}

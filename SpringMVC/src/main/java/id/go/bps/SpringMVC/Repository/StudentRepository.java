package id.go.bps.SpringMVC.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import id.go.bps.SpringMVC.Model.Student;

@Repository
public class StudentRepository {
	private static final Map<String,Student> STUDENTS = new HashMap<String, Student>();
	static {
		Student student1 = new Student("choerulafifanto","Choerul","Afifanto","choerulafifanto@gmail.com","M","ID");
		Student student2 = new Student("awalakhir","Awal","Akhir","awal@gmail.com","F","AU");
		Student student3 = new Student("bebasaja","Bebas","Aja","bebas@gmail.com","M","US");

		STUDENTS.put(student1.getUserName(),student1);
		STUDENTS.put(student2.getUserName(),student2);
		STUDENTS.put(student3.getUserName(),student3);
		
	}
	
	public List<Student> listAllStudent(){
		List<Student> students = new  ArrayList<Student>();
		students.addAll(STUDENTS.values());
		return students;
	}
	
	public Student addStudent(Student student) {
		Student saveStudent =new Student(student.getUserName(), 
				student.getFirstName(), student.getLastName(),
				student.getEmail(), student.getGender(), student.getCountry());
		STUDENTS.put(student.getUserName(), saveStudent);
		return saveStudent;
	}
	
	public Student findStudentUserName(String username) {
		Collection<Student> students = STUDENTS.values();
		for(Student s: students) {
			if(s.getUserName().equals(username)) {
				return s;
			}
		}
		return null;
	}
	
	public Student findEmail(String email) {
		Collection<Student> students = STUDENTS.values();
		for(Student s: students) {
			if(s.getEmail().equals(email)) {
				return s;
			}
		}
		return null;
	}
}

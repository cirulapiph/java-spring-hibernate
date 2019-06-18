package id.go.bps.SpringMVC.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.go.bps.SpringMVC.Model.Country;
import id.go.bps.SpringMVC.Model.Gender;
import id.go.bps.SpringMVC.Model.Student;
import id.go.bps.SpringMVC.Repository.CountryRepository;
import id.go.bps.SpringMVC.Repository.GenderRepository;
import id.go.bps.SpringMVC.Repository.StudentRepository;
import id.go.bps.SpringMVC.Validator.StudentValidator;

@Controller
public class HomeController {
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentValidator studentValidator;
	
	// static List<Gender> genders = new ArrayList<Gender>();
	// static {
	// 	genders.add(new Gender("M","lang.male"));
	// 	genders.add(new Gender("F","lang.female"));
	// }
	
	// init validator
	@InitBinder
	private void InitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if(target==null) {
			return;
		}
		if(target.getClass()==Student.class) {
			dataBinder.setValidator(studentValidator);
		}
	}
	
	
	// get value from application.properties
	@Value("${welcome.message}")
	private String greetingMessage;
	
	@Value("${error.message}")
	private String errorMessage;
	
	@RequestMapping(value= {"/","/index"}, method = RequestMethod.GET)
	public String Home(Model model) {
		// Map<String, String> genderList = new HashMap<String, String>();
		// genderList.put("F", "lang.female");
		// genderList.put("M", "lang.male");
		
		model.addAttribute("greeting", greetingMessage);
		List<Student> students  = studentRepository.listAllStudent();
		Map<String, Country> mapCountries = countryRepository.mapCountries();
		Map<String, Gender> mapGenders = genderRepository.mapGenders();
		model.addAttribute("students", students);
		model.addAttribute("mapGenders", mapGenders);
		model.addAttribute("mapCountries", mapCountries);
		return "Home";
	}
	
	// sama aja 2 syntax di bawah
//	@RequestMapping(value= {"/addStudent"}, method = RequestMethod.GET)
	@GetMapping(value= {"/addStudent"})
	public String addStudent(Student student, Model model) {
		List<Country> countries = countryRepository.listAllCountries();
		List<Gender> genders = genderRepository.listGenders();
		model.addAttribute("countries", countries);
		model.addAttribute("genders", genders);
		return "AddStudent";
	}
	
	@PostMapping(value= {"/addStudent"})
	public String saveStudent(Model model, 
			@ModelAttribute("student") @Validated Student student,
			BindingResult result) {
				
		List<Country> countries = countryRepository.listAllCountries();
		List<Gender> genders = genderRepository.listGenders();
		
		if(result.hasErrors()) {
			model.addAttribute("countries", countries);
			model.addAttribute("genders", genders);
			return "AddStudent";
		}
		
		Student newStudent = null;
		try {
			newStudent = studentRepository.addStudent(student);
		}catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("countries", countries);
			model.addAttribute("genders", genders);
			return "AddStudent";
		}
		
//		String _userName = student.getUserName();
//		String _firstName = student.getFirstName();
//		String _lastName = student.getLastName();
//		String _email = student.getEmail();
//		String _gender = student.getGender();
//		String _country = student.getCountry();
//		
//		// validasi fisrtName dan lastName
//		if(_firstName!=null && _firstName.length()>0 
//				&& _lastName!=null && _lastName.length()>0) {
//			students.add(new Student(_userName,_firstName,_lastName,_email,_gender,_country));
//			return "redirect:/";
//		}
//		model.addAttribute("errorMessage", errorMessage);
		return "redirect:/";
	}
}

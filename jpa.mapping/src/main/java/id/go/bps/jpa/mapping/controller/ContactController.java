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
import id.go.bps.jpa.mapping.model.Contact;
import id.go.bps.jpa.mapping.repository.ContactRepository;
import id.go.bps.jpa.mapping.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	// List All Contact
	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return (List<Contact>) contactRepository.findAll();
	}
	
	@GetMapping("students/{studentId}/contacts")
	public Contact getContactByStudentId(@PathVariable Long studentId) {
		if(!studentRepository.existsById(studentId)) {
			throw new CustomErrorException("Student Not Found");
		}
		List<Contact> contact = contactRepository.findStudentById(studentId);
		if(contact.size()>0) {
			return contact.get(0);
		}else {
			throw new CustomErrorException("Contact Not Found");
		}
	}
	
	@PostMapping("/students/{studentID}/contacts")
	public Contact addContact(@PathVariable Long studentID,
			@Valid@RequestBody Contact contact ) {
	 	return studentRepository.findById(studentID)
				.map(student -> {
					contact.setStudent(student);
					return contactRepository.save(contact);
				}).
				orElseThrow(()->new 
						CustomErrorException("Student With ID :" + studentID +" Not found"));
	}
	
	// Get By Id
	@GetMapping("contacts/{id}")
	public Contact getContactById(@PathVariable Long id) {
		Optional<Contact> contact = contactRepository.findById(id);
		if(contact.isPresent()) {
			return contact.get();
		}else {
			// Throw Error
			throw new CustomErrorException("contact with id: "+ id +" not found!");
		}
	}
	
	// save
	@PostMapping("/contacts")
	public Contact addNewContact(@Valid @RequestBody Contact contact) {
		return contactRepository.save(contact);
	}
	
	// update
	@PutMapping("/contacts/{id}")
	public Contact updateContact(@Valid @RequestBody Contact updatedContact, 
			@PathVariable Long id) {
		
		return contactRepository.findById(id)
			.map(contact -> {
				contact.setCity(updatedContact.getCity());
				contact.setPhone(updatedContact.getPhone());
				return contactRepository.save(contact);
			}).orElseThrow(()-> new CustomErrorException("Contact with id: "+ id +" not found!"));
	}
	
	// delete
	@DeleteMapping("/contacts/{id}")
	public String deleteContact(@PathVariable Long id) {
		return contactRepository.findById(id)
				.map(contact -> {
					contactRepository.delete(contact);
					return "Delete succesful";
				}).orElseThrow(()-> new CustomErrorException("Contact with id: "+ id +" not found!"));
	}
}

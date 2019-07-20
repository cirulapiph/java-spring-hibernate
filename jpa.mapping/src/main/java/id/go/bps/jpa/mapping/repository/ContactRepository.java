package id.go.bps.jpa.mapping.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.go.bps.jpa.mapping.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{
	@Bean
	List<Contact> findStudentById(Long studentId);
}

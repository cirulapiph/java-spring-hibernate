package id.go.bps.jpa.mapping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.go.bps.jpa.mapping.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long>{
	List<Contact> findStudentById(Long studentId);
}

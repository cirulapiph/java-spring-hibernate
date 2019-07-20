package id.go.bps.jpa.mapping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.go.bps.jpa.mapping.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}

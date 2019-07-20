package id.go.bps.jpa.mapping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.go.bps.jpa.mapping.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserName(String userName);
}

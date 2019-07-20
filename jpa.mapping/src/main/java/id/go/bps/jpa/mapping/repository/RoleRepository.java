package id.go.bps.jpa.mapping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import id.go.bps.jpa.mapping.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}

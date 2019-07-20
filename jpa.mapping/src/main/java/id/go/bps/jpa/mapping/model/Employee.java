package id.go.bps.jpa.mapping.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_employee")
@IdClass(Employee.EmployeeIdClass.class)
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String employeeId;
	@Id
	private String companyId;
	
	private String firstName;
	private String lastName;
	
	@Data
	static class EmployeeIdClass implements Serializable{
		private String employeeId;
		private String companyId;
	}
}

package id.go.bps.jpa.mapping.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(length=100)
	@NotNull
	private String firstName;
	
	@Column(length=100)
	private String lastName;
	
	@Column(length=50)
	@NotNull
	private String userName;
	
	@Column(length=100, unique = true)
	@Email(message = "Email not valid")
	private String email;
	
	@Column(length=100, nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
	private List<Role> roles;

	public User(String firstName, String lastName, String userName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	
}

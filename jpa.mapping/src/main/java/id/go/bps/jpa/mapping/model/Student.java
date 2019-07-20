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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import id.go.bps.jpa.mapping.audit.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_student")
public class Student extends Auditable<String> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="student_name", nullable=false, length=100)
	@NotEmpty(message="Student Name may not be empty")
	private String name;
	
	@NaturalId
	@Column(length=100, unique=true)
	private String email;
	
	private int age;
	
	@OneToOne(fetch=FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			mappedBy = "student"
			)
	@JsonIgnore
	private Contact contact;
	
	@OneToMany(fetch=FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			mappedBy = "student")
	private List<Assignment> assignments;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "student_subject", 
	joinColumns = @JoinColumn(name="student_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name="subject_id", referencedColumnName = "id"))
	private Set<Subject> subjects;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

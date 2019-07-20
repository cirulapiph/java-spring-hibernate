package id.go.bps.jpa.mapping.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_contact")

public class Contact implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	private String city;
	
	@Column(name="phone_number", length=100)
	private String phone;
	
	@OneToOne(fetch=FetchType.LAZY,
			optional = false)
	@JoinColumn(name = "student_id", nullable = false, unique = true)
	@JsonIgnore
	private Student student;
	
	
}

package com.example.rest.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data // This is equivalent of @Getter, @Setter , @EqualsAndHashcode and @RequiredArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

	private static final long serialVersionUID = -8416706209753717633L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Basic
	private Long id;

	@Basic
	@Column(name = "role")
	private String role;

	@Basic
	@Column(name = "name")
	private String name;
	
	public Employee(final String name, final String role) {
		this.name = name;
		this.role = role;
	}
}

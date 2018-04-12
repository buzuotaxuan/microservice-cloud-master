package com.luckin.user.mode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="TEST")
public class TestPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="name")
	String name;

	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

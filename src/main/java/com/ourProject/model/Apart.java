package com.ourProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="apart")
public class Apart {
	@Id
	@Column(name = "apartId")
	
	private Integer apartId;
}
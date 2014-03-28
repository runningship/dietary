package org.bc.dietary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Fact {

	@Id
	public String uid;
	
	@Column(nullable=false)
	public String subject;
	
	@Column(nullable=false)
	public String verb;
	
	@Column(nullable=false)
	public String object;
}

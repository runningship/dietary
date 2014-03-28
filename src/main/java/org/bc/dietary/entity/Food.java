package org.bc.dietary.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Food {
	
	@Id
	public String uid;
	
	public String name;
	
	//;分开
	public String alias;
	
	
	public String class1;
	
	public String class2;
	
	public String class3;
	
	public String area;
}

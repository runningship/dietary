package org.bc.dietary.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FoodNutrient {

	@Id
	public String uid;
	
	public String foodUID;
	
	public String nutrient;
	
	// mg/100g
	public float content;
}

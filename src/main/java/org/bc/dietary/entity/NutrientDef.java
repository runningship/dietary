package org.bc.dietary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NutrientDef {

	@Id
	public int nutrNO;
	
	@Column(nullable=false)
	public String units;
	
	@Column(nullable=false)
	public String tagName;
	
	//中文名称
	public String cName;
	
	public String nutrDesc;
	
	//小数点位数
	public int numDec;
	
}

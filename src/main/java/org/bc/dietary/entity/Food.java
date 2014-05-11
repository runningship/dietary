package org.bc.dietary.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.bc.dietary.SimpDaoTool;

@Entity
public class Food {
	
	@Id
	public int ndbNo;
	
	@Column(nullable=false)
	public String name;
	
	/**
	 * 中文名
	 */
	public String cname;
	//;分开
	public String alias;
	
	public int refuse;
	
	/**
	 * 1,0
	 */
	public char common;
	
	public List<FoodNutrient> getNutrients(){
		List<FoodNutrient> nutrients = SimpDaoTool.getGlobalCommonDaoService().listByParams(FoodNutrient.class, "from FoodNutrient where ndbNo=:ndbNo", 
				new String[]{"ndbNo"}, new Object[]{ndbNo});
		return nutrients;
	}
}

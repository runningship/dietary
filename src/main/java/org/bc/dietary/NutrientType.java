package org.bc.dietary;

import java.util.ArrayList;
import java.util.List;

public enum NutrientType {
	能量(Unit.KJ),
	蛋白质(Unit.g),
	脂肪(Unit.g),
	膳食纤维(Unit.g),
	胆固醇(Unit.mg),
	视黄醇(Unit.ug),
	胡萝卜素(Unit.ug),
	硫胺素(Unit.mg),
	核黄素(Unit.mg),
	维生素B6(Unit.mg),
	维生素B12(Unit.ug),
	叶酸(Unit.ug),
	烟酸(Unit.mg),
	维生素C(Unit.mg),
	维生素E(Unit.mg),
	钙(Unit.mg),
	磷(Unit.mg),
	钾(Unit.mg),
	钠(Unit.mg),
	镁(Unit.mg),
	铁(Unit.mg),
	锌(Unit.mg),
	硒(Unit.ug),
	铜(Unit.mg),
	锰(Unit.mg),
	碘(Unit.ug);
	
	private Unit unit;
	
	private NutrientType(Unit unit){
		this.unit = unit;
	}
	
	public Unit getUnit(){
		return this.unit;
	}
	public static List<String> toList(){
		List<String> list = new ArrayList<String>();
		for(NutrientType nt : NutrientType.values()){
			list.add(nt.toString()+":"+nt.unit.toString());
		}
		return list;
	}
	
}

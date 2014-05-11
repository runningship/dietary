package org.bc.dietary.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.bc.dietary.NutrientTypeCache;
import org.bc.dietary.SimpDaoTool;
import org.bc.dietary.Unit;

@Entity
public class FoodNutrient {

	@Id
	public Long uid;
	/**
	 * 食物编号
	 */
	public  int ndbNo;
	
	/**
	 * 营养成分编号
	 */
	public int nutrNo;
	
	// 每100g可食部分
	public float nutrVal;
	
	/**
	 * 样本数
	 */
	public int numDataPoints;
	
	/**
	 * 标准误差
	 */
	public float stdError;
	
	public float min;
	
	public float max;
	
	public String lastUpdateTime;
	
	 //以微克为单位的统一计量
	public float nutrValInUG;
	
	public void cacuContentInUG(){
		String unit = NutrientTypeCache.getNutrientUnit(nutrNo);
		if(StringUtils.isEmpty(unit)){
			return;
		}
		if(Unit.g.toString().equals(unit)){
			nutrValInUG = nutrVal*1000*1000;
		}
		if(Unit.mg.toString().equals(unit)){
			nutrValInUG = nutrVal*1000;
		}
		if(Unit.ug.toString().equals(unit)){
			nutrValInUG = nutrVal;
		}
	}
	
	public Food getFood(){
		return SimpDaoTool.getGlobalCommonDaoService().get(Food.class, ndbNo);
	}
	
	public NutrientDef getNutrientDef(){
		return SimpDaoTool.getGlobalCommonDaoService().get(NutrientDef.class, nutrNo);
	}

	@Override
	public String toString() {
		return "FoodNutrient [ndbNo=" + ndbNo + ", nutrNo=" + nutrNo
				+ ", nutrVal=" + nutrVal + ", numDataPoints=" + numDataPoints
				+ ", stdError=" + stdError + ", min=" + min + ", max=" + max
				+ ", lastUpdateTime=" + lastUpdateTime + ", nutrValInUG="
				+ nutrValInUG + "]";
	}
	
}

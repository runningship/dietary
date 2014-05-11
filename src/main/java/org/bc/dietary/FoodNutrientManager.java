package org.bc.dietary;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bc.dietary.entity.Food;
import org.bc.dietary.entity.FoodNutrient;
import org.bc.dietary.entity.NutrientDef;
import org.bc.dietary.web.ModelAndView;
import org.bc.dietary.web.Module;
import org.bc.dietary.web.WebMethod;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.GException;
import org.bc.sdak.TransactionalServiceHelper;

@Module(name="/food/nutrient")
public class FoodNutrientManager {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	@WebMethod
	public ModelAndView add(FoodNutrient nutrient){
		ModelAndView mv = null;
		FoodNutrient po = service.getUniqueByParams(FoodNutrient.class, new String[]{"ndbNo","nutrNo"},new Object[]{nutrient.ndbNo,nutrient.nutrNo});
		NutrientDef nutrDef = nutrient.getNutrientDef();
		if(po!=null){
			mv = list(nutrient.getFood().name);
			mv.data.put("msg", nutrDef.nutrDesc+"已经添加");
		}else{
			nutrient.cacuContentInUG();
			service.saveOrUpdate(nutrient);
			mv = list(nutrient.getFood().name);
			mv.data.put("msg", "营养素["+nutrDef.nutrDesc+nutrient.nutrVal+nutrDef.units+"]添加成功");
		}
		return mv;
	}
	
	@WebMethod
	public ModelAndView delete(Long nutrientUid){
		FoodNutrient po = service.get(FoodNutrient.class, nutrientUid);
		ModelAndView mv = null;
		if(po!=null){
			service.delete(po);
			mv = list(po.getFood().name);
			mv.data.put("msg", "删除"+po.getNutrientDef().nutrDesc+"成功");
		}else{
			throw new GException(DietaryExceptionType.ParameterMissingError,"没有找到记录");
		}
		return mv;
	}
	
	@WebMethod
	public ModelAndView list(String ndbNo){
		ModelAndView mv = new ModelAndView();
		mv.jsp="/Nutrient.jsp";
		
		Food food = service.get(Food.class, ndbNo);
		if(food==null){
				throw new GException(DietaryExceptionType.ParameterMissingError,"没有要查询的食物");
		}
		mv.data.put("foodName", food.name);
		mv.data.put("nutrients", food.getNutrients());
		
//		List<String> nutrientTypes = NutrientType.toList();
		//只显示没有添加的
//		for(FoodNutrient fn : nutrients){
//			nutrientTypes.remove(fn.nutrient+":"+fn.unit);
//		}
//		mv.data.put("nutrientTypes", nutrientTypes);
		return mv;
	}
}

package org.bc.dietary;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bc.dietary.entity.Food;
import org.bc.dietary.web.ModelAndView;
import org.bc.dietary.web.Module;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;

@Module(name="/food/")
public class FoodManager {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	public ModelAndView add(Food food){
		if(StringUtils.isEmpty(food.uid)){
			food.uid = UUID.randomUUID().toString();
		}
		service.saveOrUpdate(food);
		return new ModelAndView();
	}
	
	public ModelAndView list(int intVal,Integer xx){
		return new ModelAndView();
	}
}

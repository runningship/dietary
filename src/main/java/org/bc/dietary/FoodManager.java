package org.bc.dietary;

import java.util.List;

import org.bc.dietary.entity.Food;
import org.bc.dietary.web.ModelAndView;
import org.bc.dietary.web.Module;
import org.bc.dietary.web.WebMethod;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;

@Module(name="/food/")
public class FoodManager {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	@WebMethod
	public ModelAndView add(Food food){
		ModelAndView mv = null;
		Food po = service.getUnique(Food.class, food);
		if(po!=null){
			mv = list();
			mv.data.put("msg", food.name+"已经存在");
		}else{
			service.saveOrUpdate(food);
			mv = list();
			mv.data.put("msg", "添加成功");
		}
		return mv;
	}
	
	@WebMethod
	public ModelAndView list(){
		List<Food> foods = service.listByParams(Food.class, "from Food where common=0", null, null);
		ModelAndView mv = new ModelAndView();
		mv.data.put("foods", foods);
		mv.jsp="/Food.jsp";
		return mv;
	}
	
	@WebMethod
	public ModelAndView listCommon(){
		List<Food> foods = service.listByParams(Food.class, "from Food where common=1", null, null);
		ModelAndView mv = new ModelAndView();
		mv.data.put("foods", foods);
		mv.jsp="/Food.jsp";
		return mv;
	}
	
	@WebMethod
	public ModelAndView setCommon(int ndbNo){
		Food food = service.get(Food.class, ndbNo);
		ModelAndView mv = new ModelAndView();
		if(food!=null){
			food.common=true;
			service.saveOrUpdate(food);
		}
//		mv.redirect="list";
		mv.data.put("msg", "set common successfully");
		return mv;
	}
}

package org.bc.dietary;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bc.dietary.entity.Fact;
import org.bc.dietary.web.ModelAndView;
import org.bc.dietary.web.Module;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;
import org.bc.sdak.utils.BeanUtil;
import org.bc.sdak.utils.LogUtil;

@Module(name="/fact/")
public class FactManager {

	CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	public ModelAndView add(Fact fact){
		if(StringUtils.isEmpty(fact.uid)){
			fact.uid = UUID.randomUUID().toString();
		}
		Fact po = service.getUnique(Fact.class, fact);
		ModelAndView mv = new ModelAndView();
		if(po==null){
			service.saveOrUpdate(fact);
		}else{
			String msg = BeanUtil.toString(fact,"uid")+" has already exist.";
			LogUtil.info(msg);
			mv.data.put("msg", msg);
		}
		return mv;
	}
}

package org.bc.dietary;

import org.bc.dietary.entity.NutrientDef;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;

public class NutrientTypeCache {

	static CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	
	public static String getNutrientUnit(int nutrNo){
		NutrientDef def = service.get(NutrientDef.class, nutrNo);
		if(def==null){
			return null;
		}
		return def.units;
	}
}

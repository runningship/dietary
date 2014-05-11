package org.bc.dietary.init;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.bc.dietary.StartUpListener;
import org.bc.dietary.entity.Food;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;

public class TranslateFoodName {

	public static void main(String[] args) throws IOException{
		StartUpListener.initDataSource();
		CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		List<Food> list = service.listByParams(Food.class, "from Food where cname is null", null, null);
		for(Food food : list){
			try{
			URL url = new URL("http://fanyi.youdao.com/translate?type=auto&i="+food.name+"&smartresult=rule&doctype=json");
			URLConnection conn = url.openConnection();
			String result = IOUtils.toString(conn.getInputStream());
			JSONObject json = JSONObject.fromObject(result);
			JSONObject obj = json.getJSONArray("translateResult").getJSONArray(0).getJSONObject(0);
			food.cname=obj.getString("tgt");
			service.saveOrUpdate(food);
			System.out.println(food.name+":"+obj.getString("tgt"));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}

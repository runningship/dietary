package org.bc.dietary.init;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.bc.dietary.StartUpListener;
import org.bc.dietary.entity.Food;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;
import org.bc.sdak.utils.LogUtil;

public class ImportFood {

	public static void main(String[] args){
		StartUpListener.initDataSource();
		CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		File file = new File("E:\\java\\git\\dietary\\FOOD_DES.xls");
		try{
		Workbook book=Workbook.getWorkbook(file);//  
        Sheet sheet=book.getSheet(0);   //获得第一个工作表对象 
        for(int i=1;i<sheet.getRows();i++){
        	Food food = new Food();
        	food.ndbNo = Integer.valueOf(sheet.getCell(0, i).getContents());
        	food.name = sheet.getCell(1,i).getContents();
        	try{
        		food.refuse = Integer.valueOf(sheet.getCell(2,i).getContents());
        	}catch(NumberFormatException ex){
        		
        	}
        	Food po = service.get(Food.class, food.ndbNo);
            if(po==null){
            	service.saveOrUpdate(food);
            }
            LogUtil.info(i+"-->nutrient "+food.name+" added ");
        }  
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

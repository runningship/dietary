package org.bc.dietary.init;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.bc.dietary.StartUpListener;
import org.bc.dietary.entity.NutrientDef;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;
import org.bc.sdak.utils.LogUtil;

public class ImportNutr_Def {

	
	public static void main(String[] args){
		StartUpListener.initDataSource();
		CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
		File file = new File("E:\\java\\git\\dietary\\NUTR_DEF.xls");
		try{
		Workbook book=Workbook.getWorkbook(file);//  
        Sheet sheet=book.getSheet(0);   //获得第一个工作表对象 
        for(int i=1;i<sheet.getRows();i++){
        	NutrientDef def = new NutrientDef();
        	def.nutrNO=Integer.valueOf(sheet.getCell(0, i).getContents());
        	def.units = sheet.getCell(1,i).getContents();
        	def.tagName = sheet.getCell(2,i).getContents();
        	def.nutrDesc = sheet.getCell(3,i).getContents();
        	def.numDec = Integer.valueOf(sheet.getCell(4,i).getContents());
            NutrientDef po = service.get(NutrientDef.class, def.nutrNO);
            if(po==null){
            	service.saveOrUpdate(def);
            }
            LogUtil.info("nutrient "+def.tagName+" added ");
        }  
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

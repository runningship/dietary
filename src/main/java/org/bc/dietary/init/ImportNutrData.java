package org.bc.dietary.init;

import java.io.File;
import java.util.UUID;

import org.bc.dietary.StartUpListener;
import org.bc.dietary.entity.FoodNutrient;
import org.bc.sdak.CommonDaoService;
import org.bc.sdak.TransactionalServiceHelper;
import org.bc.sdak.utils.LogUtil;

import jxl.Sheet;
import jxl.Workbook;

public class ImportNutrData {
	private static CommonDaoService service = TransactionalServiceHelper.getTransactionalService(CommonDaoService.class);
	public static void main(String[] args) {
		StartUpListener.initDataSource();
		
		File file = new File("E:\\java\\git\\dietary\\NUT_DATA.xls");
		try {
			Workbook book = Workbook.getWorkbook(file);//
//			for(int i=3;i<book.getSheets().length;i++){
//				importSheet(book.getSheets()[i]);
//			}
			importSheet(book.getSheet(10));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void importSheet(Sheet sheet){
		for (int i = 1; i < sheet.getRows(); i++) {
			FoodNutrient fn = new FoodNutrient();
			fn.ndbNo=getIntValueOfCell(sheet,0,i);
			fn.nutrNo = getIntValueOfCell(sheet,1,i);
			fn.nutrVal = getFloatValueOfCell(sheet,2,i);
			fn.numDataPoints = getIntValueOfCell(sheet,3,i);
			fn.stdError =getFloatValueOfCell(sheet,4,i);
			fn.min = getFloatValueOfCell(sheet,10,i);
			fn.max= getFloatValueOfCell(sheet,11,i);
			fn.lastUpdateTime = sheet.getCell(16, i).getContents();
			fn.uid = UUID.randomUUID().getLeastSignificantBits();
			fn.cacuContentInUG();
			service.saveOrUpdate(fn);
			LogUtil.info(sheet.getName()+",row="+i+"导入成功:"+fn);
			
//			FoodNutrient po = service.getUniqueByParams(FoodNutrient.class, new String[]{"ndbNo","nutrNo"}, new Object[]{fn.ndbNo,fn.nutrNo});
//			if(po==null){
//				service.saveOrUpdate(fn);
//				LogUtil.info(sheet.getName()+",row="+i+"导入成功:"+fn);
//			}else{
//				LogUtil.info(sheet.getName()+",row="+i+"已经存在"+fn);
//			}
		}
	}
	private static Float getFloatValueOfCell(Sheet sheet,int col,int row){
		String content = sheet.getCell(col,row).getContents();
		try{
			return Float.valueOf(content);
		}catch(NumberFormatException ex){
//			System.out.println("not a valid number at sheet "+sheet.getName()+",row="+row+",column="+col);
			return -1f;
		}
	}
	
	private static Integer getIntValueOfCell(Sheet sheet,int col,int row){
		String content = sheet.getCell(col,row).getContents();
		try{
			return Integer.valueOf(content);
		}catch(NumberFormatException ex){
//			System.out.println("not a valid number at sheet "+sheet.getName()+",row="+row+",column="+col);
			return -1;
		}
	}
}

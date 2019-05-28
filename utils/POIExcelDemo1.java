package com.qhit.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIExcelDemo1 {

	public static void main(String[] args) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook(); //创建excel文件(Workbook)
		
//		HSSFSheet sheet = workbook.createSheet();   //创建一个没有命名的工作表(Sheet)
		HSSFSheet sheet = workbook.createSheet("4月24日"); //创建一个有命名的工作表(Sheet)
		
		
		String path = "d:\\demo.xls";
		FileOutputStream outputStream = new FileOutputStream(path);
		workbook.write(outputStream); //保存excel
		outputStream.close();         //关流
	}
}

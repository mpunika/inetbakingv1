package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSUtils {
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wrkbook;
	public static XSSFSheet wrksheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		
		     fis = new FileInputStream(xlfile);
		 wrkbook = new XSSFWorkbook(fis);
		wrksheet = wrkbook.getSheet(xlsheet);
		
		int rowcount = wrksheet.getLastRowNum();
		wrkbook.close();
		fis.close();
		return rowcount;
	}
	
	public static int getCellCount(String xlfile, String xlsheet,int rowcount) throws IOException {
		
		   fis = new FileInputStream(xlfile);
		   wrkbook = new XSSFWorkbook(fis);
		   wrksheet = wrkbook.getSheet(xlsheet);
		   row = wrksheet.getRow(rowcount);
		   
		   int cellcount = row.getLastCellNum();		   
		   wrkbook.close();
		   fis.close();		   
		   return cellcount;		   
	}
	
	public static String getCellData(String xlfile,String xlsheet,int rowcount,int cellcount) throws IOException {
		
		   fis = new FileInputStream(xlfile);
		   wrkbook = new XSSFWorkbook(fis);
		   wrksheet = wrkbook.getSheet(xlsheet);
		   String cellvalue = "";
		  		
		for(int i = 0;i <= rowcount ; i++) {
			
			XSSFRow currentrow = wrksheet.getRow(i);
			
			for(int j = 1; j <= cellcount; j++) {
				
				cellvalue = currentrow.getCell(j).toString();
				return cellvalue;
			}
		}
		return cellvalue;
	}
	

}

package com.iNetBanking_v1.utilities;

//dont have to create object..all these methods are static methods..jsut directly call the methods wherever applicable
//WRITING INTO XL FILE WONT WORK HERE BECAUSE EXCEL FILE IS NOT EDITABLE IN LOCAL SYSTEM WITHOUT LICENSE
//this is standard code to read from the excel file..just copy paste and modify only when necessary
//it uses rowcount n col count at runtime so for all excel sheets this code can be used
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;// import only xssf.usermodel.XSSFSheet not the SXSSF from the apache poi
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fis = new FileInputStream(xlfile);// get the file
		wb = new XSSFWorkbook(fis);// go to the workbook of the file
		ws = wb.getSheet(xlsheet);// go to the worksheet of the workbook
		int rowcount = ws.getLastRowNum();// getting last row number from the worksheet
		wb.close();// close the workbook
		fis.close();// close the file
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellcount;
	}

//below code reads single  row cell value.In main program which calls this insdie loop use this method
	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);// take any row and count the column in that
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = " ";
		}
		wb.close();
		fis.close();
		return data;
	}

//writing into xl file..this wont work as of now as excel file is not editable without license
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fis = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(xlfile);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}

}

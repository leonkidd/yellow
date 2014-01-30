package cn.yhhh.yellow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.heroes.jkit.utils.ExcelUtils;

/**
 * 将经管中，与1-3季小额农贷匹配的，在1-3季报表中填上四季
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-8
 * @since 1.0
 */
public class Main2 {

	public static final Map<String, Double> map = new HashMap<String, Double>();
	public static final Map<String, String[]> map1 = new HashMap<String, String[]>();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Main2.recursion(new File("F:/eclipse/doc/workspace"));
		
		FileInputStream fis = new FileInputStream("data.xls");
		Workbook book = create(fis);
		int numberOfSheets = book.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = book.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			Iterator<Row> iter = sheet.iterator();
			while (iter.hasNext()) {
				Row row = iter.next();
				
				int rowNum = row.getRowNum();
				
				if(rowNum < 3) {
					continue;
				}
				
				Cell cell1 = row.getCell(1);
				if(cell1 == null) {
					System.out.println(sheetName + ", " + rowNum);
					continue;
				}
				Cell cell9 = row.createCell(9);
				Cell cell10 = row.createCell(10);

				Object cellValue1 = ExcelUtils.getCellValue(cell1);
				if(cellValue1 == null) {
					System.out.println();
				}
				String[] value = map1.get(cellValue1.toString().replaceAll(" ", ""));
				if(value == null) {
					continue;
				}
				
				cell9.setCellValue(value[0]);
				cell10.setCellValue(value[1]);
			}
		}
		
		fis.close();
		FileOutputStream fos = new FileOutputStream("$.xls");
		book.write(fos);
		fos.close();
	}
	
	public static void core2(File file) throws Exception {
		
	}

	/**
	 * 根据Excel文件版本的不同，创建不同版本的Workbook。
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static Workbook create(InputStream in) throws Exception {
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(in)) {
			return new HSSFWorkbook(in);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}

	public static void core1(File file) throws Exception {
		int j = 0;
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = create(fis);
		int numberOfSheets = wb.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = wb.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			Iterator<Row> iter = sheet.iterator();
			while (iter.hasNext()) {
				Row row = iter.next();
				
				int rowNum = row.getRowNum();
				if(rowNum < 6) {
					continue;
				}
				
				// 客户号
				Cell cell1 = row.getCell(1);
				Cell cell6 = row.getCell(6);
				Cell cell7 = row.getCell(7);
				
				/*
				if (cell1 == null) {
					continue;
				}*/

				Object cellValue1 = ExcelUtils.getCellValue(cell1);

				String key = cellValue1.toString().replaceAll(" ", "");
				
				if(key.startsWith("复核人")) {
					break;
				}
				
				String[] value = map1.get(key);
				
				if(value == null) {
					Object cellValue6 = ExcelUtils.getCellValue(cell6);
					Object cellValue7 = ExcelUtils.getCellValue(cell7);
					map1.put(key, new String[]{cellValue6.toString(), cellValue7.toString()});
				}
				
				System.out.println(++j);
			}
		}
		fis.close();
	}

	public static void recursion(File file) throws Exception {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				recursion(f);
			}
		} else {
			core1(file);
		}
	}

}

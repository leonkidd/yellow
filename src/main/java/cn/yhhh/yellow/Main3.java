package cn.yhhh.yellow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.heroes.jkit.utils.ExcelUtils;

/**
 * 删除经管中与1-3季小额农贷匹配的记录，并将处理后的经管报表下发
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-8
 * @since 1.0
 */
public class Main3 {

	public static final Map<String, String> map = new HashMap<String, String>();
	public static final Map<String, String[]> map1 = new HashMap<String, String[]>();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
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
				
				Object cellValue1 = ExcelUtils.getCellValue(cell1);

				if(cellValue1 == null) {
					System.out.println("!");
				}
				
				map.put(cellValue1.toString().replaceAll(" ", ""), "");
				
				/*
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
				cell10.setCellValue(value[1]);*/
			}
		}
		fis.close();
		
		Main3.recursion(new File("F:/eclipse/doc/workspace"));
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
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = create(fis);
		int numberOfSheets = wb.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = wb.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			//Iterator<Row> iter = sheet.iterator();

			for(int j = 0; j <= sheet.getLastRowNum();) {
				Row row = sheet.getRow(j);

				if(j == 5) {
					Cell cell18 = row.createCell(18);
					cell18.setCellValue("是否小额农贷");
				}
				if(j < 6) {
					j++;
					continue;
				}

				// 客户号
				Cell cell1 = row.getCell(1);
				Object cellValue1 = ExcelUtils.getCellValue(cell1);
				String key = cellValue1.toString().replaceAll(" ", "");
				
				if(key.startsWith("复核人")) {
					break;
				}
				
				String value = map.get(key);
				if(value != null) {
					// 删除该行
					sheet.removeRow(row);
					if(sheet.getLastRowNum() <= j) {
						// row 是最后一行
						break;
					}
					sheet.shiftRows(j + 1, sheet.getLastRowNum(), -1);
				} else {
					j++;
				}
			}
			
			// 合并单元格
			int numMergedRegions = sheet.getNumMergedRegions();
			while(sheet.getNumMergedRegions() > 0) {
				sheet.removeMergedRegion(0);
			}
			
			/*
			List<Row> preRemove = new ArrayList<Row>();
			while (iter.hasNext()) {
				Row row = iter.next();
				
				int rowNum = row.getRowNum();
				if(rowNum < 6) {
					continue;
				}
				
				// 客户号
				Cell cell1 = row.getCell(1);

				Object cellValue1 = ExcelUtils.getCellValue(cell1);

				String key = cellValue1.toString().replaceAll(" ", "");
				
				if(key.startsWith("复核人")) {
					break;
				}
				
				String value = map.get(key);
				
				if(value != null) {
					// 准备删除该行
					preRemove.add(row);
				}
				
				System.out.println(++k);
			}
			
			for(Row row : preRemove) {
				sheet.removeRow(row);
				sheet.shiftRows(startRow, endRow, n);
			}*/
		}

		fis.close();
		FileOutputStream fos = new FileOutputStream("_" + file.getName());
		wb.write(fos);
		fos.close();
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

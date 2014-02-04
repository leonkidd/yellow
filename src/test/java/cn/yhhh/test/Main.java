package cn.yhhh.test;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Workbook book = ExcelUtils.create(new FileInputStream("1.xlsx"));
		int numberOfSheets = book.getNumberOfSheets();
		
		// 迭代Sheet
		for(int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = book.getSheetAt(i);
			
			// 迭代Row
			Iterator<Row> rows = sheet.rowIterator();
			while(rows.hasNext()) {
				Row row = rows.next();

				Cell cell10 = row.getCell(10);
				// 迭代Cell
				Iterator<Cell> cells = row.cellIterator();
			}
		}
		
	}

}

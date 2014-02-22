package demo;

import java.io.FileInputStream;

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
		FileInputStream fis = new FileInputStream("A.xls");
		Workbook book = ExcelUtils.create(fis);
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		System.out.println(cell.getCellFormula());
		System.out.println(cell.getStringCellValue());
		Object cellValue = ExcelUtils.getCellValue(cell);
		System.out.println(cellValue);
	}

}

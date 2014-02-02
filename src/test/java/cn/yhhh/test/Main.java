package cn.yhhh.test;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 实现Interceptor
		// 构建Yellow, 装入Parser, Interceptor, Filler

//		FileInputStream fis = new FileInputStream("");
//		// 只分析流
//		// Yellow多例
//		Yellow yellow = new TDYellow(null, null, null);
//		yellow.yellow(fis);
		Workbook book = ExcelUtils.create(new FileInputStream("1.xlsx"));
		Sheet sheet = book.getSheetAt(0);
		Row row1 = sheet.getRow(0);
		Cell cell1_3 = row1.getCell(2);
		System.out.println(cell1_3);
		
		Row row2 = sheet.getRow(2);
		Cell cell2_1 = row2.getCell(0);
		System.out.println(cell2_1);
	}

}

package cn.heroes.yellow.entity.impl;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.yellow.entity.TDSheet;

/**
 * Excel sheet
 * 
 * @author Leon
 */
public class ExcelSheet implements TDSheet {

	private Sheet sheet;
	private Workbook book;

	public ExcelSheet(Sheet sheet, Workbook book) {
		this.sheet = sheet;
		this.book = book;
	}

	@Override
	public String getName() {
		return sheet.getSheetName();
	}

	@Override
	public int getIndex() {
		return book.getSheetIndex(sheet) + 1;
	}

	@Override
	public Sheet getRaw() {
		return sheet;
	}

}

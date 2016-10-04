package cn.heroes.yellow.parser.impl;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.TDSheet;
import cn.heroes.yellow.entity.impl.ExcelRow;
import cn.heroes.yellow.entity.impl.ExcelSheet;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.exception.UnParsedException;
import cn.heroes.yellow.parser.NTDParser;

/**
 * Parse the rows in every sheet of Excel file.
 * 
 * @author Leon Kidd
 * @version 1.00, 2016-10-4
 */
public class ExcelParser implements NTDParser<Workbook> {

	private Workbook book = null;
	private Iterator<Row> rows = null;
	private int numberOfSheets = 0;
	private int index = 0;

	@Override
	public Workbook parse(InputStream is) throws ParsingException {
		try {
			// 创建相应版本Excel的Workbook(2003-2007)
			book = ExcelUtils.create(is);
			numberOfSheets = book.getNumberOfSheets();
		} catch (Exception e) {
			throw new ParsingException("Create Workbook from input stream error.", e);
		}
		return book;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public TDSheet sheet() {
		if (index < numberOfSheets) {
			Sheet sheet = book.getSheetAt(index++);
			// 迭代Row
			rows = sheet.rowIterator();
			return new ExcelSheet(sheet, book);
		}
		return null;
	}

	@Override
	public TDRow next() throws UnParsedException {
		// UnParsed
		if (rows == null)
			throw new UnParsedException();

		if (rows.hasNext()) {
			Row row = rows.next();
			return new ExcelRow(row, book);
		} else {
			return null;
		}
	}

}
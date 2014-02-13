package cn.heroes.yellow.parser.impl;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.ExcelRow;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.exception.UnParsedException;
import cn.heroes.yellow.parser.TDParser;

/**
 * 分析Excel文件指定的一个Sheet的TDParser的实现, 默认取第一个Sheet(即Sheet 0)
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-5
 * @since 1.0
 */
public class SheetNParser implements TDParser {

	private Iterator<Row> rows = null;

	private String sheetName;
	private int sheetIndex = 0;
	private Workbook book;

	/**
	 * 默认第一个sheet,即 sheet 0
	 */
	public SheetNParser() {
	}

	/**
	 * 
	 * @param sheetName
	 *            指定的Sheet name
	 */
	public SheetNParser(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 
	 * @param sheetIndex
	 *            指定的Sheet index, 0-based
	 */
	public SheetNParser(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	@Override
	public Void parse(InputStream is) throws ParsingException {
		try {
			// 创建相应版本Excel的Workbook(2003-2007)
			book = ExcelUtils.create(is);

			// Get Sheet
			Sheet sheet = null;
			if (sheetName == null) {
				sheet = book.getSheetAt(sheetIndex);
			} else {
				sheet = book.getSheet(sheetName);
			}

			// 迭代Row
			rows = sheet.rowIterator();
		} catch (Exception e) {
			throw new ParsingException(
					"Create Workbook from input stream error.", e);
		}
		return null;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

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

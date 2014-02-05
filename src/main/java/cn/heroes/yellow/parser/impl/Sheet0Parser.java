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
 * 分析Excel文件第一个Sheet的TDParser的实现。
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-5
 * @since 1.0
 */
public class Sheet0Parser implements TDParser {

	private Iterator<Row> rows = null;

	@Override
	public Void parse(InputStream is) throws ParsingException {
		Workbook book;
		try {
			// 创建相应版本Excel的Workbook(2003-2007)
			book = ExcelUtils.create(is);

			// Get Sheet0
			Sheet sheet = book.getSheetAt(0);

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
			return new ExcelRow(row);
		} else {
			return null;
		}
	}

}

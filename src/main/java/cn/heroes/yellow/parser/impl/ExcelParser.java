package cn.heroes.yellow.parser.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.entity.TDPage;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.ExcelRow;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.parser.NTDParser;

/**
 * 基于Excel的解析器实现.
 * <p>
 * 迭代Sheet
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 * TODO 写出问题sf.fill(source, fo.getOutputStream());
 */
public class ExcelParser implements NTDParser {

	/** POI Work book */
	private Workbook book = null;

	/**
	 * @return The iterable TDPage list, maybe empty but never null.
	 */
	@Override
	public Iterator<TDPage> parse(InputStream is) throws ParsingException {
		ArrayList<TDPage> list = new ArrayList<TDPage>();
		try {
			book = ExcelUtils.create(is);
			int numberOfSheets = book.getNumberOfSheets();
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = book.getSheetAt(i);
				ExcelTDPage page = new ExcelTDPage(sheet);
				list.add(page);
			}
		} catch (Exception e) {
			throw new ParsingException("Error when create POI Workbook", e);
		}
		return list.iterator();
	}

	/**
	 * TDpage implementation, deal with every sheet.
	 * 
	 * @author Leon Kidd
	 * @version 1.00, 2014-2-16
	 * @since 1.0
	 * @see cn.heroes.yellow.parser.impl.SheetNParser
	 */
	class ExcelTDPage implements TDPage {

		private Sheet sheet;
		private Iterator<Row> rows;

		public ExcelTDPage(Sheet sheet) {
			this.sheet = sheet;
			rows = sheet.rowIterator();
		}

		@Override
		public String getName() {
			return sheet.getSheetName();
		}

		@Override
		public TDRow next() {
			if (rows.hasNext()) {
				Row row = rows.next();
				return new ExcelRow(row, book);
			} else {
				return null;
			}
		}

	}

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

}
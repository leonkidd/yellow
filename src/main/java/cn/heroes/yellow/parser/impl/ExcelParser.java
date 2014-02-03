package cn.heroes.yellow.parser.impl;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.ExcelRow;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.exception.UnParsedException;
import cn.heroes.yellow.parser.TDParser;

/**
 * 对于Excel的二维表解析器的实现.
 * <p>
 * 对于Sheet的处理可以看似所有Sheet按序号从小到大进行解析, 当上一张结束或<code>end</code>后再进行下一张.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class ExcelParser implements TDParser {

	private static final Logger logger = LoggerFactory
			.getLogger(ExcelParser.class);

	@Override
	public void init() {
		logger.info("ExcelParser 初始化成功.");
	}

	@Override
	public void destroy() {
		logger.info("ExcelParser 销毁成功.");
	}

	/** POI Work book */
	private Workbook book = null;
	/** 当前正在迭代的Sheet */
	private Sheet sheet = null;

	@Override
	public Void parse(InputStream is, FileInfo info) throws ParsingException {
		try {
			book = ExcelUtils.create(is);
			int numberOfSheets = book.getNumberOfSheets();

		} catch (Exception e) {
			throw new ParsingException("Error when create POI Workbook", e);
		}
		return null;
	}

	@Override
	public TDRow next() throws UnParsedException {
		Iterator<Row> rows = sheet.rowIterator();
		boolean hasNext = rows.hasNext();
		Row row = rows.next();
		// TODO
		ExcelRow excelRow = new ExcelRow(row);
		return excelRow;
	}

}
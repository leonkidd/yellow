package cn.heroes.yellow.parser.impl;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.parser.ExcelParser;

/**
 * 基于Excel的解析器实现.
 * <p>
 * 迭代Sheet
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class ExcelParserImpl implements ExcelParser {

	private static final Logger logger = LoggerFactory
			.getLogger(ExcelParserImpl.class);

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
	public Iterator<Sheet> parse(InputStream is) throws ParsingException {
		try {
			book = ExcelUtils.create(is);
			int numberOfSheets = book.getNumberOfSheets();

		} catch (Exception e) {
			throw new ParsingException("Error when create POI Workbook", e);
		}
		return null;
	}

}
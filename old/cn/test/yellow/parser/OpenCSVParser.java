package cn.yhhh.fairy.yellow.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import cn.heroes.jkit.utils.TextUtils;
import cn.yhhh.fairy.yellow.entity.DelRow;
import cn.yhhh.fairy.yellow.entity.Row;

public class OpenCSVParser implements Parser {

	private static final Logger logger = LoggerFactory
			.getLogger(OpenCSVParser.class);

	private CSVReader reader;
	private char separator = CSVParser.DEFAULT_SEPARATOR;
	private char quotechar = CSVParser.DEFAULT_QUOTE_CHARACTER;

	public OpenCSVParser() {
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 * 
	 * @param separator
	 *            the delimiter to use for separating entries
	 */
	public OpenCSVParser(char separator) {
		this.separator = separator;
	}

	/**
	 * Constructs CSVReader with supplied separator and quote char.
	 * 
	 * @param separator
	 *            the delimiter to use for separating entries
	 * @param quotechar
	 *            the character to use for quoted elements
	 */
	public OpenCSVParser(char separator, char quotechar) {
		this.separator = separator;
		this.quotechar = quotechar;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {
		try {
			reader.close();
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	@Override
	public void parser(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis,
					TextUtils.getCharset(file));
			reader = new CSVReader(isr, separator, quotechar);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Override
	public Row next() {
		DelRow row = null;
		try {
			String[] values = reader.readNext();
			if (values != null) {
				row = new DelRow();
				row.setValues(values);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return row;
	}

	@Override
	public void parser(File file, Object id) {
		parser(file);
	}

}

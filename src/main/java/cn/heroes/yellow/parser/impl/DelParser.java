package cn.heroes.yellow.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.DelRow;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.exception.UnParsedException;
import cn.heroes.yellow.parser.TDParser;

/**
 * 基于DEL、CSV文件的TD解析器实现，底层实现由opencsv完成.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-17
 */
public class DelParser implements TDParser<Void> {

	private static final Logger logger = LoggerFactory
			.getLogger(DelParser.class);

	private CSVReader reader = null;
	private int rowNum = 1;
	private String charset = "GBK";

	public DelParser() {
	}

	public DelParser(String charset) {
		this.charset = charset;
	}

	@Override
	public Void parse(InputStream is) throws ParsingException {
		try {
			reader = new CSVReader(new InputStreamReader(is, charset));
		} catch (UnsupportedEncodingException e) {
			logger.error("The charset name {} is unsupported.", charset);
			throw new ParsingException(e);
		}
		return null;
	}

	@Override
	public void init() {
		// 清理资源
		rowNum = 1;
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			logger.error("Error to close InputStream.", e);
		}
	}

	@Override
	public void destroy() {
		try {
			if (reader != null)
				reader.close();
		} catch (IOException e) {
			logger.error("Error to close InputStream.", e);
		}
	}

	@Override
	public TDRow next() throws UnParsedException {
		if (reader == null) {
			throw new UnParsedException();
		}
		try {
			String[] ss = reader.readNext();
			if (ss != null) {
				return new DelRow(ss, rowNum++);
			} else {
				return null;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

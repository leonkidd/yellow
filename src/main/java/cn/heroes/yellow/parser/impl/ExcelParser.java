package cn.heroes.yellow.parser.impl;

import java.io.InputStream;
import java.util.Iterator;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.parser.TDParser;

public class ExcelParser implements TDParser {

	@Override
	public Iterator<TDRow> parse(InputStream is) throws ParsingException {
		// 读入is
		
		return null;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void destroy() {
		
	}

}
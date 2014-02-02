package cn.heroes.yellow.parser.impl;

import java.io.InputStream;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.exception.UnParsedException;
import cn.heroes.yellow.parser.TDParser;

public class ExcelParser implements TDParser {

	@Override
	public void init() {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public Void parse(InputStream is) throws ParsingException {
		return null;
	}

	@Override
	public TDRow next() throws UnParsedException {
		return null;
	}

}
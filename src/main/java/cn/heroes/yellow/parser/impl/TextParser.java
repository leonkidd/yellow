package cn.heroes.yellow.parser.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.parser.FullTextParser;

/**
 * Text file parse<br/>
 * 文本文件解析
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 */
public class TextParser implements FullTextParser {

	private String encoding;

	public TextParser(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public String parse(InputStream is) throws ParsingException {
		try {
			return IOUtils.toString(is, encoding);
		} catch (IOException e) {
			throw new ParsingException(e);
		}
	}

}

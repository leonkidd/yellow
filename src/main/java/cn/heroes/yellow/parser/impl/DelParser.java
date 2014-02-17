package cn.heroes.yellow.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.skife.csv.SimpleReader;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.DelRow;
import cn.heroes.yellow.exception.ParsingException;
import cn.heroes.yellow.exception.UnParsedException;
import cn.heroes.yellow.parser.TDParser;

/**
 * TODO 未完成
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-17
 */
public class DelParser implements TDParser {

	private boolean cache;
	private List<?> data;
	private int index = 0;

	/**
	 * 
	 * @param cache
	 *            Should we read the I/O once and cache the data in memory,
	 *            false if it's a large file.
	 */
	public DelParser(boolean cache) {
		// TODO until apache commons csv 1.0 released
		cache = true;
		this.cache = cache;
	}

	@Override
	public Void parse(InputStream is) throws ParsingException {
		SimpleReader reader = new SimpleReader();
		try {
			if (cache) {
				data = reader.parse(is);
			} else {
				// TODO until apache commons csv 1.0 released
			}
		} catch (IOException e) {
			throw new ParsingException(e);
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
		String[] os = (String[])data.get(index);
		index++;
		return new DelRow(os);
	}

}

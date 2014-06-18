package cn.heroes.yellow.entity.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.entity.TDRow;

/**
 * 基于DEL的TDRow的实现.
 * <p>
 * Row, Column are all 1-based.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-17
 */
public class DelRow implements TDRow {

	private static final Logger logger = LoggerFactory.getLogger(DelRow.class);

	private String[] ss;
	private int rowNum;

	/**
	 * Row, Column are all 1-based.
	 * 
	 * @param ss
	 * @param rowNum
	 */
	public DelRow(String[] ss, int rowNum) {
		this.ss = ss;
		this.rowNum = rowNum;
	}

	@Override
	public Object getObject(int i) {
		return getString(i);
	}

	@Override
	public String getString(int i) {
		try {
			return ss[i - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("The error row number is " + i);
			throw new RuntimeException(e);
		}
	}

	@Override
	public double getDouble(int i) {
		return Double.valueOf(getString(i));
	}

	@Override
	public float getFloat(int i) {
		return Float.valueOf(getString(i));
	}

	@Override
	public long getLong(int i) {
		return Long.valueOf(getString(i));
	}

	@Override
	public int getInt(int i) {
		return Integer.valueOf(getString(i));
	}

	@Override
	public int length() {
		return ss.length;
	}

	@Override
	public int getRowNum() {
		return rowNum;
	}

	@Override
	public Object[] getValues() {
		return ss;
	}

}

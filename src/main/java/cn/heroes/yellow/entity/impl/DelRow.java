package cn.heroes.yellow.entity.impl;

import cn.heroes.yellow.entity.TDRow;

/**
 * TODO
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-17
 */
public class DelRow implements TDRow {
	
	private String[] ss;
	
	public DelRow(String[] ss) {
		this.ss = ss;
	}

	@Override
	public Object getObject(int i) {
		return ss[i - 1];
	}

	@Override
	public String getString(int i) {
		return ss[i - 1];
	}

	@Override
	public double getDouble(int i) {
		return 0;
	}

	@Override
	public float getFloat(int i) {
		return 0;
	}

	@Override
	public long getLong(int i) {
		return 0;
	}

	@Override
	public int getInt(int i) {
		return 0;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public int getRowNum() {
		return 0;
	}

	@Override
	public Object[] getValues() {
		return null;
	}

}

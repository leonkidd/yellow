package cn.yhhh.fairy.yellow.entity;

public class SimpleRow implements Row {

	private long rowNum;
	private Object[] values;
	
	public Object getObject(int i) {
		return values[i];
	}

	public String getString(int i) {
		return (String) values[i];
	}

	public double getDouble(int i) {
		return (Double) values[i];
	}

	public float getFloat(int i) {
		return (Float) values[i];
	}

	public long getLong(int i) {
		return (Long) values[i];
	}

	public int getInt(int i) {
		return (Integer) values[i];
	}

	public int length() {
		return values.length;
	}

	public long getRowNum() {
		return rowNum;
	}

	public Object[] getValues() {
		return values;
	}

	public void setRowNum(long rowNum) {
		this.rowNum = rowNum;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

}

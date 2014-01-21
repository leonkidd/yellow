package cn.yhhh.fairy.yellow.entity;

public class DelRow implements Row {

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
		return ((Double) values[i]).floatValue();
	}

	public long getLong(int i) {
		return ((Double) values[i]).longValue();
	}

	public int getInt(int i) {
		// TODO Exception
		return Integer.parseInt((String) values[i]);
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

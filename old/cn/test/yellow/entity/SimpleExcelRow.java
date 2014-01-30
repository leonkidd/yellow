package cn.yhhh.fairy.yellow.entity;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cn.heroes.jkit.poi.ExcelHelper;

public class SimpleExcelRow implements cn.yhhh.fairy.yellow.entity.Row {

	private Row row;

	public SimpleExcelRow() {

	}

	public SimpleExcelRow(Row row) {
		this.row = row;
	}

	@Override
	public Object getObject(int i) {
		return ExcelHelper.getCellValue(row.getCell(i));
	}

	@Override
	public String getString(int i) {
		Cell cell = row.getCell(i);
		if(cell == null) return "";
		else return cell.getStringCellValue();
	}

	@Override
	public double getDouble(int i) {
		return row.getCell(i).getNumericCellValue();
	}

	@Override
	public float getFloat(int i) {
		return (float) row.getCell(i).getNumericCellValue();
	}

	@Override
	public long getLong(int i) {
		return (long) row.getCell(i).getNumericCellValue();
	}

	@Override
	public int getInt(int i) {
		return (int) row.getCell(i).getNumericCellValue();
	}

	@Override
	public int length() {
		// TODO row.getPhysicalNumberOfCells();
		return row.getLastCellNum();
	}

	@Override
	public long getRowNum() {
		return row.getRowNum() + 1;
	}

	@Override
	public Object[] getValues() {
		String[] values = new String[(int) getRowNum()];
		Iterator<Cell> cells = row.cellIterator();
		int i = 0;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			values[i++] = cell.getStringCellValue();
		}
		return values;
	}

}

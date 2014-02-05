package cn.heroes.yellow.entity.impl;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.exception.CellNotFoundException;

/**
 * 基于Excel的TDRow的实现.
 * <p>
 * Row, Cell are all 1-based.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class ExcelRow implements TDRow {

	private Row row;

	/**
	 * Row and cell are all 1-based.
	 * 
	 * @param row
	 *            <code>org.apache.poi.ss.usermodel.Row</code>
	 * @see org.apache.poi.ss.usermodel.Row
	 */
	public ExcelRow(Row row) {
		this.row = row;
	}

	/**
	 * 统一的获取cell方法
	 * 
	 * @param i
	 *            1-based column number
	 * @throws CellNotFoundException
	 *             当cell为null.
	 * @return
	 */
	private Cell cell(int i) {
		Cell cell = row.getCell(i - 1);
		// if (cell == null) {
		// throw new CellNotFoundException();
		// }
		return cell;
	}

	@Override
	public Object getObject(int i) {
		Cell cell = cell(i);
		return cell == null ? null : ExcelUtils.getCellValue(cell);
	}

	@Override
	public String getString(int i) {
		Cell cell = cell(i);
		return cell == null ? "" : cell.getStringCellValue();
	}

	@Override
	public double getDouble(int i) {
		Cell cell = cell(i);
		return cell == null ? 0 : cell.getNumericCellValue();
	}

	@Override
	public float getFloat(int i) {
		// TODO 强制转化可以吗?
		Cell cell = cell(i);
		return cell == null ? 0 : (float) cell.getNumericCellValue();
	}

	@Override
	public long getLong(int i) {
		Cell cell = cell(i);
		return cell == null ? 0 : (long) cell.getNumericCellValue();
	}

	@Override
	public int getInt(int i) {
		Cell cell = cell(i);
		return cell == null ? 0 : (int) cell.getNumericCellValue();
	}

	@Override
	public int length() {
		return row.getLastCellNum() + 1;
	}

	@Override
	public long getRowNum() {
		return row.getRowNum() + 1;
	}

	@Override
	public Object[] getValues() {
		Object[] values = new Object[(int) getRowNum()];
		Iterator<Cell> cells = row.cellIterator();
		int i = 0;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			values[i++] = ExcelUtils.getCellValue(cell);
		}
		return values;
	}

}

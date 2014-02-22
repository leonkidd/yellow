package cn.heroes.yellow.entity.impl;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.jkit.utils.exception.EvaluateFormulaException;
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
	private Workbook book;

	/**
	 * Row and cell are all 1-based.
	 * 
	 * @param row
	 *            <code>org.apache.poi.ss.usermodel.Row</code>
	 * @param book
	 *            if book is null, then the FORMULA cell will return FORMULA
	 *            String, else the FORMULA cell will return the value evaluated.
	 *            <code>org.apache.poi.ss.usermodel.Workbook</code>
	 * @see org.apache.poi.ss.usermodel.Row
	 */
	public ExcelRow(Row row, Workbook book) {
		this.row = row;
		this.book = book;
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

	/**
	 * @throws EvaluateFormulaException
	 *             When the formula is evaluated error.
	 */
	@Override
	public Object getObject(int i) {
		Cell cell = cell(i);
		return cell == null ? null : ExcelUtils.getCellValue(cell, book);
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
	public int getRowNum() {
		return row.getRowNum() + 1;
	}

	@Override
	public Object[] getValues() {
		Object[] values = new Object[(int) row.getLastCellNum()];
		Iterator<Cell> cells = row.cellIterator();
		int i = 0;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			if (book != null) {
				values[i++] = ExcelUtils.getCellValue(cell, book);
			} else {
				values[i++] = ExcelUtils.getCellValue(cell);
			}
		}
		return values;
	}

	/**
	 * Get the specified cell's style.
	 * 
	 * @param i
	 *            1-based, the first column is 1, the second is 2, ...
	 * @return
	 */
	public CellStyle getCellStyle(int i) {
		Cell cell = row.getCell(i);
		return cell.getCellStyle();
	}
}

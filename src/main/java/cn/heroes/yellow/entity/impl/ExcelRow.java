package cn.heroes.yellow.entity.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.jkit.utils.exception.EvaluateFormulaException;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.exception.FeatureNotSupportedException;

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
	 *            String with head "=", else the FORMULA cell will return the
	 *            value evaluated.
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
	 * @return
	 */
//	 * @throws CellNotFoundException 当cell为null.
	private Cell cell(int i) {
		Cell cell = row.getCell(i - 1);
		if (cell == null) {
			cell = row.createCell(i - 1);
		}
		return cell;
	}

	/**
	 * If this is a FORMULA cell and the <tt>book</tt> in constructor is not
	 * null, the formula will be evaluated and returned, otherwise return
	 * FORMULA String with "=" head. When the formula is evaluated error, the
	 * formula string with head "=" will be returned.
	 */
	@Override
	public Object getObject(int i) {
		Cell cell = cell(i);
		try {
			if (cell == null) {
				return null;
			} else if (book == null) {
				return ExcelUtils.getCellValue(cell);
			} else {
				return ExcelUtils.getCellValue(cell, book);
			}
		} catch (EvaluateFormulaException e) {
			return "=" + cell.getCellFormula();
		}
	}

	/**
	 * @param i
	 *            A ALPHA-26 String of column reference, 1-based. 'A' -> 1 'Z'
	 *            -> 26 'AA' -> 27 'IV' -> 256
	 * 
	 * @see #getObject(int)
	 */
	public Object getObject(String i) {
		return getObject(CellReference.convertColStringToIndex(i) + 1);
	}

	/**
	 * If this is a FORMULA cell, the FORMULA String with "=" head will be
	 * returned.
	 */
	@Override
	public String getString(int i) {
		Cell cell = cell(i);
		if (cell == null) {
			return "";
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
			return "=" + cell.getCellFormula();
		} else {
			return cell.getStringCellValue();
		}
	}

	/**
	 * @param i
	 *            A ALPHA-26 String of column reference, 1-based. 'A' -> 1 'Z'
	 *            -> 26 'AA' -> 27 'IV' -> 256
	 * 
	 * @see #getString(int)
	 */
	public Object getString(String i) {
		return getString(CellReference.convertColStringToIndex(i) + 1);
	}

	@Override
	public double getDouble(int i) {
		Cell cell = cell(i);
		return cell == null ? 0 : cell.getNumericCellValue();
	}

	/**
	 * @param i
	 *            A ALPHA-26 String of column reference, 1-based. 'A' -> 1 'Z'
	 *            -> 26 'AA' -> 27 'IV' -> 256
	 * 
	 * @see #getDouble(int)
	 */
	public Object getDouble(String i) {
		return getDouble(CellReference.convertColStringToIndex(i) + 1);
	}

	@Override
	public float getFloat(int i) {
		// TODO 强制转化可以吗?
		Cell cell = cell(i);
		return cell == null ? 0 : (float) cell.getNumericCellValue();
	}

	/**
	 * @param i
	 *            A ALPHA-26 String of column reference, 1-based. 'A' -> 1 'Z'
	 *            -> 26 'AA' -> 27 'IV' -> 256
	 * 
	 * @see #getFloat(int)
	 */
	public Object getFloat(String i) {
		return getFloat(CellReference.convertColStringToIndex(i) + 1);
	}

	@Override
	public long getLong(int i) {
		Cell cell = cell(i);
		return cell == null ? 0 : (long) cell.getNumericCellValue();
	}

	/**
	 * @param i
	 *            A ALPHA-26 String of column reference, 1-based. 'A' -> 1 'Z'
	 *            -> 26 'AA' -> 27 'IV' -> 256
	 * 
	 * @see #getLong(int)
	 */
	public Object getLong(String i) {
		return getLong(CellReference.convertColStringToIndex(i) + 1);
	}

	@Override
	public int getInt(int i) {
		Cell cell = cell(i);
		return cell == null ? 0 : (int) cell.getNumericCellValue();
	}

	/**
	 * @param i
	 *            A ALPHA-26 String of column reference, 1-based. 'A' -> 1 'Z'
	 *            -> 26 'AA' -> 27 'IV' -> 256
	 * 
	 * @see #getInt(int)
	 */
	public Object getInt(String i) {
		return getInt(CellReference.convertColStringToIndex(i) + 1);
	}

	@Override
	public int length() {
		return row.getLastCellNum();
	}

	@Override
	public int getRowNum() {
		return row.getRowNum() + 1;
	}

	/**
	 * @throws EvaluateFormulaException
	 *             When the formula is evaluated error.
	 */
	@Override
	public Object[] getValues() {
		short cellnum = row.getLastCellNum();
		Object[] values = new Object[(int) cellnum];
		for(int i = 0; i < cellnum; i++) {
			Cell cell = row.getCell(i);
			if(cell == null) {
				continue;
			}
			
			Object value = null;
			try {
				if (book != null) {
					value = ExcelUtils.getCellValue(cell, book);
				} else {
					value = ExcelUtils.getCellValue(cell);
				}
			} catch (EvaluateFormulaException e) {
				value = "=" + cell.getCellFormula();
			}
			values[i] = value;
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

	@Override
	public void setValue(int i, Object value)
			throws FeatureNotSupportedException {
		Cell cell = cell(i);
		ExcelUtils.setCellValue(cell, value);
	}

}

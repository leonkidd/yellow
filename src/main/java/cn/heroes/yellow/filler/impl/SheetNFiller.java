package cn.heroes.yellow.filler.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.filler.TDFiller;

/**
 * Excel specified sheet filler with template. The data will be inserted in the
 * specified sheet, and will be inserted after the row with the <code>$</code>
 * mark in the first cell(cell no. is 1). If there is no <code>$</code> mark,
 * the data will be filled after the end of sheet.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-22
 */
public class SheetNFiller implements TDFiller {

	private static final Logger logger = LoggerFactory
			.getLogger(SheetNFiller.class);

	private static final String CODE_PREFIX = "$";

	private Workbook basebook;
	private String sheetName;
	private int sheetIndex = 0;

	/**
	 * Create a blank Excel file with default sheet name.
	 */
	public SheetNFiller() {
		basebook = new HSSFWorkbook();
	}

	/**
	 * Create a blank Excel file with the specified sheet name.
	 * 
	 * @param sheetName
	 */
	public SheetNFiller(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * 
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file.
	 * @param sheetIndex
	 *            the specified sheet's index, 0-based.
	 */
	public SheetNFiller(File template, int sheetIndex) {
		if (template == null) {
			throw new RuntimeException("Template file cannot be NULL.");
		}
		createWorkbook(template);
		this.sheetIndex = sheetIndex;
	}

	/**
	 * 
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file. <code>NULL</code> if want a blank
	 *            Excel file.
	 * @param sheetName
	 *            the specified sheet's name.
	 */
	public SheetNFiller(File template, String sheetName) {
		createWorkbook(template);
		this.sheetName = sheetName;
	}

	private void createWorkbook(File template) {
		// create book if there is no template.
		if (template == null) {
			basebook = new HSSFWorkbook();
		} else {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(template);
				basebook = ExcelUtils.create(fis);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file.
	 * @param sheetIndex
	 *            the specified sheet's index, 0-based.
	 */
	public SheetNFiller(InputStream template, int sheetIndex) {
		if (template == null) {
			throw new RuntimeException("Template file cannot be NULL.");
		}
		createWorkbook(template);
		this.sheetIndex = sheetIndex;
	}

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file. <code>NULL</code> if want a blank
	 *            Excel file.
	 * @param sheetName
	 *            the specified sheet's name.
	 */
	public SheetNFiller(InputStream template, String sheetName) {
		createWorkbook(template);
		this.sheetName = sheetName;
	}

	private void createWorkbook(InputStream template) {
		if (template == null) {
			basebook = new HSSFWorkbook();
		} else {
			try {
				basebook = ExcelUtils.create(template);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void fill(List<Object[]> data, OutputStream output) {
		Workbook book = (Workbook)ObjectUtils.clone(basebook);
		// get Sheet with the specified name or index.
		Sheet sheet = null;
		try {
			if (sheetName != null) {
				sheet = book.getSheet(sheetName);
			} else {
				sheet = book.getSheetAt(sheetIndex);
			}
		} catch(Exception e) {
			// do nothing
		}
		// create Sheet1 when there is no Sheet
		if (sheet == null) {
			sheet = book.createSheet("Sheet1");
		}

		// 开始Fill的行号
		int beginRowNum = 0;

		// 迭代row
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			// 始终保持在目前这行的下一行
			// 这样当没有$标识出现的时候,就在最后一行下开始Fill
			beginRowNum++;

			Row row = rows.next();
			// Get first cell.
			Cell cell = row.getCell(0);
			if (cell == null) {
				continue;
			}

			Object cellValue = ExcelUtils.getCellValue(cell);
			// 是否具有"开始fill"标识的单元格
			if (cellValue != null
					&& cellValue.toString().startsWith(CODE_PREFIX)) {
				beginRowNum = row.getRowNum();
				// 删除有标识的那一行
				sheet.removeRow(row);
				break;
			}
		}

		// core code
		// TODO 文件尾部 | 有些文件本身就占了65535行空行满
		for (int i = 0; i < data.size(); i++) {
			Object[] os = data.get(i);
			Row row = sheet.createRow(beginRowNum + i);

			for (int j = 0; j < os.length; j++) {
				Cell cell = row.createCell(j);
				try {
					ExcelUtils.setCellValue(cell, os[j]);
				} catch (FormulaParseException e) {
					// set as string
					logger.warn(
							"Formula [{}] in cell [row: {}, col: {}] (1-based) is error, set as string already.",
							os[j], i + 1, j + 1);
					cell.setCellValue(os[j].toString());
				}
			}
		}

		// 输出结果Excel
		try {
			book.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

}

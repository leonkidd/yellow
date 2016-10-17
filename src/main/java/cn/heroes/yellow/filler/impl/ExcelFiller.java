package cn.heroes.yellow.filler.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.filler.NTDFiller;

/**
 * Excel total sheets Filler with template. The data will be inserted after the
 * row with the <code>$</code> mark in the first cell(cell no. is 1). If there
 * is no <code>$</code> mark, the data will be filled after the end of sheet.
 * 
 * @author Leon Kidd
 * @version 1.00, 2016-10-10
 */
public class ExcelFiller implements NTDFiller {

	private static final Logger logger = LoggerFactory
			.getLogger(ExcelFiller.class);
	// Mark to begin filling
	private static final String CODE_PREFIX = "$";

	/** template file byte array */
	private byte[] bs = null;

	/**
	 * Create a blank Excel file.
	 */
	public ExcelFiller() {
	}

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file.
	 */
	public ExcelFiller(File template) {
		createWorkbook(null, template);
	}

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file.
	 */
	public ExcelFiller(InputStream template) {
		createWorkbook(template, null);
	}

	private void createWorkbook(InputStream is, File file) {
		try {
			// Keep the template byte array in memory
			if (is != null) {
				bs = IOUtils.toByteArray(is);
			} else if (file != null) {
				bs = FileUtils.readFileToByteArray(file);
			} else {
				bs = null;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void fill(Map<String, List<Object[]>> data, OutputStream os) {
		Workbook book = null;
		Sheet sheet = null;

		// create workbook before every fill.
		if (bs != null) {
			try {
				book = new XSSFWorkbook(new ByteArrayInputStream(bs));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			book = new XSSFWorkbook();
		}

		// iterate data
		Set<String> keys = data.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			List<Object[]> list = data.get(key);

			// Get Sheet with the specified name or create one.
			try {
				sheet = book.getSheet(key);
				if (sheet == null) {
					sheet = book.createSheet(key);
				}
			} catch (Exception e) {
				// do nothing
			}

			// 开始Fill的行号
			int beginRowNum = 0;

			// iterate rows to find begin mark
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

			// Fill
			// TODO 文件尾部 | 有些文件本身就占了65535行空行满
			for (int i = 0; i < list.size(); i++) {
				Object[] obs = list.get(i);
				Row row = sheet.createRow(beginRowNum + i);

				for (int j = 0; j < obs.length; j++) {
					Cell cell = row.createCell(j);
					try {
						ExcelUtils.setCellValue(cell, obs[j]);
					} catch (FormulaParseException e) {
						// set as string
						logger.warn(
								"Formula [{}] in cell [row: {}, col: {}] (1-based) is error, set as string already.",
								obs[j], i + 1, j + 1);
						cell.setCellValue(obs[j].toString());
					}
				}
			}
		}

		// 输出结果Excel
		try {
			book.write(os);
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

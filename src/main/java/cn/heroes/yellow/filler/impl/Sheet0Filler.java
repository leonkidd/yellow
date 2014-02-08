package cn.heroes.yellow.filler.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.filler.TDFiller;

/**
 * Excel first sheet Filler with template excel file. The data will be filled
 * begin from the <code>$</code> mark in the first (cell number is 0) cell of template file.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-8
 */
public class Sheet0Filler implements TDFiller<Object> {
	
	private static final String CODE_PREFIX = "$";

	private File template;

	/**
	 * Choose a template to fill data.
	 * 
	 * @param template
	 *            the Excel template file. <code>NULL</code> if want a blank
	 *            Excel file.
	 */
	public Sheet0Filler(File template) {
		this.template = template;
	}

	@Override
	public void fill(List<Object[]> data, Info info) {
		// 创建 book
		Workbook book = null;
		if (template == null) {
			try {
				FileInputStream fis = new FileInputStream(template);
				book = ExcelUtils.create(fis);
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			book = new HSSFWorkbook();
		}

		// 创建Sheet
		Sheet sheet = book.getSheetAt(0);
		if (sheet == null) {
			sheet = book.createSheet("Sheet1");
		}
		
		// 开始Fill行号
		int beginRowNum = 0;
		
		// 迭代row
		Iterator<Row> rows = sheet.rowIterator();
		while(rows.hasNext()) {
			Row row = rows.next();
			
			// 迭代cell,查看是否存在"开始fill"的标识
			Iterator<Cell> cells = row.cellIterator();
			while(cells.hasNext()) {
				Cell cell = cells.next();
				if(cell == null) {
					continue;
				}
				Object cellValue = ExcelUtils.getCellValue(cell);
				// 是否具有"开始fill"标识的单元格
				if(cellValue != null && cellValue.toString().startsWith(CODE_PREFIX)) {
					beginRowNum = row.getRowNum();
					// 删除有标识的那一行
					sheet.removeRow(row);
					break;
				}
			}
		}
		
		// core code
		// TODO 文件尾部 | 65535行满
		for(int i = 0; i < data.size(); i++) {
			Object[] os = data.get(i);
			Row row = sheet.createRow(beginRowNum + i);
			
			for(int j = 0; j < os.length; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(os[j] == null ? "" : os[j].toString());
			}
		}

		// 输出文件
		FileInfo fi = (FileInfo) info;
		File output = fi.file;
		try {
			FileOutputStream fos = new FileOutputStream(output);
			book.write(fos);
			fos.close();
		} catch (Exception e) {
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

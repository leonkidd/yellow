package cn.yhhh.fairy.yellow.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import cn.yhhh.fairy.yellow.entity.SimpleExcelRow;

public class SimpleExcelParser implements Parser {

	private HSSFWorkbook book;

	private File file;

	public File getFile() {
		return file;
	}

	/** 当前有效行数,有1行则为1 */
	private long rownum = 0L;

	/** Buffer IO Reader */
	private Iterator<Row> rows;

	private FileInputStream fis;

	@Override
	public void init() {

	}

	@Override
	public void destroy() {
	}

	private void parser(File file, String id) {
		try {
			this.file = file;
			fis = new FileInputStream(file);
			POIFSFileSystem pfs = new POIFSFileSystem(fis);
			book = new HSSFWorkbook(pfs);
			HSSFSheet sheet = id == null ? book.getSheetAt(0) : book
					.getSheet(id);
			if (sheet == null) {
				throw new Exception("文件分析标识[" + id + "]不存在。");
			}
			rows = sheet.rowIterator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public cn.yhhh.fairy.yellow.entity.Row next() {
		if (rows.hasNext()) {
			rownum++;
			Row row = rows.next();
			return new SimpleExcelRow(row);
		} else {
			return null;
		}
	}

	public HSSFWorkbook getBook() {
		return book;
	}

	@Override
	public void parser(File file) {
		parser(file, null);
	}

	@Override
	public void parser(File file, Object id) {
		if (id instanceof String) {
			parser(file);
		} else {
			throw new RuntimeException("标识id类型有误，应该为java.lang.String。");
		}
	}

}

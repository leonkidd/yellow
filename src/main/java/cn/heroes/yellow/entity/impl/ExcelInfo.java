package cn.heroes.yellow.entity.impl;

import java.io.File;

/**
 * 拦截文件是(Excel)时的信息类
 * TODO 废除
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class ExcelInfo extends FileInfo {
	
	public ExcelInfo(File file) {
		super(file);
	}

	/**
	 * 拦截的的当前Sheet的Name
	 */
	public String sheetName;
}

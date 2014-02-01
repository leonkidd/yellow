package cn.yhhh.test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;

import cn.heroes.jkit.utils.ACallback;
import cn.heroes.jkit.utils.Callback;
import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.jkit.utils.FileUtils;
import cn.heroes.yellow.Yellow;
import cn.heroes.yellow.intercepter.Interceptor;
import cn.heroes.yellow.parser.impl.ExcelParser;

public class MyTest {

	@Before
	public void before() {

	}

	/**
	 * 模拟测试单元格式的开发<br/>
	 * 即通过编写好单元格名称（如: B2, C3）
	 */
	@Test
	public void cellTest() {
		// -------------  获取单元格名字列表
		// 模版文件
		File tempate = new File("test/cell/template.xls");
		try {
			Workbook book = WorkbookFactory.create(tempate);
			int numberOfSheets = book.getNumberOfSheets();

			// 存储单元格位置列表
			List<String> cellPoses = new ArrayList<String>();

			// 迭代Sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = book.getSheetAt(i);
				// 第四行
				Row row = sheet.getRow(3);
				if (row == null) {
					continue;
				}
				// 迭代单元格
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					// 单元格标识代码, e.g. $H2
					Object cellValue = ExcelUtils.getCellValue(cell);
					cellPoses.add(cellValue.toString());
				}
			}

			String[] array = cellPoses.toArray(new String[] {});
			ExcelParser parser = new ExcelParser();
			Interceptor interceptor = new MyTDCellIntercepter(array);

			final Yellow yellow = Yellow.build(parser, interceptor);

			// 要分析的文件所在目录
			File dir = new File("test/cell");
			FileUtils.recursion(dir, new FileFilter() {
				@Override
				public boolean accept(File file) {
					String filename = file.getName();
					return filename.endsWith(".xls") || filename.endsWith(".xlsx");
				}
			}, new ACallback() {
				@Override
				public void invoke(Object... t) {
					File file = (File)t[0];
					yellow.yellow(file);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * 要分析的目录 File dir = new File("test/cell"); FileUtils.recursion(dir,
		 * null, new Callback() {
		 * 
		 * @Override public void invoke(Object... t) { File file = (File)t[0];
		 * String parent = file.getParent();
		 * 
		 * // Excel 解析器 ExcelParser parser = new ExcelParser();
		 * 
		 * // 实现的单元格拦截器 MyTDCellIntercepter ic = new MyTDCellIntercepter();
		 * 
		 * // 创建Yellow对象 Yellow yellow = Yellow.build(parser, ic);
		 * 
		 * // 处理文件 yellow.yellow(file); }
		 * 
		 * @Override public void before(Object... t) { }
		 * 
		 * @Override public void after(Object... t) { } });
		 */
	}
}

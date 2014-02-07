package cn.yhhh.test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;

import cn.heroes.jkit.utils.ACallback;
import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.jkit.utils.FileUtils;
import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.Sheet0Parser;

public class MyTest {

	@Before
	public void before() {

	}

	//@Test
	public void cellPosesSort() {
		List<String> list = new ArrayList<String>();
		list.add("B1");
		list.add("C2");
		list.add("C1");
		list.add("A1");
		list.add("A3");
		list.add("B4");
		Collections.sort(list);
		for (String s : list) {
			System.out.println(s);
		}
	}

	/**
	 * 模拟测试单元格式的开发<br/>
	 * 即通过编写好单元格名称（如: B2, C3）
	 */
	/**
	 * 
	 */
	@Test
	public void cellTest() {
		// -------------  获取单元格名字列表
		// 模版文件
		File tempate = new File("test/template.xls");
		try {
			Workbook book = WorkbookFactory.create(tempate);
			int numberOfSheets = book.getNumberOfSheets();

			// 存储要分析的单元格位置列表, e.g. {"H2", "B2"}
			Set<String> cellPoses = new HashSet<String>();
			
			// 用于存储模版的各Sheet中，有指定$标识的单元格信息
			List<String[]> sheetCell = new ArrayList<String[]>(); 

			// 迭代Sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = book.getSheetAt(i);
				
				// 用于保存单元格位置信息, 认为列号不会超过26个.
				String[] ss = new String[26];
				
				// 第四行
				Row row = sheet.getRow(3);
				if (row == null) {
					continue;
				}
				// 迭代单元格
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					// 内容应为单元格标识代码, e.g. $H2
					Object cellValue = ExcelUtils.getCellValue(cell);
					if(cellValue == null) {
						continue;
					}
					String value = cellValue.toString();
					if (value.startsWith("$")) {
						String cellname = value.substring(1);
						ss[cell.getColumnIndex()] = cellname;
						cellPoses.add(cellname);
					}
				}
				
				sheetCell.add(ss);
			}

			TDParser parser = new Sheet0Parser(); //new ExcelParserImpl();
			// Singleton ? TODO
			final MyTDCellIntercepter interceptor = new MyTDCellIntercepter(cellPoses);
			// 保存各文件中解析出来的单元格内容信息Map{key: H2, value: 内容}, 一个Map是从一个文件中解析出来的.
			final List<Map<String, Object>> cs = new ArrayList<Map<String, Object>>();

			final Yellow yellow = new TDYellow(parser, interceptor, null);

			// 要分析的文件所在目录
			File dir = new File("test/cell");
			FileUtils.recursion(dir, new FileFilter() {
				@Override
				public boolean accept(File file) {
					String filename = file.getName();
					// return !filename.equals(".DS_Store");
					return filename.endsWith(".xls")
							|| filename.endsWith(".xlsx") || file.isDirectory();
				}
			}, new ACallback() {
				@Override
				public void invoke(Object... t) {
					File file = (File) t[0];
					yellow.yellow(file);
					
					Map<String, Object> cellDatas = interceptor.getCellDatas();
					cs.add(cellDatas);
				}
			});
			
			// 输出文件
			for(Map<String, Object> c : cs) {
				// 一个文件，一条记录， 一个c
				// TODO NOW
			}
			
			// do with sheetCell

			
			// 
//			int ns = book.getNumberOfSheets();
//			// 迭代Sheet
//			for (int i = 0; i < ns; i++) {
//				Sheet sheet = book.getSheetAt(i);
//				
//				String[] strings = sheetCell.get(i);
//				
//				// 用于保存单元格位置信息
//				String[] ss = new String[26];
//				
//				// 第四行
//				Row row = sheet.getRow(3);
//				if (row == null) {
//					continue;
//				}
//				// 迭代单元格
//				Iterator<Cell> cells = row.cellIterator();
//				while (cells.hasNext()) {
//					Cell cell = cells.next();
//					// 内容应为单元格标识代码, e.g. $H2
//					Object cellValue = ExcelUtils.getCellValue(cell);
//					if(cellValue == null) {
//						continue;
//					}
//					String value = cellValue.toString();
//					if (value.startsWith("$")) {
//						String cellname = value.substring(1);
//						cell.
//						cellPoses.add(cellname);
//					}
//				}
//			}
//			sheetCell
//			for(String[])

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

package cn.yhhh.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import cn.heroes.jkit.callback.ACallback;
import cn.heroes.jkit.utils.ExcelUtils;
import cn.heroes.jkit.utils.FileUtils;
import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.SheetNParser;

public class MyTest extends ACallback {
	private static MyTDCellIntercepter interceptor = null;
	private static Map<String, List<Object>> cs = null;
	private static Yellow yellow = null;
	// 存储要分析的单元格位置列表, e.g. {"H2", "B2"}
	private static Set<String> cellPoses = new HashSet<String>();

	/**
	 * 模拟测试单元格式的开发<br/>
	 * 即通过编写好单元格名称（如: B2, C3）
	 */
	public static void main(String[] args) {
		
		class MyCell {
			/** 列号, 0-based*/
			int col;
			String content;
			CellStyle style;
		}
		
		// -------------  获取单元格名字列表
		// 模版文件
		File tempate = new File("test/template.xls");
		try {
			Workbook book = WorkbookFactory.create(tempate);
			int numberOfSheets = book.getNumberOfSheets();

			// 用于存储模版的各Sheet中，有指定$标识的单元格信息。外层List是sheet，中层List是列
			List<List<MyCell>> sheetCell = new ArrayList<List<MyCell>>(); 

			// 迭代Sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = book.getSheetAt(i);
				
				// 用于保存单元格位置信息, 认为列号不会超过26个.
				// String[] ss = new String[26];
				List<MyCell> ss = new ArrayList<MyCell>();
				
				// 第四行
				Row row = sheet.getRow(3);
				if (row != null) {
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
							MyCell mc = new MyCell();
							mc.col = cell.getColumnIndex();
							mc.content = cellname;
							mc.style = cell.getCellStyle();
							ss.add(mc);
							cellPoses.add(cellname);
						}
					}
				}
				
				sheetCell.add(ss);
			}

			TDParser parser = new SheetNParser("Sheet1"); //new ExcelParserImpl();
			// Singleton ? TODO
			interceptor = new MyTDCellIntercepter(cellPoses);
			// 保存各文件中解析出来的单元格内容信息Map{key: H2, value: 内容}, 一个Map是从一个文件中解析出来的.
			//final List<Map<String, Object>> cs = new ArrayList<Map<String, Object>>();
			cs = new HashMap<String, List<Object>>();

			yellow = new TDYellow(parser, interceptor, null);
			
			MyTest mt = new MyTest();
			
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
			}, mt);

			// 另一种方法更佳：先将cs变成每个$H2一个列表（每个文件是列表中的一个记录）, 再次迭代Sheet，查看$标识再一次加完（也就是一开始取$的时候不用记录sheetCell）。
			// 两种方法结束，各取优点

			// 迭代Sheet
			for(int i = 0; i < sheetCell.size(); i++) {
				// 对应的sheet
				Sheet sheet = book.getSheetAt(i);
				
				// 各列中存的单元格位置名称
				List<MyCell> mcs = sheetCell.get(i);
				
				if(mcs.size() == 0) {
					// 没有特殊$单元格
					continue;
				}

				// 删除有标识的那一行
				sheet.removeRow(sheet.getRow(3));
				// 按分析文件的个数，创建Row
				for(int j = 0; j < length; j++) {
//					Row row = sheet.createRow(sheet.getLastRowNum() + 1);
					int rowNum = j + 3; // 0-based
					Row row = sheet.getRow(rowNum);
					if(row == null) row = sheet.createRow(rowNum);

					// 有标识单元格
					for(MyCell mc : mcs) {
						// 该单元格自身所在列号
						int col = mc.col;
						// 该单元格内容, e.g. H2
						String cellname = mc.content;
						// 单元格样式
						CellStyle style = mc.style;
						// 单元格内容所指向的单元格下所有文件记录
						List<Object> list = cs.get(cellname);

						Cell cell = row.createCell(col);
						// 设置单元格样式
						cell.setCellStyle(style);
						Object o = list.get(j);
						
						try {
							cell.setCellValue(Integer.parseInt(o.toString()));	
						} catch(Exception e) {
							cell.setCellValue(o == null ? "" : o.toString());
						}
					}
				}
			}
			
			
			// 保存文件
			FileOutputStream fos = new FileOutputStream("$.xls");
			book.write(fos);
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 分析的文件个数 */
	private static int length = 0;

	@Override
	public void invoke(Object... t) {
		File file = (File) t[0];
		yellow.yellow(file);
		length++;
		
		Map<String, Object> cellDatas = interceptor.getCellDatas();
		//cs.add(cellDatas);
		for(String cellPos : cellPoses) {
			Object value = cellDatas.get(cellPos);
			List<Object> list = cs.get(cellPos);
			if(list == null) {
				list = new ArrayList<Object>();
				cs.put(cellPos, list);
			}
			// if value is null, it still hold a space
			list.add(value);
		}
	}
}

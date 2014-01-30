package cn.yhhh.yellow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.skife.csv.CSVWriter;
import org.skife.csv.SimpleWriter;

import cn.heroes.jkit.utils.ExcelUtils;

public class Main {

	public static final Map<String, Double> map = new HashMap<String, Double>();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Main.recursion(new File("C:/Users/Leon/Desktop/业务质量考核扣分/支行检查"));
		
		FileWriter fw = new FileWriter("2.csv");
		CSVWriter writer = new SimpleWriter(fw);
		
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Double value = map.get(key);
			
			writer.append(new String[]{key, value.toString()});
		}
		
		fw.flush();
		fw.close();
		
	}

	/**
	 * 根据Excel文件版本的不同，创建不同版本的Workbook。
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static Workbook create(InputStream in) throws Exception {
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(in)) {
			return new HSSFWorkbook(in);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	}

	public static void core(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = create(fis);
		int numberOfSheets = wb.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = wb.getSheetAt(i);
			Iterator<Row> iter = sheet.iterator();
			while (iter.hasNext()) {
				Row row = iter.next();
				// 1 5
				Cell cell1 = row.getCell(1);
				Cell cell5 = row.getCell(5);

				if (cell1 == null || cell5 == null) {
					continue;
				}

				Object cellValue1 = ExcelUtils.getCellValue(cell1);
				Object cellValue5 = ExcelUtils.getCellValue(cell5);

				if (cellValue1 != null && cellValue5 != null) {
					String key = cellValue1.toString();
					String score = cellValue5.toString().replaceAll("分", "");
					try {
						double v = Double.parseDouble(score);

						Double value = map.get(key);
						if(value == null) {
							value = v;
						} else {
							value = new BigDecimal(v).add(new BigDecimal(value)).doubleValue();
						}
						map.put(key, value);
						
					} catch(NumberFormatException e) {
						System.out.println(file.getName() + " : " + sheet.getSheetName() + " : " + row.getRowNum());
					}
				}

			}
		}
		fis.close();
	}

	public static void recursion(File file) throws Exception {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				recursion(f);
			}
		} else {
			core(file);
		}
	}

}

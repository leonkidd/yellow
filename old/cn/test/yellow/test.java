package cn.yhhh.yellow;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("Book1.xls");
		Workbook book = create(fis);
		Sheet sheet = book.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		sheet.removeRowBreak(1);
		//System.out.println(lastRowNum);
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

}

package demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.heroes.yellow.core.impl.TDYellow;
import cn.heroes.yellow.filler.impl.Sheet0Filler;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.SheetNParser;

/**
 * Demo 1:<br/>
 * TD-SheetN-Parser, TD-Intercepter, Sheet0-Filler Demo
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-15
 * @since 1.0
 */
public class OpenScan {

	public static void main(String[] args) throws Exception {

		File file = new File("source.xls");

		// create filler with template
		InputStream is = new FileInputStream("template.xls");
		Sheet0Filler filler = new Sheet0Filler(is);
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create Parser
		TDParser parser = new SheetNParser();

		// implements Intercepter
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OpenScanIntercepter intercepter = new OpenScanIntercepter(baos);
		// create Yellow
		TDYellow yellow = new TDYellow(parser, intercepter, filler);
		yellow.yellow(file);

		byte[] result = baos.toByteArray();
	}

}

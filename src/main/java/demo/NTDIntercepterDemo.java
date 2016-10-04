package demo;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.core.impl.NTDYellow;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.NTDIntercepter;
import cn.heroes.yellow.parser.impl.ExcelParser;

public class NTDIntercepterDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// NTDIntercepter
		Yellow yellow = new NTDYellow(new ExcelParser(), new NTDIntercepterImpl(), null);
		yellow.yellow(new File("A.xlsx"));
		yellow.destroy();
	}

	static class NTDIntercepterImpl implements NTDIntercepter {

		@Override
		public boolean sheet(int index, String name) {
			System.out.println(index + ", " + name);
			return true;
		}

		@Override
		public boolean begin(TDRow row) {
			return true;
		}

		@Override
		public boolean end(TDRow row) {
			return false;
		}

		@Override
		public boolean ignore(TDRow row) {
			return false;
		}

		@Override
		public void row(TDRow row) {
			System.out.println(row.getString(2));
		}

		@Override
		public void info(Object info) {

		}

		@Override
		public OutputStream outputStream() {
			return null;
		}

		@Override
		public List<Object[]> data() {
			return null;
		}

		@Override
		public void init() {

		}

		@Override
		public void destroy() {

		}

	}
}

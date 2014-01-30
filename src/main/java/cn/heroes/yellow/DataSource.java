package cn.heroes.yellow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 数据源, 实现了各数据源之间的转换, 包括InputStream, File等.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @deprecated 未完成，统一使用输入流
 */
public class DataSource<T> {
	// NOTE: 加入时不转换, 获取时转换.

	private File file = null;
	private InputStream is = null;

	public DataSource(File file) {
		this.file = file;
	}

	public DataSource(InputStream is) {
		this.is = is;
	}

	public File getFile() {
		return null;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		// TODO How to close
		return is != null ? is : new FileInputStream(file);
	}
}

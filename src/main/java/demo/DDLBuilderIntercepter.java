package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.CopyIntercepter;

public class DDLBuilderIntercepter extends CopyIntercepter<File> {
	private static final Logger logger = LoggerFactory.getLogger(DDLBuilderIntercepter.class);
	@Override
	public boolean ignore(TDRow row) {
		return false;
	}

	@Override
	public void init() {
	}

	private File file = null;
	
	@Override
	public void info(Info<File> info) {
		super.info(info);
		file = info.body();
		
	}

	@Override
	public void destroy() {
		if (fos != null)
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public FillObject<List<Object[]>> over() {
		logger.info("File [{}] copied [{}] rows", file.getAbsolutePath(), this.getCurCopyied());
		return super.over();
	}

	@Override
	public boolean begin(TDRow row) {
		return row.getRowNum() == 2;
	}

	@Override
	public boolean end(TDRow row) {
		return false;
	}

	@Override
	public boolean sheet(int index, String name) {
		return name.equals("标准化字段");
	}

	private FileOutputStream fos = null;

	@Override
	public OutputStream getOutputStream() {
		try {
			fos = new FileOutputStream("$.xls");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fos;
	}

}

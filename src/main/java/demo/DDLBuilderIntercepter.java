package demo;

import java.io.File;

import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.CopyIntercepter;

public class DDLBuilderIntercepter extends CopyIntercepter<File> {

	@Override
	public boolean ignore(TDRow row) {
		return false;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void destroy() {
		
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

}

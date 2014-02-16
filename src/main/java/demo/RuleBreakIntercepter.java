package demo;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.intercepter.NTDIntercepter;

public class RuleBreakIntercepter extends NTDIntercepter<File> {

	private static final Logger logger = LoggerFactory
			.getLogger(RuleBreakIntercepter.class);

	private boolean begin = false;

	@Override
	public boolean begin(TDRow row) {
		String name = row.getString(2);
		if (begin) {
			return true;
		} else if (name != null && name.contains("姓名")) {
			// 匹配后下一行再开始
			begin = true;
		}
		return false;
	}

	@Override
	public boolean end(TDRow row) {
		//String name = row.getString(2);
		//return name == null || "".equals(name);
		return false;
	}

	@Override
	public boolean ignore(TDRow row) {
		return false;
	}

	@Override
	public void row(TDRow row) {
		System.out.println(row.getRowNum());
	}

	@Override
	public void sheet(int index, String name) {
		begin = false;
		logger.info("开始分析第{}个Sheet [{}]", index, name);
	}

	@Override
	public FillObject<List<Object[]>> over() {
		return null;
	}

	@Override
	public void init() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void info(Info<File> info) {
		File file = info.body();
		logger.info("开始分析文件[{}]", file.getAbsolutePath());
	}

}

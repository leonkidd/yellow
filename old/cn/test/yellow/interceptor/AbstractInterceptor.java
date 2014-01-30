package cn.yhhh.fairy.yellow.interceptor;

import java.io.File;

import cn.yhhh.fairy.yellow.core.Configuration;
import cn.yhhh.fairy.yellow.entity.Row;
import cn.yhhh.fairy.yellow.parser.Parser;

public abstract class AbstractInterceptor implements Interceptor {

	/** 当前使用的分析器 */
	protected Parser parser;
	/** 忽略的条数 */
	protected int ignore;
	/** 有效处理行数(不包括忽略的) */
	protected int available;

	@Override
	public void init(Configuration config) {
		parser = config.getParser();
	}

	@Override
	public void destroy() {
	}

	@Override
	public boolean begin(Row row) {
		return true;
	}

	@Override
	public boolean end(Row row) {
		return false;
	}

	@Override
	public boolean ignore(Row row) {
		ignore++;
		return false;
	}

	@Override
	public void row(Row row) {
		available++;
	}

	@Override
	public void file(File file) {
	}

	@Override
	public void directory(File directory) {
	}
}

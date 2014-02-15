package cn.heroes.yellow.core.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.entity.TDRow;
import cn.heroes.yellow.entity.impl.FileInfo;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.TDParser;
import cn.heroes.yellow.parser.impl.ExcelParserImpl;

public class ExcelYellow extends Yellow {

	/** 解析器对象 */
	private ExcelParserImpl p;
	/** 拦截器对象 */
	private TDIntercepter i;
	/** 填充器对象 */
	private Filler f;

	public ExcelYellow(ExcelParserImpl parser, TDIntercepter intercepter,
			Filler filler) {
		super(parser, intercepter, filler);
		this.p = parser;
		this.i = intercepter;
		this.f = filler;
	}

	@Override
	public void yellow(InputStream is, Info info) {
		// 调用解析器去解析InputStream
		Iterator<TDParser> tdps = p.parse(is);
		while(tdps.hasNext()) {
			TDParser tdp = tdps.next();
			TDRow next = tdp.next();
		}
	}

	@Override
	public void yellow(File file) {

	}

}

package cn.heroes.yellow.core.impl;

import java.io.InputStream;

import cn.heroes.yellow.core.Yellow;
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

	public ExcelYellow(ExcelParserImpl parser, TDIntercepter intercepter, Filler filler) {
		super(parser, intercepter, filler);
		this.p = parser;
		this.i = intercepter;
		this.f = filler;
	}

	@Override
	public void yellow(InputStream is) {
		// 调用解析器去解析InputStream
		p.parse(is);
		
		// 是否已真正开始的标识
		boolean isBegin = false;
		
		// 迭代row
		TDRow row = null;
		
		FileInfo info = new FileInfo();
		
		/*
		i.info(info);
		while ((row = p.next()) != null) {

			// 是否已真正开始
			if (isBegin) {
				// 已开始
				if (i.end(row)) {
					// 结束
					break;
				} else if (i.ignore(row)) {
					// 该行忽略
					continue;
				} else {
					// Business Code
					i.row(row);
				}
			} else if (i.begin(row)) {
				// 判断是否真正开始, 真正开始后不再判断
				isBegin = true;
			}
		}*/
	}

}

package cn.heroes.yellow.core.impl;

import java.io.InputStream;

import cn.heroes.yellow.core.Yellow;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.Intercepter;
import cn.heroes.yellow.intercepter.TDIntercepter;
import cn.heroes.yellow.parser.Parser;
import cn.heroes.yellow.parser.TDParser;

/**
 * 二维表的Yellow实现
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class TDYellow extends Yellow {
	
	/** 解析器对象 */
	protected Parser<?> p;
	/** 拦截器对象 */
	protected Intercepter i;
	/** 填充器对象 */
	protected Filler f;

	public TDYellow(TDParser parser, TDIntercepter intercepter, Filler filler) {
		super(parser, intercepter, filler);
		this.p = parser;
		this.i = intercepter;
		this.f = filler;
	}

	@Override
	public void yellow(InputStream is) {
		p.parse(is);
	}

}

package cn.heroes.yellow.core;

import java.io.File;
import java.io.InputStream;

import cn.heroes.yellow.entity.Info;
import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.Intercepter;
import cn.heroes.yellow.parser.Parser;

/**
 * 程序入口抽象类. 对应每一大类Parser(e.g. TDParser, ODParser)就有相关的Yellow.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public abstract class Yellow {
	/** 解析器对象 */
	protected Parser<?> p;
	/** 拦截器对象 */
	protected Intercepter i;
	/** 填充器对象 */
	protected Filler f;

	/**
	 * 装入三组件, 并已父类中完成初始化.
	 * 
	 * @param parser
	 * @param interceptor
	 * @param filler
	 */
	public Yellow(Parser<?> parser, Intercepter intercepter, Filler filler) {
		this.p = parser;
		this.i = intercepter;
		this.f = filler;

		p.init();
		i.init();
		if (f != null)
			f.init();
	}

	/**
	 * 程序入口放法，处理指定数据流
	 * 
	 * @param is
	 * @param info
	 */
	public abstract void yellow(InputStream is, Info info);

	/**
	 * 程序入口放法，处理指定数文件
	 * 
	 * @param file
	 */
	public abstract void yellow(File file);

	/**
	 * 销毁三组件
	 */
	public void destroy() {
		if (f != null)
			f.destroy();
		i.destroy();
		p.destroy();
	}
}

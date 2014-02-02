package cn.heroes.yellow.core;

import java.io.File;
import java.io.InputStream;

import org.apache.poi.ss.formula.functions.T;

import cn.heroes.yellow.filler.Filler;
import cn.heroes.yellow.intercepter.Interceptor;
import cn.heroes.yellow.parser.Parser;

/**
 * 程序入口接口.
 * 对应每一大类Parser(e.g. TDParser)就有相关的Yellow.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public interface Yellow {

	void set(Parser<T> parser, Interceptor interceptor, Filler filler);

	void yellow(File file);

	void yellow(InputStream is);
}

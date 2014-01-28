package cn.yhhh.yellow.parser;

/**
 * 解析器接口, 所有解析器必须实现该接口
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public interface Parser {

	/**
	 * Parser initialize
	 */
	void init();

	/**
	 * Parser destroy
	 */
	void destroy();
}

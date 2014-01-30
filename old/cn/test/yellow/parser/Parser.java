package cn.yhhh.fairy.yellow.parser;

import java.io.File;

import cn.yhhh.fairy.yellow.entity.Row;


/**
 * 数据源分析器,用于分析源文件,然后将期以"行结构"呈现,数据类型由分析器控制.
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-9-4
 * @since 1.0
 */
public interface Parser {
	
	void init();

	/**
	 * 关闭分析器的资源连接
	 */
	void destroy();

	/**
	 * 分析文件
	 * 
	 * @param file 要分析的文件
	 * @return
	 */
	void parser(File file);

	/**
	 * 分析文件
	 * 
	 * @param file 要分析的文件
	 * @param id 标识
	 * @return
	 */
	void parser(File file, Object id);

	/**
	 * 到下一行并返回该行（“下一行”）的数据，一开始的时候不占仍何行
	 * 
	 * @return Row对象，当不存在时返回null
	 */
	Row next();
}

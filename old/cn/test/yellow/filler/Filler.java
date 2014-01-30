package cn.yhhh.fairy.yellow.filler;

/**
 * 填充器,将数据填充至目的地的具体操作，也可以是直接插入数据库中。
 * 
 * @author Leon Kidd
 * @version 1.00, 2011-8-18
 * @since 1.0
 */
public interface Filler {

	public void init();

	public void fill(Object input, Object output);

	public void destroy();
}

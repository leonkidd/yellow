package cn.heroes.yellow.entity;

/**
 * 标准的拦截内容信息对象
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public interface Info<T> {

	/**
	 * 拦截对象的字符串标识, 如名字等
	 * 
	 * @return
	 */
	String id();

	/**
	 * 拦截对象的内容对象
	 * 
	 * @return
	 */
	T body();

}

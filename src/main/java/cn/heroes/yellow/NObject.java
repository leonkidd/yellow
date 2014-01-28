package cn.heroes.yellow;

/**
 * Normal Object<br/>
 * 定义了一般对象都会有方法
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 */
public interface NObject {

	/**
	 * Invoke after object initialize
	 */
	void init();

	/**
	 * Invoke before object Destroy
	 */
	void destroy();
}

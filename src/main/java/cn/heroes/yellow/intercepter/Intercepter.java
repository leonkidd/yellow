package cn.heroes.yellow.intercepter;

import cn.heroes.yellow.NObject;
import cn.heroes.yellow.entity.FillObject;
import cn.heroes.yellow.entity.Info;

/**
 * The <tt>Interceptor</tt> interface should be implemented by all Interceptors.
 * Handle the data parsed by {@link cn.heroes.yellow.parser.Parser}.
 * <p>
 * The <tt>T</tt> is the type of data which is return by <code>over</code>
 * method and used by {@link cn.heroes.yellow.filler.Filler}. The <tt>F</tt> is
 * the type of info about object intercepted.
 * <p/>
 * <p>
 * 拦截器, 上接<code>Parser</code>, 下接<code>Filler</code>, 只关注标准格式下的内容数据处理,
 * 具体的处理规则一般由框架使用者的业务逻辑来确定实现.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-30
 */
public interface Intercepter<T, F> extends NObject {

	/**
	 * 在Parser要开始化析某个内容时调用, 一般是放入一些Parser正要解析的内容主体相关的信息.
	 * 
	 * @param info
	 *            信息, 如文件名等
	 */
	void info(Info<F> info);

	/**
	 * Invoke when the parsing is over, and return the data for filling.
	 * 
	 * @return the <code>FillObject</code> object. It contain the data to fill
	 *         and the information about saving media. Can also return null when
	 *         there is no filler object in yellow.
	 * @see cn.heroes.yellow.filler.Filler
	 */
	FillObject<T> over();
}

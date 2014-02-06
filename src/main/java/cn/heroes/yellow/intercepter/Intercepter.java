package cn.heroes.yellow.intercepter;

import cn.heroes.yellow.NObject;
import cn.heroes.yellow.entity.Info;

/**
 * The <code>Interceptor</code> interface should be implemented by all
 * Interceptors. Handle the data parsed by <code>Parser</code>.
 * <p>
 * 拦截器, 上接<code>Parser</code>, 下接<code>Filler</code>, 只关注标准格式下的内容数据处理,
 * 具体的处理规则一般由框架使用者的业务逻辑来确定实现.
 * </p>
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-30
 */
public interface Intercepter extends NObject {

	/**
	 * 在Parser要开始化析某个内容时调用, 一般是放入一些Parser正要解析的内容主体相关的信息.
	 * 
	 * @param info
	 *            信息, 如文件名等
	 */
	void info(Info info);

	/**
	 * The parsing is over now. 解析工作已结束.
	 */
	void over();
}

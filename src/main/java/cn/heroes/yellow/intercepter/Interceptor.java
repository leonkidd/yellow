package cn.heroes.yellow.intercepter;

import cn.heroes.yellow.NObject;

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
public interface Interceptor extends NObject {

}

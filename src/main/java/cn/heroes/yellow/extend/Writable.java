package cn.heroes.yellow.extend;

/**
 * An entity implements the <code>Writable</code> interface to be writable in
 * the
 * {@link cn.heroes.yellow.intercepter.TDIntercepter#row(cn.heroes.yellow.entity.TDRow)}
 * method.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-7-4
 */
public interface Writable {
	void setValue(int row, Object value);
}

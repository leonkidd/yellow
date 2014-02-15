package cn.heroes.yellow.entity;

import java.io.OutputStream;

/**
 * The Object which is gave by <code>Intercepter#over()</code>, and used to fill
 * <code>data</code> into a media specified by <code>info<code>.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-11
 */
public class FillObject<T> {
	/**
	 * The <code>OutputStream</code> that data save into.
	 */
	private OutputStream os;
	/**
	 * Data to fill.
	 */
	private T data;

	public OutputStream getOutputStream() {
		return os;
	}

	public void setOutputStream(OutputStream os) {
		this.os = os;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

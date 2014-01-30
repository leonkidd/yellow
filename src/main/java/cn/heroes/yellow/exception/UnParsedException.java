package cn.heroes.yellow.exception;

/**
 * 文件未解析
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public class UnParsedException extends RuntimeException {

	public UnParsedException() {
		super("Please invoke parser Method first.");
	}
}

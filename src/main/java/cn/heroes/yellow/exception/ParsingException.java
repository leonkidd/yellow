package cn.heroes.yellow.exception;

/**
 * 文件解析出错
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-1-28
 * @since 1.0
 */
public class ParsingException extends RuntimeException {

	public ParsingException() {
		super("File parsing error.");
	}

	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParsingException(Throwable cause) {
		this("File parsing error.", cause);
	}
}

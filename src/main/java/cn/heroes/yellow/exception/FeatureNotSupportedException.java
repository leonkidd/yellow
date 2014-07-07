package cn.heroes.yellow.exception;

/**
 * Feature not supported.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-7-7
 * @since 1.0
 */
public class FeatureNotSupportedException extends RuntimeException {

	public FeatureNotSupportedException() {
		super();
	}

	public FeatureNotSupportedException(String reason) {
		super(reason);
	}
}

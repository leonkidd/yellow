package cn.heroes.yellow.exception;

/**
 * The cell in row not found.
 * 
 * @author Leon Kidd
 * @version 1.00, 2014-2-2
 */
public class CellNotFoundException extends RuntimeException {

	public CellNotFoundException() {
		super("Cell not found.");
	}

	public CellNotFoundException(String message) {
		super(message);
	}

	public CellNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CellNotFoundException(Throwable cause) {
		this("Cell not found.", cause);
	}
}

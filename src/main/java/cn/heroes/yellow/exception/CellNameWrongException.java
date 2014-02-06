package cn.heroes.yellow.exception;

public class CellNameWrongException extends RuntimeException {

	public CellNameWrongException() {
		super("Cell name Wrong.");
	}

	public CellNameWrongException(String message) {
		super(message);
	}

	public CellNameWrongException(String message, Throwable cause) {
		super(message, cause);
	}

	public CellNameWrongException(Throwable cause) {
		this("Cell name Wrong.", cause);
	}
}
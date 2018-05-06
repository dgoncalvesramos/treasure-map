public class ReadFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public ReadFileException() {
		super();
	}

	public ReadFileException(String message) {
		super(message);
	}

	public ReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadFileException(Throwable cause) {
		super(cause);
	}
}
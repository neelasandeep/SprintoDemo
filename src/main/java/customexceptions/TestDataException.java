package customexceptions;

public class TestDataException extends Exception {

	private static final long serialVersionUID = 5559289177513350945L;

	public TestDataException() {
		super();
	}

	public TestDataException(String message) {
		super(message);
	}

	public TestDataException(String message, Exception e) {
		super(message, e);
	}

}

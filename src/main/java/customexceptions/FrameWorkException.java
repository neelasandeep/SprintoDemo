package customexceptions;

public class FrameWorkException extends Exception {

	private static final long serialVersionUID = -5853964053681245908L;

	public FrameWorkException() {
		super();
	}

	public FrameWorkException(String message) {
		super(message);
	}

	public FrameWorkException(String message, Exception e) {
		super(message, e);
	}

}

package io.github.railroad.mods.version;

public class FailedToGetException extends RuntimeException {

	@java.io.Serial
	private static final long serialVersionUID = 9145556598793462645L;
	
	public FailedToGetException(String message) {
		super(message);
	}
	
	public FailedToGetException(String message, Throwable cause) {
		super(message, cause);
	}
}

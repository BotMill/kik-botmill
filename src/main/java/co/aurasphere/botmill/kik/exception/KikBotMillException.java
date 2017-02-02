package co.aurasphere.botmill.kik.exception;

public class KikBotMillException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public KikBotMillException(String message) {
        super(message);
    }
	

	@Override
	public String toString() {
		return "KikBotMillException";
	}
}

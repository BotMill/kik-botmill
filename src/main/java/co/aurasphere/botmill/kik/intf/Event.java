package co.aurasphere.botmill.kik.intf;

public interface Event<T> {
	boolean verifyEvent(T incomingMessage);
}

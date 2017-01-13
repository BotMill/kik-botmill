package co.aurasphere.botmill.kik.intf;

public interface Frame {
	Event getEvent();
	void setEvent(Event event);
	void addReply(Reply reply);
}

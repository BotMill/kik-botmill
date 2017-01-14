package co.aurasphere.botmill.kik.intf;

import java.util.List;

import co.aurasphere.botmill.kik.model.Message;

public interface Frame {
	Event getEvent();
	void setEvent(Event event);
	void addReply(Reply<? extends Message> reply);
	List<Reply<? extends Message>> getReplies();
}

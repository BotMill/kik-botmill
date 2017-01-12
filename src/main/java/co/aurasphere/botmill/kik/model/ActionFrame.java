package co.aurasphere.botmill.kik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Frame;
import co.aurasphere.botmill.kik.intf.Reply;


public class ActionFrame implements Frame, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Event event;
	private List<Reply> replies = new ArrayList<Reply>();
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


	public void addReply(Reply reply) {
		this.replies.add(reply);
	}


	
}

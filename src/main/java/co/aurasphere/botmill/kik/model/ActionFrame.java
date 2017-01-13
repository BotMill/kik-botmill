package co.aurasphere.botmill.kik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Frame;
import co.aurasphere.botmill.kik.intf.Reply;


public class ActionFrame extends AbstractFrame implements Frame, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Event getEvent() {
		return super.event;
	}
	
	@Override
	public void setEvent(Event event) {
		super.event = event;
	}

	@Override
	public void addReply(Reply reply) {
		super.replies.add(reply);
	}

	public void addPreCommand(Command command) {
		super.preCommands.add(command);
	}
	
	public void addPostCommand(Command command) {
		super.postCommands.add(command);
	}	
}

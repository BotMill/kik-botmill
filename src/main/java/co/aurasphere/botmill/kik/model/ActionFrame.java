package co.aurasphere.botmill.kik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Frame;
import co.aurasphere.botmill.kik.intf.Reply;


public class ActionFrame implements Frame, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Event event;
	private List<Reply> replies = new ArrayList<Reply>();
	private List<Command> preCommands = new ArrayList<Command>();
	private List<Command> postCommands = new ArrayList<Command>();
	
	public Event getEvent() {
		return event;
	}
	
	@Override
	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public void addReply(Reply reply) {
		this.replies.add(reply);
	}

	public void addPreCommand(Command command) {
		this.preCommands.add(command);
	}
	
	public void addPostCommand(Command command) {
		this.postCommands.add(command);
	}

	
}

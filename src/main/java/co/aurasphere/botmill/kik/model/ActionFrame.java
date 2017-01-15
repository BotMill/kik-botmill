package co.aurasphere.botmill.kik.model;

import java.io.Serializable;

import java.util.List;

import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Frame;
import co.aurasphere.botmill.kik.intf.Reply;


public class ActionFrame extends AbstractFrame implements Frame, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Event<? extends Message> getEvent() {
		return this.event;
	}
	public List<Reply<? extends Message>> getReplies() {
		return this.replies;
	}
	
	@Override
	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public void addReply(Reply<? extends Message> reply) {
		this.replies.add(reply);
	}

	public void addPreCommand(Command command) {
		this.preCommands.add(command);
	}
	
	public void addPostCommand(Command command) {
		this.postCommands.add(command);
	}	
}

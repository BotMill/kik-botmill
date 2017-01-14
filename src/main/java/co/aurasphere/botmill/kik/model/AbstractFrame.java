package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;
import java.util.List;
import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Reply;

public abstract class AbstractFrame {
	
	protected Event<? extends Message> event;
	protected List<Reply<? extends Message>> replies = new ArrayList<Reply<? extends Message>>();
	protected List<Command> preCommands = new ArrayList<Command>();
	protected List<Command> postCommands = new ArrayList<Command>();
	
	protected void processFrame(Message message) {
		this.processPreCommands();
		for(Reply<? extends Message> reply : replies) {
			reply.processReply(message);
		}
		this.processPostCommands();
	}
	
	protected void processPreCommands() {
		for(Command preCommand : preCommands) {
			
		}
	}
	
	protected void processPostCommands() {
		for(Command postCommand : postCommands) {
			
		}
	}
}

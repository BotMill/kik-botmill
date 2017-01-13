package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;
import java.util.List;
import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Reply;

public abstract class AbstractFrame {
	
	protected Event event;
	protected List<Reply> replies = new ArrayList<Reply>();
	protected List<Command> preCommands = new ArrayList<Command>();
	protected List<Command> postCommands = new ArrayList<Command>();
	
	protected void processFrame(MessageEnvelope messageEnvelope) {
		this.processPreCommands();
		for(Reply reply : replies) {
			reply.processReply(messageEnvelope);
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

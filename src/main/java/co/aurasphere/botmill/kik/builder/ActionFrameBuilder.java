package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.intf.Buildable;
import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.ActionFrame;
import co.aurasphere.botmill.kik.model.Message;

public class ActionFrameBuilder implements Buildable<ActionFrame>{

	/** The config. */
	private static ActionFrame actionFrame = new ActionFrame();
	
	/** The instance. */
	private static ActionFrameBuilder instance;
	
	public static ActionFrameBuilder createAction() {
		if (instance == null) {
			instance = new ActionFrameBuilder();
		}
		actionFrame = new ActionFrame();
		return instance;
	}
	
	public ActionFrameBuilder setEvent(Event event) {
		actionFrame.setEvent(event);
		return this;
	}
	public ActionFrameBuilder addReply(Reply reply) {
		actionFrame.addReply(reply);
		return this;
	}
	
	public ActionFrameBuilder addPreCommand(Command command) {
		actionFrame.addPreCommand(command);
		return this;
	}
	
	public ActionFrameBuilder addPostCommand(Command command) {
		actionFrame.addPostCommand(command);
		return this;
	}
	
	public ActionFrame buildToContext() {
		KikBotMillContext.getInstance().addActionFrameToContext(actionFrame);
		return actionFrame;
	}
	
	@Override
	public ActionFrame build() {
		return actionFrame;
	}
}

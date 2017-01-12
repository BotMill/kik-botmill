package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.intf.Buildable;
import co.aurasphere.botmill.kik.intf.Command;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.ActionFrame;

public class ActionFrameBuilder implements Buildable<ActionFrame>{

	/** The config. */
	private ActionFrame actionFrame = new ActionFrame();
	
	/** The instance. */
	private static ActionFrameBuilder instance;
	
	public static ActionFrameBuilder createAction() {
		if (instance == null) {
			instance = new ActionFrameBuilder();
		}
		return instance;
	}
	
	public ActionFrameBuilder setEvent(Event event) {
		this.actionFrame.setEvent(event);
		return this;
	}
	public ActionFrameBuilder addReply(Reply reply) {
		this.actionFrame.addReply(reply);
		return this;
	}
	
	public ActionFrameBuilder addPreCommand(Command command) {
		this.actionFrame.addPreCommand(command);
		return this;
	}
	
	public ActionFrameBuilder addPostCommand(Command command) {
		this.actionFrame.addPostCommand(command);
		return this;
	}
	
	public ActionFrame buildToContext() {
		KikBotMillContext.getInstance().addActionFrameToContext(this.actionFrame);
		return this.actionFrame;
	}
	@Override
	public ActionFrame build() {
		return this.actionFrame;
	}
}

package co.aurasphere.botmill.kik.builder;

import co.aurasphere.botmill.kik.KikBotMillResponseContext;
import co.aurasphere.botmill.kik.intf.Buildable;
import co.aurasphere.botmill.kik.intf.Event;
import co.aurasphere.botmill.kik.intf.Reply;
import co.aurasphere.botmill.kik.model.ActionFrame;

public class ActionFrameBuilder implements Buildable<ActionFrame>{

	/** The config. */
	private ActionFrame actionFrame = new ActionFrame();
	
	/** The instance. */
	private static ActionFrameBuilder instance;
	
	public static ActionFrameBuilder buildAction() {
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
	
	@Override
	public ActionFrame build() {
		KikBotMillResponseContext.getInstance().addActionFrameToContext(this.actionFrame);
		return this.actionFrame;
	}
}

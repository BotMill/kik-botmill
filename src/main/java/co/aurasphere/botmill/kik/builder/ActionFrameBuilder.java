/*
 * 
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package co.aurasphere.botmill.kik.builder;

import java.util.List;

import co.aurasphere.botmill.kik.KikBotMillContext;
import co.aurasphere.botmill.kik.model.ActionFrame;
import co.aurasphere.botmill.kik.model.Buildable;
import co.aurasphere.botmill.kik.model.Command;
import co.aurasphere.botmill.kik.model.Event;
import co.aurasphere.botmill.kik.model.Message;
import co.aurasphere.botmill.kik.model.Reply;

/**
 * The Class ActionFrameBuilder.
 * 
 * The main frame builder class. This can be used to create action frames
 * that are essential in building responses from the Bot.
 * 
 * @author Alvin P. Reyes
 */
public class ActionFrameBuilder implements Buildable<ActionFrame>{

	/** The config. */
	private static ActionFrame actionFrame = new ActionFrame();
	
	/** The instance. */
	private static ActionFrameBuilder instance;
	
	/**
	 * Instantiates a new action frame builder.
	 */
	private ActionFrameBuilder() {}
	
	/**
	 * Creates the action.
	 *
	 * @return the action frame builder
	 */
	public static ActionFrameBuilder getInstance() {
		if (instance == null) {
			instance = new ActionFrameBuilder();
		}
		actionFrame = new ActionFrame();
		return instance;
	}
	
	/**
	 * Sets the event.
	 *
	 * @param event the event
	 * @return the action frame builder
	 */
	public ActionFrameBuilder setEvent(Event event) {
		actionFrame.setEvent(event);
		return this;
	}
	
	/**
	 * Adds the reply.
	 *
	 * @param reply the reply
	 * @return the action frame builder
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionFrameBuilder addReply(Reply reply) {
		actionFrame.addReply(reply);
		return this;
	}
	
	/**
	 * Adds the replies.
	 *
	 * @param replies the replies
	 * @return the action frame builder
	 */
	public ActionFrameBuilder addReplies(List<Reply<? extends Message>> replies) {
		actionFrame.addReplies(replies);
		return this;
	}
	
	/**
	 * Adds the replies.
	 *
	 * @param replies the replies
	 * @return the action frame builder
	 */
	public ActionFrameBuilder addReplies(@SuppressWarnings("unchecked") Reply<? extends Message>... replies) {
		actionFrame.addReplies(replies);
		return this;
	}
	
	/**
	 * Adds the pre command.
	 *
	 * @param command the command
	 * @return the action frame builder
	 */
	public ActionFrameBuilder addPreCommand(Command command) {
		actionFrame.addPreCommand(command);
		return this;
	}
	
	/**
	 * Adds the post command.
	 *
	 * @param command the command
	 * @return the action frame builder
	 */
	public ActionFrameBuilder addPostCommand(Command command) {
		actionFrame.addPostCommand(command);
		return this;
	}
	
	/**
	 * Builds the to context.
	 *
	 * @return the action frame
	 */
	public ActionFrame buildToContext() {
		KikBotMillContext.getInstance().addActionFrameToContext(actionFrame);
		return actionFrame;
	}
	
	
	/**
	 * Builds the to broadcast.
	 *
	 * @return the action frame
	 */
	public ActionFrame buildToBroadcast() {
		KikBotMillContext.getInstance().addActionFrameToBroadcast(actionFrame);
		return actionFrame;
	}
	
	/**
	 * Builds the to context and broadcast.
	 *
	 * @return the action frame
	 */
	public ActionFrame buildToContextAndBroadcast() {
		KikBotMillContext.getInstance().addActionFrameToContext(actionFrame);
		KikBotMillContext.getInstance().addActionFrameToBroadcast(actionFrame);
		return actionFrame;
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Buildable#build()
	 */
	@Override
	public ActionFrame build() {
		return actionFrame;
	}
	
}

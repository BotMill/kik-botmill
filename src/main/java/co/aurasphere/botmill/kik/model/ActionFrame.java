/**
 * 
 * MIT License
 *
 * Copyright (c) 2017 BotMill.io
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
package co.aurasphere.botmill.kik.model;

import java.io.Serializable;

import java.util.List;


/**
 * The Class ActionFrame.
 * 
 * @author Alvin P. Reyes
 */
public class ActionFrame extends AbstractFrame implements Frame, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Frame#getEvent()
	 */
	public Event getEvent() {
		return this.event;
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Frame#getReplies()
	 */
	public List<Reply<? extends Message>> getReplies() {
		return this.replies;
	}
	
	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Frame#setEvent(co.aurasphere.botmill.kik.intf.Event)
	 */
	@Override
	public void setEvent(Event event) {
		this.event = event;
	}

	/* (non-Javadoc)
	 * @see co.aurasphere.botmill.kik.intf.Frame#addReply(co.aurasphere.botmill.kik.intf.Reply)
	 */
	@Override
	public void addReply(Reply<? extends Message> reply) {
		this.replies.add(reply);
	}
	
	/**
	 * This method is used to add multiple replies.
	 * @param reply
	 */
	public void addReplies(List<Reply<? extends Message>> reply) {
		this.replies.addAll(reply);
	}

	/**
	 * Adds the pre command.
	 *
	 * @param command the command
	 */
	public void addPreCommand(Command command) {
		this.preCommands.add(command);
	}
	
	/**
	 * Adds the post command.
	 *
	 * @param command the command
	 */
	public void addPostCommand(Command command) {
		this.postCommands.add(command);
	}	
}

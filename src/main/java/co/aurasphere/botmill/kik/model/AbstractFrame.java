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

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AbstractFrame.
 * 
 * @author Alvin P. Reyes
 */
public abstract class AbstractFrame {
	
	/** The event. */
	protected Event event;
	
	/** The replies. */
	protected List<Reply<? extends Message>> replies = new ArrayList<Reply<? extends Message>>();
	
	/** The pre commands. */
	protected List<Command> preCommands = new ArrayList<Command>();
	
	/** The post commands. */
	protected List<Command> postCommands = new ArrayList<Command>();
	
	/**
	 * Process frame.
	 *
	 * @param message the message
	 */
	protected void processFrame(Message message) {
		this.processPreCommands();
		for(Reply<? extends Message> reply : replies) {
			reply.processReply(message);
		}
		this.processPostCommands();
	}
	
	/**
	 * Process pre commands.
	 */
	protected void processPreCommands() {
		for(Command preCommand : preCommands) {
			preCommand.run();
		}
	}
	
	/**
	 * Process post commands.
	 */
	protected void processPostCommands() {
		for(Command postCommand : postCommands) {
			postCommand.run();
		}
	}
}

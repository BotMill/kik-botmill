package co.aurasphere.botmill.kik.event;

import co.aurasphere.botmill.kik.incoming.model.VideoMessage;
import co.aurasphere.botmill.kik.intf.Event;

public class VideoMessageEvent implements Event<VideoMessage>{
	@Override
	public boolean verifyEvent(VideoMessage incomingMessage) {
		// TODO Auto-generated method stub
		return false;
	}
}

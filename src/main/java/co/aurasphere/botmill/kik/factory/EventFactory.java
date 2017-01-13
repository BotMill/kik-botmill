package co.aurasphere.botmill.kik.factory;

import co.aurasphere.botmill.kik.event.LinkMessageEvent;
import co.aurasphere.botmill.kik.event.PictureMessageEvent;
import co.aurasphere.botmill.kik.event.TextMessageEvent;
import co.aurasphere.botmill.kik.event.TextMessagePatternEvent;
import co.aurasphere.botmill.kik.event.VideoMessageEvent;

public class EventFactory {
	
	public static TextMessagePatternEvent textMessagePattern(String pattern){
		return new TextMessagePatternEvent().setPattern(pattern);
	}
	
	public static TextMessageEvent textMessage(String text){
		return new TextMessageEvent().setText(text);
	}
	
	public static PictureMessageEvent picture(){
		return new PictureMessageEvent();
	}
	
	public static VideoMessageEvent video(){
		return new VideoMessageEvent();
	}
	
	public static LinkMessageEvent link() {
		return new LinkMessageEvent();
	}
}

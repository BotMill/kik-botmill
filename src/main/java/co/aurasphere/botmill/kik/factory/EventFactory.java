package co.aurasphere.botmill.kik.factory;

import co.aurasphere.botmill.kik.event.PictureMessageEvent;
import co.aurasphere.botmill.kik.event.TextMessageEvent;
import co.aurasphere.botmill.kik.event.TextMessagePatternEvent;

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
}

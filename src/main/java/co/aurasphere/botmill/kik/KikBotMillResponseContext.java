package co.aurasphere.botmill.kik;

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.intf.Frame;

public class KikBotMillResponseContext {
	/** The instance. */
	private static KikBotMillResponseContext instance;
	private List<Frame> actionFrames;
	
	public KikBotMillResponseContext() {
		this.actionFrames = new ArrayList<Frame>();
	}
	
	public static KikBotMillResponseContext getInstance() {
		if (instance == null) {
			instance = new KikBotMillResponseContext();
		}
		return instance;
	}
	
	public void addActionFrameToContext(Frame actionFrame) {
		this.actionFrames.add(actionFrame);
	}
}

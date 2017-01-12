package co.aurasphere.botmill.kik.intf;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDomain implements Domain {
	
	List<Frame> actionFrames;
	
	public AbstractDomain() {
		this.actionFrames = new ArrayList<Frame>();
		this.buildDomain();
	}
	
	public void addActionFrame(Frame actionFrame) {
		this.actionFrames.add(actionFrame);
	}
	
	
}

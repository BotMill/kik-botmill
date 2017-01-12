package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.intf.Domain;
import co.aurasphere.botmill.kik.intf.Frame;

public abstract class AbstractDomain implements Domain {
	
	List<Frame> actionFrames;
	
	public AbstractDomain() {
		this.actionFrames = new ArrayList<Frame>();
		this.buildDomain();
		this.sortContextBuckets();
	}
	
	public void addActionFrame(Frame actionFrame) {
		this.actionFrames.add(actionFrame);
	}
	
	protected void sortContextBuckets(){
		System.out.println("sort it to different buckets");
	}

	
}

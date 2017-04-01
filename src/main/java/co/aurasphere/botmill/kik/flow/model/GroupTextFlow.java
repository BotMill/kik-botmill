package co.aurasphere.botmill.kik.flow.model;

import java.util.ArrayList;
import java.util.List;

public class GroupTextFlow {

	private String groupId;
	private List<TextFlow> textFlow = new ArrayList<TextFlow>();
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public List<TextFlow> getTextFlow() {
		return textFlow;
	}
	public void setTextFlow(List<TextFlow> textFlow) {
		this.textFlow = textFlow;
	}
	
	public void addTextFlow(TextFlow textFlow) {
		this.textFlow.add(textFlow);
	}
}

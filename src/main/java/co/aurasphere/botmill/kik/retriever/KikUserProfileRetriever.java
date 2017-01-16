package co.aurasphere.botmill.kik.retriever;

import co.aurasphere.botmill.kik.json.JsonUtils;
import co.aurasphere.botmill.kik.model.UserProfile;
import co.aurasphere.botmill.kik.network.NetworkUtils;

public class KikUserProfileRetriever {
	
	public static UserProfile getUserProfile(String username) {
		return JsonUtils.fromJson(NetworkUtils.getJsonUserMessage(username), UserProfile.class);
	}
}

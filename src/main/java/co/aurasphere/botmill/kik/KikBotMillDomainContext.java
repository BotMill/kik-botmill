package co.aurasphere.botmill.kik;

import java.util.ArrayList;
import java.util.List;

import co.aurasphere.botmill.kik.intf.Domain;

public class KikBotMillDomainContext {
	
	/** The instance. */
	private static KikBotMillDomainContext instance;
	private List<Domain> domains;
	
	public KikBotMillDomainContext() {
		this.domains = new ArrayList<Domain>();
	}
	
	public static KikBotMillDomainContext getInstance() {
		if (instance == null) {
			instance = new KikBotMillDomainContext();
		}
		return instance;
	}
	
	public void registerDomain(Domain domain) {
		this.domains.add(domain);
	}
}

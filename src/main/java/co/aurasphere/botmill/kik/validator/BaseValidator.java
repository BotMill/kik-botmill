package co.aurasphere.botmill.kik.validator;

import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;

public abstract class BaseValidator {
	public abstract boolean validate(IncomingMessage incomingMessage);
}

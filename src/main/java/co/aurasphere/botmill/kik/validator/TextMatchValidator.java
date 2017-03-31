package co.aurasphere.botmill.kik.validator;

import co.aurasphere.botmill.kik.incoming.model.IncomingMessage;

public class TextMatchValidator extends BaseValidator {
	 
	@Override
	public boolean validate(IncomingMessage incomingMessage) {
		//	run your validation, if this returns falses, the method reruns.
		return false;
	}

}

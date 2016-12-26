package co.aurasphere.botmill.kik.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * The Class Keyboard.
 */
public class Keyboard {

	/** The choices. */
	private List<Object> choices = new ArrayList<Object>();

	/**
	 * Instantiates a new keyboard.
	 *
	 * @param choices
	 *            the choices
	 */
	public Keyboard(List<Object> choices) {
		this.choices = choices;
	}

	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public List<Object> getChoices() {
		return choices;
	}

	/**
	 * Sets the choices.
	 *
	 * @param choices
	 *            the new choices
	 */
	public void setChoices(ArrayList<Object> choices) {
		this.choices = choices;
	}

	/**
	 * Gets the keyboard json.
	 *
	 * @return the keyboard json
	 */
	public JsonArray getKeyboardJson() {
		if (choices.isEmpty())
			throw new RuntimeException("Choices arraylist was empty!");

		JsonObject keyboard = new JsonObject();
		JsonArray arr = new JsonArray();

		keyboard.addProperty("type", "suggested");

		for (int i = 0; i < choices.size(); i++) {
			if (choices.get(i) instanceof String) {
				String str = (String) choices.get(i);

				if (str.isEmpty())
					continue;

				JsonObject obj = new JsonObject();
				obj.addProperty("type", "text");
				obj.addProperty("body", str);
				arr.add(obj);
			} else if (choices.get(i) instanceof FriendPicker) {
				FriendPicker picker = (FriendPicker) choices.get(i);

				if (picker.getBody().isEmpty())
					continue;

				JsonObject obj = new JsonObject();
				obj.addProperty("type", picker.getType());
				obj.addProperty("body", picker.getBody());
				obj.addProperty("min", picker.getMin());
				obj.addProperty("max", picker.getMax());

				if (!picker.getPreselected().isEmpty()) {
					JsonArray preselectedArray = new JsonArray();

					for (int j = 0; j < picker.getPreselected().size(); j++) {
						preselectedArray.add(picker.getPreselected().get(i));
					}

					arr.add(preselectedArray);
				}

				arr.add(obj);
			} else {
				JsonObject obj = new JsonObject();
				obj.addProperty("type", "text");
				obj.addProperty("body", choices.get(i).toString());
				arr.add(obj);
			}
		}

		if (arr.size() == 0)
			throw new RuntimeException("The keyboard json was empty!");
		keyboard.add("responses", arr);

		JsonArray keyboardArray = new JsonArray();

		keyboardArray.add(keyboard);

		return keyboardArray;
	}
}

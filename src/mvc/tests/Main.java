package mvc.tests;

import mvc.controller.ControllerInputQuestion;
import mvc.model.Model;

public class Main {

	public static void main(String[] args) {
		System.out.println(" In main app");
		Model model = new Model();
		ControllerInputQuestion controller = new ControllerInputQuestion(model);
		

	}

}

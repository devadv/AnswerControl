package mvc.tests;

import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.model.Model;

public class Main {

	public static void main(String[] args) {
		System.out.println(" In main app");
		Model model = new Model();
		ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
		//ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);

	}

}

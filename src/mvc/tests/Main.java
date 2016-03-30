package mvc.tests;

import javax.swing.JOptionPane;
import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.model.Model;

public class Main {

	public static void main(String[] args) {
		System.out.println(" In main app");
		Model model = new Model();
		ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
		//ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
        
        //int dialogResult = JOptionPane.showConfirmDialog(null, "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
	}

}

package mvc.tests;

import javax.swing.JOptionPane;
import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.model.Model;

public class Main 
{

	public static void main(String[] args)
    {
		System.out.println(" In main app");
		Model model = new Model();
		//ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
		//ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
        
        Object[] setGUI = {"Input questions.", "Input answers."};
        int option = JOptionPane.showOptionDialog(null, "Choose an ui:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, setGUI, setGUI[0]);
        
        if(option == 0)// input question uci
        {
            ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
        }
        else if(option == 1)// input answer uci
        {
            ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
        }
        
    }
}



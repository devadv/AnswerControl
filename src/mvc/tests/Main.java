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
        
        Object[] setGUI = {"Invoer vragen.", "Invoer antwoorden."};
        int a = JOptionPane.showOptionDialog(null, "Shoose a uci", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, setGUI, setGUI[1]);
        if(a == 0)
        {
            ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
        }
        else if(a == 1)
        {
            ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
        }
        
    }
}



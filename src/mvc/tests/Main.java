package mvc.tests;

import javax.swing.JOptionPane;
import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.controller.ControllerInputUserAnswer;
import mvc.model.Model;

public class Main 
{

	public static void main(String[] args)
    {
		Model model = new Model();
        String name = System.getProperty("user.name");
        
		//ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
		//ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
        ControllerInputUserAnswer controllerInputUserAnswer = new ControllerInputUserAnswer(model, name);
        
        
//        Object[] setGUI = {"Input questions.", "Input answers.", "Input user answer"};
//        int option = JOptionPane.showOptionDialog(null, "Choose an user interface:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, setGUI, setGUI[0]);
//        
//        if(option == 0)// input question uci
//        {
//            ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
//        }
//        else if(option == 1)// input answer uci
//        {
//            ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
//        }
//        else if(option == 2)// input user answer
//        {
//            ControllerInputUserAnswer controllerInputUserAnswer = new ControllerInputUserAnswer(model);
//        }
//        
    }
}



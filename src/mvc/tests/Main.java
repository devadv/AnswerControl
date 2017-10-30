package mvc.tests;


import java.awt.Frame;

import javax.swing.JOptionPane;

import mvc.controller.ControllerInputQuestion;
import mvc.controller.ControllerInputUserAnswer;
import mvc.controller.ControllerUserAdvance;
import mvc.model.Model;
import mvc.testview.ViewTest;

public class Main
{

	public static void main(String[] args)
    {
		Model model = new Model();
		model.createDBConnection();


        String name = System.getProperty("user.name");
        //name = "azego";
        System.out.println("user name: " + name);
        //JOptionPane.showMessageDialog(null, "name: " + name);
		//ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
		//ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
        //ControllerInputUserAnswer controllerInputUserAnswer = new ControllerInputUserAnswer(model, name);
        //ControllerUserAdvance controllerUserAdvance = new ControllerUserAdvance(model);

        ViewTest viewTest = new ViewTest(model);


//        Object[] setGUI = {"Input questions.", "Input answers."};
//        int option = JOptionPane.showOptionDialog(null, "Choose an user interface:", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, setGUI, setGUI[0]);
//
//        if(option == 0)// input question ui
//        {
//            ControllerInputQuestion controllerInputQuestion = new ControllerInputQuestion(model);
//        }
//        else if(option == 1)// input answer ui
//        {
//            ControllerInputAnswer controllerInputAnswer = new ControllerInputAnswer(model);
//        }
//
    }
}



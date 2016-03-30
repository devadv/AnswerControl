package mvc.controller;

import javax.swing.JDesktopPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;



public class ControllerInputAnswer implements iCRUD{

	private Model model;
	private ViewInputAnswer view;

	public ControllerInputAnswer(Model model) 
    {
		this.model = model;
		createDBConnection();
		this.view = new ViewInputAnswer(model, this);
		model.addObserver(view);
		
	}

	@Override
	public void createDBConnection() {
		System.out.println("Make database connection!");
		model.createDBConnection();
	}


	@Override
	public void create() {
		System.out.println("Excercise : " + view.getExcercise());
		System.out.println("Answer : " + view.getAnswer());
		System.out.println("BlockID : " + view.getBlockID());
		model.createAnswer(view.getExcercise(), view.getAnswer(), view.getBlockID());
	
		
	}

	@Override
	public void retrieve() {
		
	}

	@Override
	public void update() {
		System.out.println("Excercise : " + view.getExcercise());
		System.out.println("Answer : " + view.getAnswer());
		System.out.println("BlockID : " + view.getBlockID());
		model.updateAnswer(view.getExcercise(), view.getAnswer(), view.getBlockID());
			
	}

	@Override
	public void delete() {
		
		
	}
    
    @Override
    public void updateQuestion()
    {
        model.updateQuestion(view.getQuestion(), view.getAnswer(), view.getBlockID());
    }
    
    private class windowClosingAdaptor extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent we)
        {
            System.out.println("Closing viewInputQuestion");
            
            if(view.isAnswerchanged())
            {
                int dialogResult = JOptionPane.showInternalConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", null, JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0)// yes button clicked
                {
                    System.out.println("Yes option");
                    //Model.updateQuestion();
                    System.exit(0);
                }
            }
            else
            {
                System.exit(0);
            }
        }
    }
}

package mvc.controller;

import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;



public class ControllerInputAnswer implements iCRUD{

	private Model model;
	private ViewInputAnswer viewInputAnswer;

	public ControllerInputAnswer(Model model) 
    {
		this.model = model;
		createDBConnection();
		viewInputAnswer = new ViewInputAnswer(model, this);
		
		model.addObserver(viewInputAnswer);
		
	}

	@Override
	public void createDBConnection() {
		System.out.println("Make database connection!");
		model.createDBConnection();
	}


	@Override
	public void create() {
		System.out.println("Excercise : " + viewInputAnswer.getExcercise());
		System.out.println("Answer : " + viewInputAnswer.getAnswer());
		System.out.println("BlockID : " + viewInputAnswer.getBlockID());
		
		
	}

	@Override
	public void retrieve() {
		
	}

	@Override
	public void update() {
		System.out.println("Excercise : " + viewInputAnswer.getExcercise());
		System.out.println("Answer : " + viewInputAnswer.getAnswer());
		System.out.println("BlockID : " + viewInputAnswer.getBlockID());
		model.updateAnswer(viewInputAnswer.getExcercise(), viewInputAnswer.getAnswer(), viewInputAnswer.getBlockID());
			
	}

	@Override
	public void delete() {
		
		
	}

    @Override
    public String getUserName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

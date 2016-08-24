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
    public String getUserName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}

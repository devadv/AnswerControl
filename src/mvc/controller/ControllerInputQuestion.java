package mvc.controller;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;
import mvc.view.ViewInputQuestion;


public class ControllerInputQuestion implements iCRUD 
{

	private Model model;
	private ViewInputQuestion view;

	public ControllerInputQuestion(Model model) {
		
		this.model = model;
		createDBConnection();
		this.view = new ViewInputQuestion(model, this);
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
		System.out.println("Question : " + view.getQuestion());
		System.out.println("BlockID : " + view.getBlockID());
		model.createQuestion(view.getExcercise(), view.getQuestion(), view.getBlockID());
		
	}

	@Override
	public void retrieve() {
		
		
	}

	@Override
	public void update() {
		model.updateQuestion(view.getExcercise(), view.getQuestion(), view.getBlockID());
		
	}

	@Override
	public void delete() {
		
		
	}

	
}// end class ControllerInputQuestion

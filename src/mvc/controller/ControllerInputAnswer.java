package mvc.controller;


import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;



public class ControllerInputAnswer implements iCRUD{

	private Model model;
	private ViewInputAnswer view;

	public ControllerInputAnswer(Model model) {
		
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
		System.out.println("Question : " + view.getQuestion());
		System.out.println("BlockID : " + view.getBlockID());
		model.createAnswer(view.getExcercise(), view.getQuestion(), view.getBlockID());
	
		
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

	

	

	
	

}

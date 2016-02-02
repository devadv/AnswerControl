package mvc.controller;


import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;



public class ControllerInputAnswer implements iCRUD{

	private Model model;
	private View viewQuestion;
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
		System.out.println("Excercise : " + viewQuestion.getExcercise());
		System.out.println("Question : " + viewQuestion.getQuestion());
		System.out.println("BlockID : " + viewQuestion.getBlockID());
		model.createAnswer(viewQuestion.getExcercise(), viewQuestion.getQuestion(), viewQuestion.getBlockID());
	
		
	}

	@Override
	public void retrieve() {
		
	}

	@Override
	public void update() {
		model.updateQuestion(viewQuestion.getExcercise(), viewQuestion.getQuestion(), viewQuestion.getBlockID());
			
	}

	@Override
	public void delete() {
		
		
	}

	

	

	
	

}

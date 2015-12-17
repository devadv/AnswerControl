package mvc.controller;


import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;
import mvc.view.ViewInputQuestion;


public class ControllerInputAnswer implements iControllerRecord{

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
	public void createRecord() {
		System.out.println("Excercise : " + viewQuestion.getExcercise());
		System.out.println("Question : " + viewQuestion.getQuestion());
		System.out.println("BlockID : " + viewQuestion.getBlockID());
		model.createQuestion(viewQuestion.getExcercise(), viewQuestion.getQuestion(), viewQuestion.getBlockID());
	
		
	}

	@Override
	public void getRecord() {
		
	}

	@Override
	public void updateRecord() {
		model.updateQuestion(viewQuestion.getExcercise(), viewQuestion.getQuestion(), viewQuestion.getBlockID());
			
	}

	@Override
	public void deleteRecord() {
		
		
	}

	@Override
	public boolean isRecord() {
		
		return false;
	}

	

	
	

}

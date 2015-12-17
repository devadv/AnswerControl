package mvc.controller;


import mvc.model.Model;
import mvc.view.View;
import mvc.view.ViewInputAnswer;
import mvc.view.ViewInputQuestion;


public class ControllerInputQuestion implements iControllerRecord{

	private Model model;
	private View view;


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
	public void createRecord() {
		System.out.println("Excercise : " + view.getExcercise());
		System.out.println("Question : " + view.getQuestion());
		System.out.println("BlockID : " + view.getBlockID());
		model.createQuestion(view.getExcercise(), view.getQuestion(), view.getBlockID());
		
	}

	@Override
	public void getRecord() {
		
		
	}

	@Override
	public void updateRecord() {
		model.updateQuestion(view.getExcercise(), view.getQuestion(), view.getBlockID());
		
	}

	@Override
	public void deleteRecord() {
		
		
	}

	@Override
	public boolean isRecord() {
		
		return false;
	}

	

	
	

}

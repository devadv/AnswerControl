package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.model.Model;

public class ControllerInputQuestionAnswerView implements ActionListener{
	private Model model;
	private InputQuestionAnswerView view;

	/**
	 * Constructor to make the view for input questions and answers.
	 */
	public ControllerInputQuestionAnswerView(){
		model = new Model();
		model.createDBConnection();
		view = new InputQuestionAnswerView(model);
		model.addObserver(view);
		view.addSaveButtonListener(this);

	}// end constructor ControllerInputQuestionAnswerView

	// Execute when save button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {

		if(model.questionExist(view.getExerciseNr()) == true){
			model.updateQuestion(view.getExerciseNr(), view.questionTextArea.getText(), view.getBlockNumber());
		}
		else{
			model.createQuestion(view.getExerciseNr(), view.questionTextArea.getText(), view.getBlockNumber());
		}

		if(model.answerExist(view.getExerciseNr())){
			model.updateAnswer(view.getExerciseNr(), "TODO", view.getBlockNumber());
		}
		else{
			model.createAnswer(view.getExerciseNr(), "TODO", view.getBlockName());
		}
	}// end method actionPerformed

}// end class ControllerInputQuestionAnswerView

package mvc.controller;

import javax.swing.JOptionPane;

import mvc.model.Model;
import mvc.view.AbstractView;

public abstract class AbstractController {
	protected Model model;


	public AbstractController() {
		this.model = new Model();
		model.createDBConnection();

	}

    /**
     * Check if text is changed or doesn't exist in database and ask user to save it.
     * @param text from database
     * @param exist text from database
     * @param textToCompare from textArea
     */
	public void saveMessage(String text, String textToCompare, mvc.view.AbstractView view) {
		if(text == null){
			text = "";
		}

		if(!( text.equals(textToCompare) )){
			// message to user
			int option = JOptionPane.showConfirmDialog(null, "Save to database?",
					"Warning", JOptionPane.YES_NO_OPTION);
			if(option == 0){ // Yes button
				saveUserAnswer(view);
			}
			else if(option == 1){ // No button
				// Do nothing
			}
		}

	}// end method saveMessage

	/**
	 * Method to sava user answer to data base.
	 */
	public void saveUserAnswer(AbstractView view) {
		if(model.answerExist(view.getExerciseNr()) || model.questionExist(view.getExerciseNr())){
			model.updateAnswer(view.getExerciseNr(), view.getAnswerText(), view.getBlockNumber());
			model.updateQuestion(view.getExerciseNr(), view.getQuestionTextArea().getText(), view.getBlockNumber());
		}
		else{
			model.createAnswer(view.getExerciseNr(), view.getAnswerText(), view.getBlockNumber());
			model.createQuestion(view.getExerciseNr(), view.getQuestionTextArea().getText(), view.getBlockNumber());
		}
	}// end method saveUserAnswer

}

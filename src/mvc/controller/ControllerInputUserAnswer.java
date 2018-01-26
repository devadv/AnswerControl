package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mvc.model.Model;
import mvc.view.InputUserAnswerView;

public class ControllerInputUserAnswer implements ActionListener{
    private Model model;
    private InputUserAnswerView view;
    private String userName;

    /**
     * Constructor to make the view to see the questions and user answers textAreas.
     */
	public ControllerInputUserAnswer() {
		model = new Model();
		model.createDBConnection();
		view = new InputUserAnswerView(model, this);
		model.addObserver(view);
		view.addSaveButtonListener(this);
		userName = view.getUserName();
	}

	// Execute when save button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		if(model.userAnswerExist(view.getExerciseNr(), userName)){
			model.updateUserAnswer(view.answer.getText(), view.getExerciseNr(), userName);
		}
		else{
			model.createUserAnswer(view.answer.getText(), view.getExerciseNr(), userName);
		}
	}

    /**
     * Check if text is changed or doesn't exist in database and ask user to save it.
     * @param text from database
     * @param exist text from database
     * @param textToCompare from textArea
     */
	public void saveMessage(String text, boolean exist, String textToCompare) {

		if(text.equals(textToCompare) == false){
			// message to user
			int option = JOptionPane.showConfirmDialog(null, "Save to database?",
					"Warning", JOptionPane.YES_NO_OPTION);
			if(option == 0){ // Yes button
				if(exist){
					// user answer exists
					model.updateUserAnswer(textToCompare, view.getExerciseNr(), userName);

				}
				else{
					// make field in database
					model.createUserAnswer(text, view.getExerciseNr(), userName);
				}
			}
			else if(option == 1){ // No button
				// Do nothing
			}
		}

	}// end method saveMessage

} // end class ControllerInputUserAnswer

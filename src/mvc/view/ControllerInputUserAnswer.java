package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.model.Model;

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
		view = new InputUserAnswerView(model);
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


} // end class ControllerInputUserAnswer

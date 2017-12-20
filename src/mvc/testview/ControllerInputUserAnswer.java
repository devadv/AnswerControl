package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.model.Model;

public class ControllerInputUserAnswer implements ActionListener{
    private Model model;
    private InputUserAnswerView view;
    private String userName;

	public ControllerInputUserAnswer() {
		model = new Model();
		model.createDBConnection();
		view = new InputUserAnswerView(model);
		model.addObserver(view);
		view.addSaveButtonListener(this);
		userName = System.getProperty("user.name");
		
	}

	// execute when save button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		if(model.userAnswerExist(view.getExerciseNr(), userName))
			model.updateAnswer(view.getExerciseNr(), view.answerUser.getText(), view.getBlockId());
		else{
			model.createUserAnswer(view.answerUser.getText(), view.getExerciseNr() , userName);
		}
	}

} // end class ControllerInputUserAnswer

package mvc.view;

import java.awt.event.ActionEvent;
import java.util.Observable;

import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.controller.iController;
import mvc.controller.iControllerRecord;
import mvc.model.Model;

public class ViewInputQuestion extends View {

	/*private Model model;
	private iControllerRecord controller;*/

	public ViewInputQuestion(Model model, iControllerRecord controller) {
		super(model, controller);
		//this.model = model;
		model.createDBConnection();
		setGUI();
		questionField.setText(model.getQuestion(getExcercise()));

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == exercise_nr) {
			questionField.setText(model.getQuestion(getExcercise()));
		} else if (event.getSource() == btnSave) {
			if (!(model.isQuestion(getExcercise()))) {
				controller.createRecord();
			} else {
				model.updateQuestion(getExcercise(), getQuestion(),
						getBlockID());
			}

		} else if (event.getSource() == btnNext) {
			exercise_nr.setSelectedIndex(exercise_nr.getSelectedIndex() + 1);
			questionField.setText(model.getQuestion(getExcercise()));
		} else if (event.getSource() == btnPrevious) {
			exercise_nr.setSelectedIndex(exercise_nr.getSelectedIndex() - 1);
			questionField.setText(model.getQuestion(getExcercise()));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("model updated");
		questionField.setText(model.getQuestion(getExcercise()));
	}

	@Override
	public void setGUI() {
		super.setGUI();
		label.setText("Question");
		pack();
		setLocation(400, 100);
		setVisible(true);
	}

}

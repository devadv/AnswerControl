package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.model.Model;

public class ControllerSave implements ActionListener{
	private Model model;
	private SaveView view;

	public ControllerSave() {
		model = new Model();
		model.createDBConnection();
		view = new InputQuestionAnswerView(model);
		model.addObserver(view);
		view.addSaveButtonListener(this);

	}

	// save button
	@Override
	public void actionPerformed(ActionEvent e) {


	}

}

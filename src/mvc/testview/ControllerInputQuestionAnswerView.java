package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.model.Model;

public class ControllerInputQuestionAnswerView implements ActionListener{
	private Model model;
	private InputQuestionAnswerView view;
	private String userName;

	/**
	 * Constructor to make the view for input questions and answers.
	 */
	public ControllerInputQuestionAnswerView(){
		this.model = new Model();
		this.view = new InputQuestionAnswerView(model);
		this.userName = System.getProperty("user.name");
		model.addObserver(view);
		view.addSaveButtonListener(this);

	}// end constructor ControllerInputQuestionAnswerView

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}// end class ControllerInputQuestionAnswerView

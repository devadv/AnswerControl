package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mvc.model.Model;
import mvc.view.InputQuestionAnswerView;

public class ControllerInputQuestionAnswer extends AbstractController implements ActionListener{
	private InputQuestionAnswerView view;
	private String userName;

	/**
	 * Constructor to make the view for input questions and answers.
	 */
	public ControllerInputQuestionAnswer(){
		super();
		view = new InputQuestionAnswerView(model, this);
		model.addObserver(view);
		view.addSaveButtonListener(this);
		userName = view.getUserName();

	}// end constructor ControllerInputQuestionAnswerView

	// Execute when save button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		saveUserAnswer(view);

	}// end method actionPerformed


}// end class ControllerInputQuestionAnswerView

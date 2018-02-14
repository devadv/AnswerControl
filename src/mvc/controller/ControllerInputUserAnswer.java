package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mvc.model.Model;
import mvc.view.InputUserAnswerView;

public class ControllerInputUserAnswer extends AbstractController implements ActionListener{
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
		saveUserAnswer(view);
	}


} // end class ControllerInputUserAnswer

package mvc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.controller.iControllerAnswerQuestion;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputQuestion extends View {

	
	

	public ViewInputQuestion(Model model, iCRUD controller) {
		super(model, controller);
		setGUI();
		questionField.setText(model.retrieveQuestion(getExcercise()));
		
		
	}
	@Override
	public void setGUI() {
		super.setGUI();
		
		panel.add(panelQuestion);
        panel.add(panelBottom);
        
        this.pack();
		this.setTitle("Invoer vragen");
        this.setSize(600, 600);
		this.setLocation(200, 100);
		this.getContentPane().add(panel);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == exercise_id) {
			questionField.setText(model.retrieveQuestion(getExcercise()));
		} else if (event.getSource() == btnSave) {
			if (!(model.isQuestion(getExcercise()))) {
				controller.create();
			} else {
				model.updateQuestion(getExcercise(), getQuestion(),
						getBlockID());
			}

		} else if (event.getSource() == btnNext) {
			exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() + 1);
			questionField.setText(model.retrieveQuestion(getExcercise()));
		} else if (event.getSource() == btnPrevious) {
			exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() - 1);
			questionField.setText(model.retrieveQuestion(getExcercise()));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("model updated");
		questionField.setText(model.retrieveQuestion(getExcercise()));
	}

	
	


}

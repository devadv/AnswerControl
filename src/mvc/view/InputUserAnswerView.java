package mvc.view;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.controller.ControllerInputUserAnswer;
import mvc.model.Model;

public class InputUserAnswerView extends SaveView {

	public JTextArea answer;
	private JButton checkAnswer;
	private ControllerInputUserAnswer controllerInputUserAnswer;

	public InputUserAnswerView(Model model, ControllerInputUserAnswer controllerInputUserAnswer) {
		super(model);
		this.setTitle("Input user answer.");
        this.setSize(600, 800);
        this.setLocation(700, 150);
        this.setVisible(true);
        this.controllerInputUserAnswer = controllerInputUserAnswer;

        getQuestionTextArea().setEditable(false);
        answer = new JTextArea(10, 35);
        answer.setFont(textAreaFont);
		answer.setMargin(new Insets(5, 10, 0, 10));
		answer.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(answer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 33, 10, 34));
		panelCenter.add(scrollPane);
		checkAnswer = new JButton("Check answer");
		checkAnswer.addActionListener(new checkAnswerListener());
		panelBottom.add(checkAnswer);
		checkAnswer.setEnabled(false);
		this.addWindowListener(new windowClosingAdaptor());
		updateView();
	}// end constructor InputUserAnswerView

	/**
	 * Listerner for checkAnswer button
	 * @author hintveld
	 *
	 */
	private class checkAnswerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String blockId = String.valueOf(blockBox.getSelectedItem());
			UserAnswerCorrectAnswerView userAnswerCorrectAnswerView =
					new UserAnswerCorrectAnswerView(model, blockId);
			//InputUserAnswerView.this.setVisible(false);

		}
	}// end class checkAnswerListener

	@Override
	public void updateView() {
		super.updateView();
		answer.setText(model.retrieveAnswerUser(String.valueOf(exerciseBox.getSelectedItem()),
				getUserName()));

		if(model.allAnswersFilled(String.valueOf(getBlockName()), getUserName()) == exerciseBox.getItemCount()){
			checkAnswer.setEnabled(true);
		}
		else{
			checkAnswer.setEnabled(false);
		}
	}// end method updateView

	@Override
	public void btnNext() {
		// check if text has been changed
		controllerInputUserAnswer.saveMessage(model.retrieveAnswerUser(getExerciseNr(), getUserName()),
				model.userAnswerExist(getExerciseNr(), getUserName()), answer.getText());
		super.btnNext();
	}



	@Override
	public void btnPrevious() {
		// check if text has been changed
		controllerInputUserAnswer.saveMessage(model.retrieveAnswerUser(getExerciseNr(), getUserName()),
				model.userAnswerExist(getExerciseNr(), getUserName()), answer.getText());
		super.btnPrevious();
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView();
	}

	/**
	 * Inner class if window is closing
	 * @author hintveld
	 *
	 */
	class windowClosingAdaptor extends WindowAdapter{

		public void windowClosing(WindowEvent e) {
			controllerInputUserAnswer.saveMessage(model.retrieveAnswerUser(getExerciseNr(), getUserName()),
					model.userAnswerExist(getExerciseNr(), getUserName()), answer.getText());
			System.exit(1);
		}
	}// end class windowClosingAdaptor

}// end class InputUserAnswerView

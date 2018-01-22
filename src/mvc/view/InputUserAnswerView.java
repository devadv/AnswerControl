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
import mvc.model.Model;

public class InputUserAnswerView extends SaveView {

	public JTextArea answer;
	private JButton checkAnswer;

	public InputUserAnswerView(Model model) {
		super(model);
		this.setTitle("Input user answer.");
        this.setSize(600, 800);
        this.setLocation(700, 150);
        this.setVisible(true);

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
		saveMessage(); // check if text has been changed
		super.btnNext();
	}

	@Override
	public void btnPrevious() {
		saveMessage();// check if text has been changed
		super.btnPrevious();
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView();
	}

	/**
	 * Check if text is changed or doesn't exist in database
	 * and ask user to save it.
	 */
	public void saveMessage() {

		if(model.retrieveAnswerUser(getExerciseNr(), getUserName()).equals( answer.getText()) == false){
			if(model.userAnswerExist(getExerciseNr(), getUserName()) == false){

				// message to user
				int option = JOptionPane.showConfirmDialog(null, "Save to database?", "Warning",
						JOptionPane.YES_NO_OPTION);
				if(option == 0){ // Yes button
					if(model.userAnswerExist(getExerciseNr(), getUserName())){
						// user answer exists
						model.updateUserAnswer(answer.getText(), getExerciseNr(), getUserName());
					}
					else{
						// make field in database
						model.createUserAnswer(answer.getText(), getExerciseNr(), getExerciseNr());
					}
				}
				else if(option == 1){ // No button
					// Do nothing
				}
			}
		}// end outer if
	}// end method saveMessage

	/**
	 * Inner class if window is closing
	 * @author hintveld
	 *
	 */
	public class windowClosingAdaptor extends WindowAdapter{

		public void windowClosing(WindowEvent e) {
			saveMessage();
		}
	}

}// end class InputUserAnswerView

package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		answer = new JTextArea(30, 40);
		JScrollPane scrollPane = new JScrollPane(answer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
		panelCenter.add(scrollPane);
		checkAnswer = new JButton("Check answer");
		checkAnswer.addActionListener(new checkAnswerListener());
		panelBottom.add(checkAnswer);
		checkAnswer.setEnabled(false);
		updateView();
	}

	private class checkAnswerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String blockId = String.valueOf(blockBox.getSelectedItem());
			UserAnswerCorrectAnswerView userAnswerCorrectAnswerView =
					new UserAnswerCorrectAnswerView(model, blockId);
			//InputUserAnswerView.this.setVisible(false);

		}

	}


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
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView();
	}

	@Override
	public void btnNext() {
		if(model.userAnswerExist(getExerciseNr(), getUserName()) == false ||
				model.retrieveAnswer(getExerciseNr()) != answer.getText()){
			saveMessage();
		}
		super.btnNext();
	}

	@Override
	public void btnPrevious() {

		super.btnPrevious();
	}

	public void saveMessage() {
		JOptionPane.showConfirmDialog(null, "Save", "Warning", JOptionPane.YES_NO_OPTION);
	}

}// end class InputUserAnswerView

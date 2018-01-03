package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
        this.setLocation(800, 200);
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
		updateView();
	}

	private class checkAnswerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UserAnswerCorrectAnswerView UACAV = new UserAnswerCorrectAnswerView(model);

		}

	}

	@Override
	public void updateView() {
		super.updateView();
		answer.setText(model.retrieveAnswerUser(String.valueOf(exerciseBox.getSelectedItem()),
				System.getProperty("user.name")));
	}

	@Override
	public void update(Observable o, Object arg) {
		updateView();

	}


}// end class InputUserAnswerView

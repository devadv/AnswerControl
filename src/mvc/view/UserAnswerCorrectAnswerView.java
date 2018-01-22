package mvc.view;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.model.Model;

public class UserAnswerCorrectAnswerView extends AbstractView{

	private JTextArea answer;
	private JTextArea correctAnswer;
	private JLabel answerLabel;
	private JLabel correctAnswerLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;

	/**
	 * constructor makes view with question, user answer and correct answer textArea
	 * to check the user answer with correct answer.
	 * @param model
	 * @param blockId
	 */
	public UserAnswerCorrectAnswerView(Model model, String blockId) {

		super(model);
		this.setTitle("Check answer");
		this.setSize(1000, 800);
        this.setLocation(500, 150);
        this.setVisible(true);
		setComponents();
		blockBox.setSelectedItem(blockId);

		JPanel answerPanel = new JPanel(new BorderLayout());
		answerLabel = new JLabel("Your answer:");
		answer = new JTextArea(30, 38);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setMargin(new Insets(5, 10, 0, 5));
		answer.setCaretPosition(0);

		scrollPane = new JScrollPane(answer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
		answerPanel.add(scrollPane, BorderLayout.CENTER);
		answerPanel.add(answerLabel, BorderLayout.NORTH);


		JPanel correctAnswerPanel = new JPanel(new BorderLayout());
		correctAnswerLabel = new JLabel("Correct answer:");
		correctAnswer = new JTextArea(30, 38);
		correctAnswer.setLineWrap(true);
		correctAnswer.setWrapStyleWord(true);
		correctAnswer.setMargin(new Insets(5, 10, 0, 5));

		scrollPane1 = new JScrollPane(correctAnswer);
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		correctAnswerPanel.setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
		correctAnswerPanel.add(scrollPane1, BorderLayout.CENTER);
		correctAnswerPanel.add(correctAnswerLabel, BorderLayout.NORTH);

		panelCenter.add(answerPanel, BorderLayout.WEST);
		panelCenter.add(correctAnswerPanel, BorderLayout.EAST);
		setAnswerAndCorrectAnswer();
		updateView();

	}// end constructor UserAnswerCorrectAnswerView

	/**
	 * button next method
	 */
	int s;
	@Override
	public void btnNext() {
		super.btnNext();
		setAnswerAndCorrectAnswer();
		answer.setCaretPosition(0);
		correctAnswer.setCaretPosition(0);
	}

	/**
	 * button previous method
	 */
	@Override
	public void btnPrevious() {
		super.btnPrevious();
		setAnswerAndCorrectAnswer();
		answer.setCaretPosition(0);
		correctAnswer.setCaretPosition(0);
	}

	/**
	 * method to set answer and correctAnswer textArea.
	 */
	public void setAnswerAndCorrectAnswer() {
		answer.setText(model.retrieveAnswerUser(getExerciseNr(), System.getProperty("user.name")));
		correctAnswer.setText(model.retrieveAnswer(getExerciseNr()));
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

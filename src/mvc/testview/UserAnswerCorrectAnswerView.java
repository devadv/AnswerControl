package mvc.testview;

import java.awt.BorderLayout;
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

	public UserAnswerCorrectAnswerView(Model model, String blockId) {

		super(model);
		this.setTitle("Check answer");
		this.setSize(1000, 800);
        this.setLocation(500, 150);
        this.setVisible(true);
		setComponents();
		answerLabel = new JLabel("Answer:");
		correctAnswerLabel = new JLabel("Correct answer:");

		JPanel answerPanel = new JPanel(new BorderLayout());
		answer = new JTextArea(30, 40);
		JScrollPane scrollPane = new JScrollPane(answer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
		answerPanel.add(scrollPane, BorderLayout.WEST);

		correctAnswer = new JTextArea(30, 40);
		JScrollPane scrollPane1 = new JScrollPane(correctAnswer);
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
		answerPanel.add(scrollPane1, BorderLayout.EAST);

		panelCenter.add(answerPanel);
		blockBox.setSelectedItem(blockId);
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

package mvc.testview;

import java.awt.Insets;
import java.util.Observable;

import javax.swing.JTextArea;

import mvc.model.Model;

public class InputQuestionAnswerView extends SaveView {
	private JTextArea answer;

	/**
	 * Constructor to make view to input question and aswer.
	 * @param model
	 */
	public InputQuestionAnswerView(Model model) {
		super(model);
        this.setTitle("TestView");
        this.setSize(600, 800);
        this.setLocation(600, 170);
        this.setVisible(true);
        this.answer = new JTextArea();
        answer.setFont(textAreaFont);
        answer.setMargin(new Insets(5, 10, 0, 10));
        answer.setLineWrap(true);
        panelCenter.add(answer);
	}


	/**
	 * Update question and answer text area.
	 */
	@Override
	public void update(Observable o, Object arg) {
		updateView();

	}

}

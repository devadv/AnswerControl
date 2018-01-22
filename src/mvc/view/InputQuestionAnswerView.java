package mvc.view;

import java.awt.Insets;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
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
        setAnswer(new JTextArea(10, 37));
        getAnswer().setFont(textAreaFont);
        getAnswer().setMargin(new Insets(5, 10, 0, 10));
        getAnswer().setLineWrap(true);
        getAnswer().setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(getAnswer());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 34, 10, 34) );
        panelCenter.add(scrollPane);
        updateView();
	}// end constructor InputQuestionAnswerView


	public void updateView() {
		super.updateView();
		
		getAnswer().setText(model.retrieveAnswer(getExerciseNr()));
		getAnswer().setCaretPosition(0);
		
	}


	/**
	 * Update question and answer text area.
	 */
	@Override
	public void update(Observable o, Object arg) {
		updateView();

	}


	public JTextArea getAnswer() {
		return answer;
	}


	public void setAnswer(JTextArea answer) {
		this.answer = answer;
	}

}

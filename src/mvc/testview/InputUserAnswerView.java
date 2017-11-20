package mvc.testview;

import java.util.Observable;

import javax.swing.JTextArea;

import mvc.model.Model;

public class InputUserAnswerView extends SaveView {
	private JTextArea question;
	private JTextArea answer;

	public InputUserAnswerView(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}


}

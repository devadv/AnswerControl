package mvc.testview;

import java.util.Observable;

import javax.swing.JTextArea;

import mvc.model.Model;

public class InputUserAnswerView extends SaveView {
	
	private JTextArea answer;
	
	public InputUserAnswerView(Model model) {
		super(model);
		setComponents();
		answer = new JTextArea(20, 40);
		panelBottom.add(answer);
	}

	@Override
	public void setComponents() {
		super.setComponents();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {


	}


}

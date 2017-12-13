package mvc.testview;

import java.util.Observable;
import javax.swing.JTextArea;
import mvc.model.Model;

public class InputUserAnswerView extends SaveView {

	private JTextArea answer;

	public InputUserAnswerView(Model model) {
		super(model);
		this.setTitle("Input user answer.");
        this.setSize(600, 800);
        this.setLocation(800, 200);
        this.setVisible(true);
		answer = new JTextArea(20, 40);
		panelBottom.add(answer, 1);
	}



	@Override
	public void update(Observable o, Object arg) {


	}


}

package mvc.testview;

import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
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
		answer = new JTextArea(30, 40);
		JScrollPane scrollPane = new JScrollPane(answer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 37, 10, 37));
		panelCenter.add(scrollPane);
		updateView();
	}

	@Override
	public void update(Observable o, Object arg) {


	}


}

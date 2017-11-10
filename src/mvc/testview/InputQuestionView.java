package mvc.testview;

import java.util.Observable;

import mvc.model.Model;

public class InputQuestionView extends SaveView {

	public InputQuestionView(Model model) {
		super(model);
        this.setTitle("TestView");
        this.setSize(600, 800);
        this.setLocation(800, 200);
        this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

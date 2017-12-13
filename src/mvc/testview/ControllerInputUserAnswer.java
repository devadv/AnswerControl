package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.model.Model;

public class ControllerInputUserAnswer implements ActionListener{
    private Model model;
    private InputUserAnswerView view;
	
	public ControllerInputUserAnswer() {
		model = new Model();
		model.createDBConnection();
		view = new InputUserAnswerView(model);
		model.addObserver(view);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

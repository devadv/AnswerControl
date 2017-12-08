package mvc.testview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.model.Model;

public class ControllerInputUser implements ActionListener{
    private Model model;
    private InputUserAnswerView view;
	
	public ControllerInputUser() {
		model = new Model();
		model.createDBConnection();
		view = new InputUserAnswerView(model);
		model.addObserver(view);
		view.addSaveButtonListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

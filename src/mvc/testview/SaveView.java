package mvc.testview;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import mvc.model.Model;

public abstract class SaveView extends AbstractView {

	private JButton btnSave;

	public SaveView(Model model) {
		super(model);

		setComponents();

	}

	@Override
	public void setComponents() {
		super.setComponents();
		btnSave = new JButton("Save");

		panelBottom.add(btnSave, 0);
	}

	public void addSaveButtonListener(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}

}

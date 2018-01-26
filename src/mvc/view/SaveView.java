package mvc.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import mvc.model.Model;

public abstract class SaveView extends AbstractView {

	public JButton btnSave;

	/**
	 * constructor SaveView
	 * @param model
	 */
	public SaveView(Model model) {
		super(model);
		setComponents();

	}

	/**
	 * sets components for this view and calls super class setComponent
	 */
	@Override
	public void setComponents() {
		super.setComponents();
		btnSave = new JButton("Save");
		panelBottom.add(btnSave, 0);
	}

	/**
	 * add ActionListener to button save
	 * @param actionListener
	 */
	public void addSaveButtonListener(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}


}

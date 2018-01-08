package mvc.testview;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import mvc.model.Model;

public abstract class SaveView extends AbstractView {

	private JButton btnSave;

	/**
	 * constructor SaveView
	 * @param model
	 */
	public SaveView(Model model) {
		super(model);
		setComponents();

	}

	/**
	 * method sets components for this view and calls super class setComponent
	 */
	@Override
	public void setComponents() {
		super.setComponents();
		btnSave = new JButton("Save");
		panelBottom.add(btnSave, 0);
	}

	/**
	 * method add ActionListener to button save
	 * @param actionListener
	 */
	public void addSaveButtonListener(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}

}

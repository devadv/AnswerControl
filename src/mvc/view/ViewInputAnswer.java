package mvc.view;


import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputAnswer extends View {
	

	private iCRUD controller;
	private Model model;
	private JTextArea answerField ;

	public ViewInputAnswer(Model model, iCRUD controller) {
		super(model, controller);
		this.model = model;
		this.controller = controller;
		setGUI();
		questionField.setText(model.retrieveQuestion(getExcercise()));
		answerField.setText(model.retrieveAnswer(getExcercise())+ " voor " + getExcercise() );// change when model is updated
	}

	@Override
	public void setGUI() {
		super.setGUI();
		answerField = new JTextArea(25, 38);
		answerField.setLineWrap(true);
		answerField.setWrapStyleWord(true);
		answerField.setFont(textAreaFont);
		questionField.setEditable(false);
		questionField.setColumns(38);
		questionField.setRows(5);
		
		JScrollPane jspAnswer = new JScrollPane(answerField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(panelQuestion);
		JPanel panelAnswer = new JPanel();
		panelAnswer.add(jspAnswer);
		panel.add(panelAnswer);
		panel.add(panelBottom);
		this.pack();
		
		this.setTitle("Invoer Antwoorden");
		this.setSize(600, 800);
		this.setLocation(1000, 200);
		this.getContentPane().add(panel);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==exercise_id){
			System.out.println("exercise_id changed!");
			questionField.setText(model.retrieveQuestion(getExcercise()));
			answerField.setText(model.retrieveAnswer(getExcercise())+ " voor " + getExcercise() );// change when model is updated
		}else if(event.getSource()==btnSave){
			if(!model.isQuestion(getExcercise())){
				controller.create();
			}else {
				controller.update();
			}
			
		}else if(event.getSource()==btnNext){
			exercise_id.setSelectedIndex(exercise_id.getSelectedIndex()+1);
			questionField.setText(model.retrieveQuestion(getExcercise()));
			answerField.setText(model.retrieveAnswer(getExcercise())+ " voor " + getExcercise() );// change when model is updated
		}else if(event.getSource()==btnPrevious){
			exercise_id.setSelectedIndex(exercise_id.getSelectedIndex()-1);
			answerField.setText(model.retrieveAnswer(getExcercise())+ " voor " + getExcercise() );// change when model is updated
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("model updated in Answer");
		questionField.setText(model.retrieveQuestion(getExcercise()));
		
		
	}
	public String getAnswer() {
		 
		return answerField.getText();
	}

	
	

	
}

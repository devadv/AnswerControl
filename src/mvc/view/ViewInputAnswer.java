package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import mvc.controller.ControllerInputQuestion;
import mvc.controller.iControllerAnswerQuestion;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputAnswer extends ViewInputQuestion {
	

	private iCRUD controller;
	private Model model;
	private JTextArea answer ;

	public ViewInputAnswer(Model model, iCRUD controller) {
		super(model, controller);
		this.model = model;
		this.controller = controller;
		//model.createDBConnection();
		setGUI();
		questionField.setText(model.retrieveQuestion(getExcercise()));
	}

	@Override
	public void setGUI() {
		super.setGUI();
		answer = new JTextArea(10, 38);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(textAreaFont);
		questionField.setEditable(false);
		questionField.setRows(10);
		
		JScrollPane jspAnswer = new JScrollPane(answer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JPanel panelAnswer = new JPanel();
		panelAnswer.add(jspAnswer);
		panel.add(panelAnswer);
		panel.add(panelBottom);
		
		System.out.println("antwoorden");
		this.setTitle("Invoer Antwoorden");
		this.setSize(600, 800);
		this.setLocation(1000, 200);
		this.getContentPane().add(panel);
		this.setVisible(true);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==exercise_id){
			System.out.println("exercise_id changed!");
			questionField.setText(model.retrieveQuestion(getExcercise()));
		}else if(event.getSource()==btnSave){
			if(!model.isQuestion(getExcercise())){
				controller.create();
			}else {
				controller.update();
			}
			
		}else if(event.getSource()==btnNext){
			exercise_id.setSelectedIndex(exercise_id.getSelectedIndex()+1);
			questionField.setText(model.retrieveQuestion(getExcercise()));
		}else if(event.getSource()==btnPrevious){
			exercise_id.setSelectedIndex(exercise_id.getSelectedIndex()-1);
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("model updated in Answer");
		questionField.setText(model.retrieveQuestion(getExcercise()));
		
		
	}

	
	

	
}

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
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import mvc.controller.ControllerInputQuestion;
import mvc.controller.iControllerAnswerQuestion;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputAnswer extends View {
	

	private iCRUD controller;
	private Model model;
	private JTextArea answer;

	public ViewInputAnswer(Model model, iCRUD controller) {
		super(model, controller);
		this.model = model;
		this.controller = controller;
		setGUI();
		questionField.setText(model.retrieveQuestion(getExcercise()));
	}

	@Override
	public void setGUI() {
		super.setGUI();
		label.setText(String.format("%20s %30s" ,"Question","Answer"));
		
		questionField.setEditable(false);
		answer = new JTextArea(10, 20);
		answer.setBorder(BorderFactory.createLineBorder(Color.green,5));
		//JLabel distance = new JLabel("distance");
		//center.add(distance, BorderLayout.CENTER);
		center.add(answer, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(800, 200);
		setVisible(true);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==exercise_nr){
			System.out.println("exercise_nr changed!");
			questionField.setText(model.retrieveQuestion(getExcercise()));
		}else if(event.getSource()==btnSave){
			if(!model.isQuestion(getExcercise())){
				controller.create();
			}else {
				controller.update();
			}
			
		}else if(event.getSource()==btnNext){
			exercise_nr.setSelectedIndex(exercise_nr.getSelectedIndex()+1);
			questionField.setText(model.retrieveQuestion(getExcercise()));
		}else if(event.getSource()==btnPrevious){
			exercise_nr.setSelectedIndex(exercise_nr.getSelectedIndex()-1);
		}
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("model updated in Answer");
		questionField.setText(model.retrieveQuestion(getExcercise()));
		
		
	}

	
	

	
}

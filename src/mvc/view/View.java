package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.xml.ws.Dispatch;

import mvc.controller.iController;
import mvc.controller.iControllerRecord;
import mvc.model.Model;

public abstract class View extends JFrame implements iView, Observer{

	private String[] listExercises = {"1.1", "1.2", "1.3", "1.4","1.5","1.6","1.7","1.8","1.9","1.10","1.11","1.12",
			"1.13","1.14","1.15","1.16","1.17","1.18","1.19", "1.20","1.21","1.22","1.23","1.24","1.25","1.26","1.27",
			"1.28","1.28","1.29","1.30","1.31","1.32","1.33","1.34","1.35","1.36","2.1", "2.2", "2.3", "2.4","2.5","2.6",
			"2.7","2.8","2.9","2.10","2.11","2.12","2.13","2.14","2.15","2.16","2.17","2.18","2.19", "2.20","2.21",
			"2.22","2.23","2.24","2.25","2.26","2.27","2.28","2.29","2.30","2.31","2.32","2.33","2.34","2.35","2.36",
			"2.37","2.38","2.39","2.40","2.41", "2.42", "2.43","2.44","2.45","2.46","2.47","2.28","2.29", "2.30","2.31", 
			"2.32", "2.33", "2.34","2.35","2.36","2.37","2.38","2.39","2.40","2.41", "2.42", "2.43", "2.44","2.45",
			"2.46","2.47","2.48","2.49","2.50","2.61", "2.62", "2.63", "2.64","2.65","2.66","26.7","2.68","2.69",
			"2.70","2.81", "2.82", "2.83", "2.84","2.85","2.86","2.87","2.88","2.89","2.90","2.91", "2.92", "2.93"
 };
	private String[] blocks = {"1", "2", "3", "4", "5", "6", "7","8", "9","10", "11", "12","13"};
	
	JTextArea questionField = new JTextArea(10, 20);
	protected JButton btnSave = new JButton("Save");
	protected JButton btnNext = new JButton("Next");
	protected JButton btnPrevious = new JButton("Previous");
	protected JComboBox<String> exercise_nr = new JComboBox<>(listExercises);
	
	protected JPanel center;
	protected JPanel btnGroup;
	protected JPanel top;
	protected JLabel label;
	private JComboBox<String> blocks_id = new JComboBox<>(blocks);
	private JPanel subtop;

	public View(Model model,iControllerRecord controller ) {
		
	}

	public void setGUI() {
		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		exercise_nr.addActionListener(this);
		blocks_id.addActionListener(this);
		// top panel
		//top = new JPanel();
		
		label = new JLabel();
		
		subtop = new JPanel();
		subtop.setLayout(new BorderLayout());
		subtop.add(label, BorderLayout.CENTER);
		subtop.add(blocks_id,BorderLayout.WEST);
		subtop.add(exercise_nr, BorderLayout.EAST);
		// btnGroup
		btnGroup = new JPanel();
		btnGroup.setLayout(new FlowLayout());
		btnGroup.add(btnSave);
		btnGroup.add(btnNext);
		btnGroup.add(btnPrevious);
		center = new JPanel();
		center.setBorder(new EmptyBorder(8, 8, 8, 8));
		center.setLayout(new BorderLayout());
		questionField.setBorder(BorderFactory.createLineBorder(Color.black));
		center.add(questionField, BorderLayout.WEST);
		//center.add(top, BorderLayout.NORTH);
		center.add(subtop, BorderLayout.NORTH);
		center.add(btnGroup, BorderLayout.SOUTH);
		add(center);
		
	}

	public String getExcercise() {
		return exercise_nr.getSelectedItem().toString();
	}
	public int getBlockID(){
		return blocks_id.getSelectedIndex()+1;
	}

	
	public String getQuestion() {
		 
		return questionField.getText();
	}

	

	
}
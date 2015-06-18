package bluejexercisecheck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class BlueJExerciseCheckView extends JFrame {
	
	private JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");
	private JButton inputQuestion = new JButton("Invoer vragen");
	private JButton inputCorrectAnswer = new JButton("Invoer antwoorden");
	
	
	public BlueJExerciseCheckView(){
		
		JPanel panel = new JPanel();
		panel.add(inputQuestion);
		panel.add(inputCorrectAnswer);
		
		this.add(panel);
		this.setResizable(false);
		this.setLocation(new Point(800, 200));
		this.getContentPane().add(panel);
		this.pack();
		
		
		
	}
	
	

	

	public void addSaveActionListener(ActionListener listenForInputQuestion) {
		
		inputQuestion.addActionListener(listenForInputQuestion);

	}

	public void addNextActionListener(ActionListener listenForNextBtn) {
		
		inputCorrectAnswer.addActionListener(listenForNextBtn);

	}

	
	
	

}

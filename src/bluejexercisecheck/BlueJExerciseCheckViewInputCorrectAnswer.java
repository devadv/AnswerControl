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

public class BlueJExerciseCheckViewInputCorrectAnswer extends JFrame {
	private String[] listBlocks = BlueJExerciseCheckViewInputQuestion.blocks;
	private String[] listExercises = BlueJExerciseCheckViewInputQuestion.listExercises;

	
	private JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");
	private JComboBox<String> setBlock = new JComboBox<>(listBlocks);

	private JComboBox<String> exercise_nr = new JComboBox<>(listExercises);
	private JTextArea input_question = new JTextArea(6, 10);
	private JTextArea input_answer = new JTextArea(20, 15);
	private JScrollPane scrollPaneQuestion = new JScrollPane();
	private JScrollPane scrollPaneAnswer = new JScrollPane();
        private JLabel labelChapter = new JLabel("Blok");
	private JLabel labelExercise = new JLabel("Oefening:");
	private JLabel labelQuestion = new JLabel("Vraag:");
	private JLabel labelAnswer = new JLabel("Antwoord:");
	private JButton btnSave = new JButton("Save");
	private JButton btnNext = new JButton("Next");
	private JButton btnPrevious = new JButton("Previous");

	public BlueJExerciseCheckViewInputCorrectAnswer() {
		// TextArea settings
		input_question.setBounds(10, 10, 350, 550);
		input_question.setWrapStyleWord(true);
		input_question.setLineWrap(true);
		input_question.setBackground(new Color(219, 205, 197));
		input_answer.setBounds(10, 10, 350, 550);
		input_answer.setWrapStyleWord(true);
		input_answer.setLineWrap(true);
		input_answer.setBackground(new Color(219, 205, 197));

		// ScrollPane settings
		scrollPaneQuestion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneQuestion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneAnswer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneAnswer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneQuestion.setMaximumSize(new Dimension(200,50));
			
		//main panel - layout vertical
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		
		//title panel 		
		JPanel panelTitle = new JPanel();
		
		//top panel
		JPanel panelTop = new JPanel();
		
		//question panel
		JPanel panelQuestion = new JPanel();
		
		//answer panel
		JPanel panelAnswer = new JPanel();
		
		//bottom panel
		JPanel panelBottom = new JPanel();
		
		//add components to panels	
		panelTitle.add(title_course);
                panelTop.add(labelChapter);
		panelTop.add(setBlock);
                panelTop.add(labelExercise);
		panelTop.add(exercise_nr);
		panelQuestion.add(labelQuestion);
		panelQuestion.add(scrollPaneQuestion.add(input_question));
		panelAnswer.add(labelAnswer);
		panelAnswer.add(scrollPaneAnswer.add(input_answer));
		panelBottom.add(btnSave);
		panelBottom.add(btnNext);
		panelBottom.add(btnPrevious);
		
		//add to main panel
		panel.add(panelTitle);
		panel.add(panelTop);
		panel.add(panelQuestion);
		panel.add(panelAnswer);
		panel.add(panelBottom);

		this.setResizable(false);

		this.setLocation(new Point(800, 200));
		this.getContentPane().add(panel);
		this.pack();

	}
	

	public void addSaveActionListener(ActionListener listenForSaveBtn) {
		
		btnSave.addActionListener(listenForSaveBtn);
		
	}

	public void addNextActionListener(ActionListener listenForNextBtn) {
		
		btnNext.addActionListener(listenForNextBtn);

	}

	public void addPreviousActionListener(ActionListener listenForPreviousBtn) {
		
		btnPrevious.addActionListener(listenForPreviousBtn);

	}

	
	
	

}

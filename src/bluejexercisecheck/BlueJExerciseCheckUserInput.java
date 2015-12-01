package bluejexercisecheck;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;



/**
 * Gui for input of user answers
 * @author david
 */
@SuppressWarnings("serial")
public class BlueJExerciseCheckUserInput extends BlueJExerciseCheckView
{
	
	private JPanel panelQuestion;
	private JPanel panelAnswer;
	
	private JTextArea textAreaQuestion;
	private JTextArea textAreaAnswer;
	
	private JScrollPane jspQuestion;
	private JScrollPane jspAnswer;
	
	private JLabel labelAnswer;
	
	
	public BlueJExerciseCheckUserInput()
	{
		super();
		setGUI();
	}
	
	@Override
	public void setGUI()
	{
		this.setTitle("BlueJ Exercise Check  -  User input");
		panelQuestion = new JPanel();
		panelAnswer = new JPanel();
		
		textAreaQuestion = new JTextArea(8 ,30);
		textAreaQuestion.setEditable(false);
		textAreaQuestion.setLineWrap(true);
		textAreaQuestion.setWrapStyleWord(true);
		textAreaQuestion.setText("Vraag weergave");

		textAreaAnswer = new JTextArea(12, 30);
		textAreaAnswer.setLineWrap(true);
		textAreaAnswer.setWrapStyleWord(true);
		textAreaAnswer.setText("Antwoord invoeren");
				
		
		labelAnswer = new JLabel("Antwoord:");
		

		jspQuestion = new JScrollPane(textAreaQuestion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspAnswer = new JScrollPane(textAreaAnswer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelQuestion.add(jspQuestion);
		panelQuestion.add(labelQuestion);
		
		
		panelAnswer.add(labelAnswer);
		panelAnswer.add(jspAnswer);		
		
	
		//panel.add(labelQuestion);
		
		
		

		panel.add(panelQuestion);
		
		panel.add(panelAnswer);
		
		panel.add(panelBottom);


	}
	


	/**
	 * Main method for testing 
	 */
	public static void main(String[] args)
	{
		System.out.println("Show the userInput window for testing...\n");
		BlueJExerciseCheckUserInput userInput = new BlueJExerciseCheckUserInput();
		userInput.setSize(400, 600);
		userInput.setDefaultCloseOperation(EXIT_ON_CLOSE);
		userInput.setVisible(true);
		
		GuiUtils.printHierarchy(userInput);
	}

}

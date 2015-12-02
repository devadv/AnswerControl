package bluejexercisecheck;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BlueJExerciseCheckMainView extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2015110200010001L;

	protected JButton inputQuestion = new JButton("Invoer vragen");
    protected JButton inputCorrectAnswer = new JButton("Invoer antwoorden");
    protected JButton userInputButton = new JButton("User input");
	
	public BlueJExerciseCheckMainView(){
		super();
		JPanel panel = new JPanel();
		panel.add(inputQuestion);
		panel.add(inputCorrectAnswer);
		panel.add(userInputButton);
		
		this.add(panel);
		this.setResizable(false);
		this.setLocation(new Point(800, 200));
		this.getContentPane().add(panel);
		this.pack();
	}
		
	public void addInputQuestionListener(ActionListener listenForInputQuestion) 
    {
		inputQuestion.addActionListener(listenForInputQuestion);
	}

	public void addInputCorrectAnswerListener(ActionListener listenForNextBtn) 
    {
		inputCorrectAnswer.addActionListener(listenForNextBtn);

	}
	
	public void addUserInputButtonListener(ActionListener userInputButtonListener)
	{
		userInputButton.addActionListener(userInputButtonListener);
	}
}

package bluejexercisecheck;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

public class BlueJExerciseCheckController {

	private final BlueJExerciseCheckMainView theView;
	private final BlueJExerciseCheckModel theModel;
	

	public BlueJExerciseCheckController(BlueJExerciseCheckMainView theView, BlueJExerciseCheckModel theModel)
			throws SQLException {

		this.theView = theView;
		this.theModel = theModel;
		// add listeners
		this.theView.addInputQuestionListener(new InputQuestionListener());
		this.theView.addInputCorrectAnswerListener(new InputAnswerListener());
		// get values out of db and set in the view
		this.theModel.setConnectionDatabase();
		
	}

	// Save button Listener
	class InputQuestionListener implements ActionListener {
		
		
		private BlueJExerciseCheckViewInputQuestion theViewInputQuestion;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			theViewInputQuestion = new BlueJExerciseCheckViewInputQuestion();
			theViewInputQuestion.addSaveActionListener(new SaveBtnListener());
			theViewInputQuestion.addNextActionListener(new NextBtnListener());
			theViewInputQuestion.addPreviousActionListener(new PreviousBtnListener());
                        theViewInputQuestion.setBlocks(theModel.getBlockList());
			theViewInputQuestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			theViewInputQuestion.setSize(400, 600);
			theViewInputQuestion.setVisible(true);
                        theView.dispose();
			System.out.println("Question Clicked!");
		}	 
			// Save button Listener
				class SaveBtnListener implements ActionListener {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.out.println(theViewInputQuestion.getSelectedBlock());		
						System.out.println(theViewInputQuestion.getSelectedExercise());
                                                System.out.println(theViewInputQuestion.getSelectedBlockIndex());
                                                System.out.println(theViewInputQuestion.getSelectedExerciseIndex());
						System.out.println(theViewInputQuestion.getQuestion());
						System.out.println("save Clicked!");

					}

				}
				class NextBtnListener implements ActionListener {

					@Override
					public void actionPerformed(ActionEvent e) {
						theViewInputQuestion.setNextExercise();
                                                						System.out.println("next  Clicked!");

					}
					
				}
				class PreviousBtnListener implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
                                                theViewInputQuestion.setPreviousExercise();
						System.out.println("previous Clicked!");

					}
					
				}

		}

	

	// Next button Listener
	class InputAnswerListener implements ActionListener {
            private BlueJExerciseCheckViewInputCorrectAnswer theViewInputCorrectAnswer;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			theViewInputCorrectAnswer = new BlueJExerciseCheckViewInputCorrectAnswer();
                        theViewInputCorrectAnswer.addSaveActionListener(new SaveBtnListener());
			theViewInputCorrectAnswer.addNextActionListener(new NextBtnListener());
			theViewInputCorrectAnswer.addPreviousActionListener(new PreviousBtnListener());
                        
			theViewInputCorrectAnswer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			theViewInputCorrectAnswer.setSize(400, 600);
			theViewInputCorrectAnswer.setVisible(true);
                        theView.dispose();
			System.out.println("Correct Answer Clicked!");
                }       
                        // Save button Listener
				class SaveBtnListener implements ActionListener {

					@Override
					public void actionPerformed(ActionEvent arg0) {
                                                System.out.println(theViewInputCorrectAnswer.getSelectedBlock());		
						System.out.println(theViewInputCorrectAnswer.getSelectedExercise());
                                                System.out.println(theViewInputCorrectAnswer.getAnswer());
						System.out.println("save Clicked!");

					}

				}
				class NextBtnListener implements ActionListener {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						System.out.println("next  Clicked!");

					}
					
				}
				class PreviousBtnListener implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("previous Clicked!");

					}
					
				}


		}

	
}
	


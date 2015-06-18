package bluejexercisecheck;

import bluejexercisecheck.BlueJExerciseCheckViewInputQuestion;
import bluejexercisecheck.BlueJExerciseCheckView;
import bluejexercisecheck.BlueJExerciseCheckModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

public class BlueJExerciseCheckController {

	private BlueJExerciseCheckView theView;
	private BlueJExerciseCheckModel theModel;
	

	public BlueJExerciseCheckController(BlueJExerciseCheckView theView, BlueJExerciseCheckModel theModel)
			throws SQLException {

		this.theView = theView;
		this.theModel = theModel;
		// add listeners
		this.theView.addSaveActionListener(new InputQuestionListener());
		this.theView.addNextActionListener(new InputAnswerListener());
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
			theViewInputQuestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			theViewInputQuestion.setSize(400, 600);
			theViewInputQuestion.setVisible(true);
			System.out.println("Question Clicked!");
		}	 
			// Next button Listener
				class SaveBtnListener implements ActionListener {

					@Override
					public void actionPerformed(ActionEvent arg0) {
								
						System.out.println(theViewInputQuestion.getExcercise_nr());
						System.out.println(theViewInputQuestion.getQuestion());
						
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

	// Next button Listener
	class InputAnswerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
					

			System.out.println("Correct Answer Clicked!");

		}

	}

	


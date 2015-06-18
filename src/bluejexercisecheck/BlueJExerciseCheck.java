package bluejexercisecheck;

import bluejexercisecheck.BlueJExerciseCheckView;
import bluejexercisecheck.BlueJExerciseCheckModel;
import java.sql.SQLException;

import javax.swing.JFrame;


public class BlueJExerciseCheck {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		BlueJExerciseCheckView theView = new BlueJExerciseCheckView();
		BlueJExerciseCheckModel theModel = new BlueJExerciseCheckModel();
		
		BlueJExerciseCheckController theController = new BlueJExerciseCheckController(theView, theModel);
                theView.setTitle("BlueJ Input Questions and Answers");
      
		theView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theView.setSize(400, 600);
		theView.setVisible(true);
                

	}

}

package bluejexercisecheck;

import bluejexercisecheck.BlueJExerciseCheckView;
import bluejexercisecheck.BlueJExerciseCheckModel;
import java.sql.SQLException;

import javax.swing.JFrame;


public class BlueJExerciseCheck {

	
	public static void main(String[] args) throws SQLException {
		
		BlueJExerciseCheckMainView theView = new BlueJExerciseCheckMainView();
		BlueJExerciseCheckModel theModel = new BlueJExerciseCheckModel();
		
		BlueJExerciseCheckController theController = new BlueJExerciseCheckController(theView, theModel);
                theView.setTitle("BlueJ Input Questions and Answers");
      
		theView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theView.setSize(400, 600);
		theView.setVisible(true);
                

	}

}

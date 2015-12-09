package bluejexercisecheck;

import java.sql.SQLException;


public class BlueJExerciseCheck 
{
	public static void main(String[] args) throws SQLException
    {        
		BlueJExerciseCheckMainView theView = new BlueJExerciseCheckMainView();
		BlueJExerciseCheckModel theModel = new BlueJExerciseCheckModel();  // the Model
		//BlueJExerciseCheckModel theModel = new BjecTestModel();  // testModel
		
		BlueJExerciseCheckController theController = new BlueJExerciseCheckController( theView, theModel );
        theView.setTitle("BlueJ Input Questions and Answers");
                
		theView.setSize(400, 600);
		theView.setVisible(true);
    }
}


/*

 */
package testbluej;

import javax.swing.JFrame;

public class Start 
{
    public static void main(String[] args) 
    {
        boolean fillingDatabase = false;
        
        if( fillingDatabase )
        {
            GUI_InputAnswer inputAnswers = new GUI_InputAnswer();
            inputAnswers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inputAnswers.setSize(600, 600);
            inputAnswers.setLocationRelativeTo( null );
            inputAnswers.setResizable(false);
            inputAnswers.setVisible(true);
        }
        else
        {
            GUI_InputQuestionsSolutions inputQuestionsSolutions = new GUI_InputQuestionsSolutions();
            //inputQuestions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inputQuestionsSolutions.setSize(600, 700);
            inputQuestionsSolutions.setLocationRelativeTo( null );
            inputQuestionsSolutions.setResizable(false);
            inputQuestionsSolutions.setVisible(true);
            
        }// end if else
        
    }// end main
    
}// end class Start

package bluejexercisecheck;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class BlueJExerciseCheckViewInputQuestion extends BlueJExerciseCheckView {
     private JTextArea input_question = new JTextArea(20,35);

    public BlueJExerciseCheckViewInputQuestion() {
        super();
        setGUI();
    }

    @Override
    public void setGUI() {
        // TextArea settings
        input_question.setWrapStyleWord(true);
        input_question.setLineWrap(true);
        input_question.setBackground(new Color(219, 205, 197));
        box.add(new JScrollPane(input_question));
        panelQuestion.add(box);
        
        
        this.setTitle("Invoer Vragen");
       

    }
    public String getQuestion(){
        return input_question.getText();
    }
    
   
}

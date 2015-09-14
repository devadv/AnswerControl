package bluejexercisecheck;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BlueJExerciseCheckViewInputQuestion extends BlueJExerciseCheckView {

    private JTextArea input_question = new JTextArea(20, 35);
    private String oldtext;

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

    public String getQuestion() {
        return input_question.getText();
    }

    public void setQuestion(String question) {
        input_question.setText(question);
        oldtext = input_question.getText();
    }

    public void clearQuestionTextArea() {
        input_question.setText("");
    }
    
    public boolean questionChanged(){
        System.out.println("Oldtext: " +oldtext);
        if(getQuestion().equals(oldtext)){
            return false;
        }else{
            return true;
        }
        
    }
       
        
    

}

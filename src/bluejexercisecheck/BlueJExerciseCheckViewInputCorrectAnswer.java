package bluejexercisecheck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class BlueJExerciseCheckViewInputCorrectAnswer extends BlueJExerciseCheckView {

    private JTextArea display_question = new JTextArea(5, 35);
    private JTextArea input_answer = new JTextArea(15, 35);

    private JLabel labelAnswer = new JLabel("Antwoord:");
    private JPanel panelAnswer;

    public BlueJExerciseCheckViewInputCorrectAnswer() {
        super();
        setGUI();
    }

    @Override
    public void setGUI() {

        //TextArea settings
        display_question.setEditable(false);
        
        input_answer.setBounds(10, 10, 350, 550);
        input_answer.setWrapStyleWord(true);
        input_answer.setLineWrap(true);
        input_answer.setBackground(new Color(219, 205, 197));
        
        //answer panel
        panelAnswer = new JPanel();
        panelAnswer.add(labelAnswer);
        panelAnswer.add(new JScrollPane(input_answer));
        //add components to panels	
       
        box.add(new JScrollPane(display_question));
        panelQuestion.add(box);
       
        //add to main panel
        panel.add(panelAnswer);
        panel.add(panelBottom);
       
        
        this.setTitle("Invoer Antwoord");
    }
    
    public String getAnswer(){
        return input_answer.getText();
    }

}


package mvc.view;

import java.awt.Color;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewUserAnswerCorrectAnswer extends View
{
    private JTextArea userAnswerField;
    private iCRUD controller;
    String exerciseNr = "";
    
    public ViewUserAnswerCorrectAnswer(Model model, iCRUD controller) 
    {
        super(model, controller);
        this.model = model;
        this.controller = controller;
        
        setGUI();
        questionField.setText(model.retrieveQuestion(getExcercise()));
        userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
        
    }
    
    public void setGUI()
    {
        super.setGUI();
        
        questionField.setRows(10);
        questionField.setColumns(38);
        questionField.setFont(textAreaFont);
        questionField.setEditable(false);
        
        
        userAnswerField = new JTextArea(23, 38);
        userAnswerField.setFont(textAreaFont);
        userAnswerField.setLineWrap(true);
        userAnswerField.setBackground(new Color(219, 205, 197));
        
        JScrollPane jspUserAnswer = new JScrollPane(userAnswerField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JPanel panelAnswer = new JPanel();
        panelAnswer.add(jspUserAnswer);
               
        
        
                
        panel.add(panelQuestion);
        panel.add(panelAnswer);
        panel.add(panelBottom);
        
        this.setTitle("Input user answer.");
        this.setSize(1200, 800);
        this.setLocation(800, 200);
        this.getContentPane().add(panel);
        this.setVisible(true);
        
    }
    
    
    @Override
    public void btnNext()
    {
        if(exercise_id.getSelectedIndex() + 1 < exercise_id.getItemCount())
        {
            btnPrevious.setEnabled(true);
            exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() + 1);
            userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
        }
        else
        {
            btnPrevious.setEnabled(false);
        } 
        
        setEnable_btnCheckAllAnswer();
    }
    
    public void btnPrevious()
    {
        if(exercise_id.getSelectedIndex() - 1 >= 0)
        {
            btnNext.setEnabled(true);
            exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() - 1);
            exerciseNr = getExcercise();
            userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
        }
        else if(exercise_id.getSelectedIndex() == 0)
        {
            btnPrevious.setEnabled(false);
        }
        setEnable_btnCheckAllAnswer();
    }

    
    private void setEnable_btnCheckAllAnswer()
    {
        if(model.allAnswersFilled(getBlockName(), controller.getUserName()) <= exercise_id.getItemCount())
        {
            btnCheckAnswer.setEnabled(false);
        }
        else
        {
            btnCheckAnswer.setEnabled(true);
        }
    }
    
    public String getUserAnswer()
    {
        return userAnswerField.getText();
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        questionField.setText(model.retrieveQuestion(getExcercise()));
        userAnswerField.getText();
    }

    
    
    @Override
    public void exerciseId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnSave() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void blocksId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnCheckAnswer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}// end class ViewInputAnswerUser

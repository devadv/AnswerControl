
package mvc.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputAnswerUser extends View
{
    private JTextArea userAnswerField;
    private iCRUD controller;
    private String exerciseNr = "";
    
    public ViewInputAnswerUser(Model model, iCRUD controller) 
    {
        super(model, controller);
        this.model = model;
        this.controller = controller;
        
        exerciseNr = getExcercise();
        setGUI();
        questionField.setText(model.retrieveQuestion(getExcercise()));
        userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
        btnCheckAllAnswer_setEnabled();
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
        
        exercise_id.setModel(new DefaultComboBoxModel<>(model.getExerciseList(getBlockID())));   
        exerciseNr = getExcercise();
        
                
        panel.add(panelQuestion);
        panel.add(panelAnswer);
        panel.add(panelBottom);
        
        this.addWindowListener(new windowClosingAdapter());
        this.setTitle("Input user answer.");
        this.setSize(600, 800);
        this.setLocation(800, 200);
        this.getContentPane().add(panel);
        this.setVisible(true);
        
    }
    
    
    @Override
    public void exerciseId()
    {  
        if(isUserAnswerChanged(exerciseNr))
        {
            messageUser();
            userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
            exerciseNr = getExcercise();
        }
        else
        {
            if(exercise_id.getSelectedIndex() == 0)
            {
                btnPrevious.setEnabled(false);
                btnNext.setEnabled(true);
            }
            else if(exercise_id.getSelectedIndex() == exercise_id.getItemCount() - 1)
            {
                btnNext.setEnabled(false);
                btnPrevious.setEnabled(true);
            }
            else
            {
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(true);
            }
            questionField.setText(model.retrieveQuestion(getExcercise()));
            userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));

            exerciseNr = getExcercise();
        }
        
        if(exercise_id.getSelectedIndex() == 0)
        {
            btnPrevious.setEnabled(false);
        }
        
        btnCheckAllAnswer_setEnabled();
    }
    
    public void btnSave()
    { 
        if(!model.userAnswerExist(exerciseNr, controller.getUserName()))// user answer doesn't exist
        {
            model.createUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
        }
        else
        {
            model.updateUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
        }
        
        btnCheckAllAnswer_setEnabled();
    }
    
    boolean message = true;
    @Override
    public void btnNext()
    {
        if(isUserAnswerChanged(exerciseNr))
        {
            messageUser();
            message = false;
        }

        if(exercise_id.getSelectedIndex() + 1 < exercise_id.getItemCount())
        {
            btnPrevious.setEnabled(true);
            exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() + 1);
            userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
        }
        else if(exercise_id.getSelectedIndex() == exercise_id.getItemCount())
        {
            btnNext.setEnabled(false);
        }
        else
        {
            btnPrevious.setEnabled(false);
        } 
        
        btnCheckAllAnswer_setEnabled();
        message = true;
    }
    
    public void btnPrevious()
    { 
        if(isUserAnswerChanged(exerciseNr))
        {
            messageUser();
            message = false;
        }

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
        btnCheckAllAnswer_setEnabled();
        message = true;
    }
    
    @Override
    public void blocksId()
    {
        exercise_id.setModel(new DefaultComboBoxModel<>(model.getExerciseList(getBlockID())));
        exerciseNr = getExcercise();
        userAnswerField.setText(model.retrieveAnswerUser(exerciseNr, controller.getUserName()));
        questionField.setText(model.retrieveQuestion(getExcercise()));
        btnCheckAllAnswer_setEnabled();
    }
    
    @Override
    public void btnCheckAnswer()
    {
        if(model.blockNameExist(getBlockName(), controller.getUserName()));
        {
            JOptionPane.showMessageDialog(null, "All answers filled.");
            
        }
    }
    
    
    private void messageUser()
    {
        if(message)
        {
            int dialogReslult = JOptionPane.showConfirmDialog(null, 
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                
            if(dialogReslult == 0)// yes button
            {
                model.updateUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
            }
        }
                
    }// end method messageUser
    
    private void btnCheckAllAnswer_setEnabled()
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
    
    public boolean isUserAnswerChanged(String oldExerciseNr)
    {
        String currentAnswerUser = getUserAnswer();
        String oldText = model.retrieveAnswerUser(oldExerciseNr, controller.getUserName());
        
        if(oldText == null)
        {
            oldText = "";
        }
        
        if(currentAnswerUser.equals(oldText))
        {
            return false;
        }
        
        return true;
    }
    
    public class windowClosingAdapter extends WindowAdapter
    {           
        @Override
        public void windowClosing(WindowEvent we)
        {
            if(isUserAnswerChanged(exerciseNr))
            {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                
                if(dialogResult == 0)// yes button clicked
                {
                    
                    model.updateUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
                    System.exit(0);
                }
                else
                {
                    System.exit(0);
                }
            }
            else
            {
                System.exit(0);
            }
        }
    }// end class windowClosingAdaptor
    
}// end class ViewInputAnswerUser

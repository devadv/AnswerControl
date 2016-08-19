
package mvc.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.DefaultComboBoxModel;
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
    private String oldExerciseNr = "";
    
    public ViewInputAnswerUser(Model model, iCRUD controller) 
    {
        super(model, controller);
        this.model = model;
        this.controller = controller;
        
        oldExerciseNr = getExcercise();
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
        
        userAnswerField = new JTextArea(23, 48);
        userAnswerField.setLineWrap(true);
        userAnswerField.setBackground(new Color(219, 205, 197));
        
        JScrollPane jspUserAnswer = new JScrollPane(userAnswerField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JPanel panelAnswer = new JPanel();
        panelAnswer.add(jspUserAnswer);
        exercise_id.setModel(new DefaultComboBoxModel<>(model.getExerciseList(getBlockID())));   
        
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
    public void actionPerformed(ActionEvent event) 
    {
        if(event.getSource() == exercise_id)
        {
            if(isUserAnswerChanged(oldExerciseNr))
            {
                messageUserAnswer();
                userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
                oldExerciseNr = getExcercise();
            }
            else
            {
                if(exercise_id.getSelectedIndex() == 0)
                {
                    btnPrevious.setEnabled(false);
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
                
                oldExerciseNr = getExcercise();
            }
            
        }
        else if(event.getSource() == btnSave)
        {
            if(!model.userAnswerExist(oldExerciseNr, controller.getUserName()))// user answer doesn't exist
            {
                model.createUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
            }
            else
            {
                model.updateUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
            }
        }
        else if(event.getSource() == btnNext)
        {
            if(isUserAnswerChanged(oldExerciseNr))
            {
                messageUserAnswer();
            }
            
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
            
        }
        else if(event.getSource() == btnPrevious)
        {
            if(isUserAnswerChanged(oldExerciseNr))
            {
                messageUserAnswer();
            }
            
            if(exercise_id.getSelectedIndex() - 1 >= 0)
            {
                btnNext.setEnabled(true);
                exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() - 1);
                userAnswerField.setText(model.retrieveAnswerUser(getExcercise(), controller.getUserName()));
            }
            else
            {
                btnNext.setEnabled(false);
            }
            
        }
        else if(event.getSource() == blocks_id)
        {            
            exercise_id.setModel(new DefaultComboBoxModel<>(model.getExerciseList(getBlockID())));
        }
		
        
    }// end mothod actionPerformed
    
    public void messageUserAnswer()
    {
        int dialogReslult = JOptionPane.showConfirmDialog(null, 
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                
        if(dialogReslult == 0)// yes button
        {
            model.updateUserAnswer(userAnswerField.getText(), getTitle(), controller.getUserName());
        }
        
    }
    
    public String getUserAnswer()
    {
        return userAnswerField.getText();
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        userAnswerField.getText();
    }
    
    public boolean isUserAnswerChanged(String oldExercise)
    {
        String currentAnswerUser = getUserAnswer();
        String oldText = model.retrieveAnswerUser(oldExercise, controller.getUserName());
        
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
            if(isUserAnswerChanged(oldExerciseNr))
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

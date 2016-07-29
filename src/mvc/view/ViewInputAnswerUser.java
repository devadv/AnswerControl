
package mvc.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
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
    
    public ViewInputAnswerUser(Model model, iCRUD controller) 
    {
        super(model, controller);
        this.model = model;
        this.controller = controller;
        setGUI();
        questionField.setText(model.retrieveQuestion(getExcercise()));
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
        }
        else if(event.getSource() == btnSave)
        {
            model.updateUserAnswer(userAnswerField.getText(), getExcercise(), controller.getUserName());
        }
        else if(event.getSource() == btnNext)
        {
            
        }
        
    }// end mothod actionPerformed
    
    public String getUserAnswer()
    {
        return userAnswerField.getText();
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        
    }
    
    public boolean isUserAnswerChanged()
    {
        String currentAnswerUser = getUserAnswer();
        String oldText = model.retrieveAnswerUser(getExcercise(), controller.getUserName());
        
        
        return false;
    }
    
    public class windowClosingAdapter extends WindowAdapter
    {           
        @Override
        public void windowClosing(WindowEvent we)
        {         
            boolean isAnswerchanged = false;
            if(isAnswerchanged)
            {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0)// yes button clicked
                {
                    
                    //model.updateAnswer(String.valueOf(exercise_id.getSelectedItem()), getAnswer(), 0);
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

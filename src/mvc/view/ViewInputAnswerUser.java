
package mvc.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputAnswerUser extends View
{
    private JTextArea  userAnswerField;

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
        questionField.setEnabled(false);
        questionField.setColumns(38);
        questionField.setRows(10);
        
        userAnswerField = new JTextArea(20, 30);
        userAnswerField.setLineWrap(true);
        userAnswerField.setBackground(new Color(219, 205, 197));
        JScrollPane jspUserAnswer = new JScrollPane(userAnswerField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(panelQuestion);
        panel.add(jspUserAnswer);
        panel.add(panelBottom);
        
        this.addWindowListener(new windowClosingAdapter());
        this.setTitle("Input user answer.");
        this.setSize(600, 800);
        this.setLocation(1000, 200);
        this.getContentPane().add(panel);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
    
    public String getUserAnswer()
    {
        return "";
    }
    
    public String getUserName()
    {
        return "";
    }

    @Override
    public void update(Observable o, Object arg) 
    {
        
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

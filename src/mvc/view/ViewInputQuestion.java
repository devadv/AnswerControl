package mvc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.controller.ControllerInputAnswer;
import mvc.controller.ControllerInputQuestion;
import mvc.controller.iControllerAnswerQuestion;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewInputQuestion extends View 
{

	public ViewInputQuestion(Model model, iCRUD controller) {
		super(model, controller);
		setGUI();
		questionField.setText(model.retrieveQuestion(getExcercise()));
        
		
	}
	@Override
	public void setGUI() {
		super.setGUI();
		
		panel.add(panelQuestion);
        panel.add(panelBottom);
                
        this.addWindowListener( new WindowClosingAdapter1());
        this.pack();
		this.setTitle("Invoer vragen");
        this.setSize(600, 600);
        
		this.setLocation(200, 100);
		this.getContentPane().add(panel);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) 
    {
		if (event.getSource() == exercise_id) 
        {
            if(exercise_id.getSelectedIndex() == 0)
            {
                btnPrevious.setEnabled(false);
            }
            else if(exercise_id.getSelectedIndex() == exercise_id.getItemCount()-1)
            {
                btnNext.setEnabled(false);
                btnPrevious.setEnabled(true);
            }
            else
            {
                btnPrevious.setEnabled(true);
            }
			questionField.setText(model.retrieveQuestion(getExcercise()));
		}
        else if (event.getSource() == btnSave) 
        {
			if (!(model.questionExist(getExcercise()))) 
            {
				controller.create();
			} 
            else 
            {
				model.updateQuestion(getExcercise(), getQuestion(), getBlockID());
			}

		} 
        else if (event.getSource() == btnNext)
        {
            if(isQuestionChanged())
            {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0)// yes button clicked
                {
                    model.updateQuestion(String.valueOf(exercise_id.getSelectedItem()), getQuestion(), 0);
                }
            }
            
            if(exercise_id.getSelectedIndex() + 1 < exercise_id.getItemCount())
            {
                btnPrevious.setEnabled(true);
                exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() + 1);
                questionField.setText(model.retrieveQuestion(getExcercise()));
            }
            else
            {
                btnNext.setEnabled(false);
            }
            
            
		}
        else if (event.getSource() == btnPrevious) 
        {
            if(isQuestionChanged())
            {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0)// yes button clicked
                {
                    model.updateQuestion(String.valueOf(exercise_id.getSelectedItem()), getQuestion(), 0);
                }
            }
            
            if(exercise_id.getSelectedIndex() - 1 >= 0)  
            {
                btnPrevious.setEnabled(true);
                exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() - 1);
                questionField.setText(model.retrieveQuestion(getExcercise()));
            }
            else
            {
                btnPrevious.setEnabled(false);
            }
            
            if(exercise_id.getSelectedIndex() + 1 < exercise_id.getItemCount())
            {
                btnNext.setEnabled(true);
            }
	    }
    }// end method actionPerformed
    
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("model updated");
		questionField.setText(model.retrieveQuestion(getExcercise()));
	}
    
    public String getQuestion()
    {
        return questionField.getText();
    }
    
    public boolean isQuestionChanged()
    {
        String currentText = getQuestion();
        String oldtext = model.retrieveQuestion(String.valueOf(exercise_id.getSelectedItem()));
        
        if(oldtext == null)
        {
            oldtext = "";
        }
        
        if(currentText.equals(oldtext))
        {
            return false;
        }
        
        return true;
    }  
    
    public class WindowClosingAdapter1 extends WindowAdapter
    {           
        @Override
        public void windowClosing(WindowEvent we)
        {         
            if(isQuestionChanged())
            {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0)// yes button clicked
                {
                    System.out.println("Yes option");
                    model.updateQuestion(String.valueOf(exercise_id.getSelectedItem()), getQuestion(), 0);
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

}// end class ViewInputQuestion

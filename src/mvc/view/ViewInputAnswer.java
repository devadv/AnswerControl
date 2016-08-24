package mvc.view;


import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.controller.iCRUD;
import mvc.model.Model;


public class ViewInputAnswer extends View
{
	
	//private iCRUD controller;
	private Model model;
	private JTextArea answerField ;
    private String oldExerciseNr;

	public ViewInputAnswer(Model model, iCRUD controller) 
    {
		super(model, controller);
		this.model = model;
		this.controller = controller;
		setGUI();
		questionField.setText(model.retrieveQuestion(getExcercise()));
		answerField.setText(model.retrieveAnswer(getExcercise()) );// change when model is updated
        
	}

	@Override
	public void setGUI()
    {
		super.setGUI();
		answerField = new JTextArea(25, 38);
		answerField.setLineWrap(true);
		answerField.setWrapStyleWord(true);
		answerField.setFont(textAreaFont);
		questionField.setEditable(false);
		questionField.setColumns(38);
		questionField.setRows(5);
		
		JScrollPane jspAnswer = new JScrollPane(answerField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
		panel.add(panelQuestion);
		JPanel panelAnswer = new JPanel();
		panelAnswer.add(jspAnswer);
        
        oldExerciseNr = getExcercise();
        System.out.println("");
        exercise_id.setModel(new DefaultComboBoxModel<>(model.getExerciseList(getBlockID()))); 
        
		panel.add(panelAnswer);
		panel.add(panelBottom);
		this.pack();
                
		this.addWindowListener(new windowClosingAdapter());
		this.setTitle("Invoer Antwoorden");
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
            if(isAnswerchanged(oldExerciseNr))
            {
                messageUserAnswer();
                answerField.setText(model.retrieveAnswer(oldExerciseNr));
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
                answerField.setText(model.retrieveAnswer(oldExerciseNr));
                
                oldExerciseNr = getExcercise();
            }
            
        }
        else if(event.getSource() == btnSave)
        {
            if(!model.answerExist(oldExerciseNr))// answer doesn't exist
            {
                model.updateAnswer(oldExerciseNr, answerField.getText(), getBlockID());
            }
            else
            {
                model.updateAnswer(oldExerciseNr, answerField.getText(), 0);
            }
        }
        else if(event.getSource() == btnNext)
        {
            if(isAnswerchanged(oldExerciseNr))
            {
                messageUserAnswer();
            }
            
            if(exercise_id.getSelectedIndex() + 1 < exercise_id.getItemCount())
            {
                btnPrevious.setEnabled(true);
                exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() + 1);
                answerField.setText(model.retrieveAnswer(oldExerciseNr));
            }
            else
            {
                btnPrevious.setEnabled(false);
            } 
            
            
            
        }
        else if(event.getSource() == btnPrevious)
        {
            if(isAnswerchanged(oldExerciseNr))
            {
                messageUserAnswer();
            }
            
            if(exercise_id.getSelectedIndex() - 1 >= 0)
            {
                btnNext.setEnabled(true);
                exercise_id.setSelectedIndex(exercise_id.getSelectedIndex() - 1);
                answerField.setText(model.retrieveAnswer(oldExerciseNr));
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
            model.updateAnswer(oldExerciseNr, answerField.getText(), 0);
        }
        
    }
	
	@Override
	public void update(Observable o, Object arg)
    {
		System.out.println("model updated in Answer");
		questionField.setText(model.retrieveQuestion(getExcercise()));
		
		
	}
	public String getAnswer() 
    {
		return answerField.getText();
	}
    
    public boolean isAnswerchanged(String oldExerciseNr)
    {
        String currentText = getAnswer();
        String oldtext = model.retrieveAnswer(oldExerciseNr);
        
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

    public class windowClosingAdapter extends WindowAdapter
    {           
        @Override
        public void windowClosing(WindowEvent we)
        {         
            if(isAnswerchanged(oldExerciseNr))
            {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Gegevens zijn gewijzigd, opslaan?", "Message", JOptionPane.YES_NO_OPTION);
                if(dialogResult == 0)// yes button clicked
                {
                    System.out.println("Yes option");
                    System.out.println("exercise_id.getSelectedItem()" + exercise_id.getSelectedItem());
                    System.out.println("getAnswer()" + getAnswer());
                    model.updateAnswer(String.valueOf(exercise_id.getSelectedItem()), getAnswer(), 0);
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

}

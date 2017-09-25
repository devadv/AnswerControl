package mvc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import mvc.controller.iCRUD;
import mvc.model.Model;

public abstract class View extends JFrame implements iView, Observer
{
	ListExercises list = new ListExercises();
	private String[] listExercises  = list.getListExercises();

	protected JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");

	protected JButton btnSave = new JButton("Save");
	protected JButton btnNext = new JButton("Next");
	protected JButton btnPrevious = new JButton("Previous");
	protected JLabel labelExercise = new JLabel("Oefening:");
	protected JComboBox<String> exercise_id;
    protected JComboBox<String> blocks_id;

	protected JLabel label = new JLabel();
	protected JLabel labelBlock = new JLabel("Blok");

	protected iCRUD controller;
	protected Model model;


	protected JPanel panel;
	protected JPanel panelTitle;
	protected JPanel panelTop;
	protected JPanel panelBottom;
	protected Font textAreaFont;
	protected JTextArea questionField = new JTextArea(10, 20);
	protected JScrollPane jspQuestion;
	protected JPanel panelQuestion;

    protected JButton btnCheckAnswer;
    protected int blockId = 0;
    protected String exerciseNr = "";

	public View(Model model,iCRUD controller )
    {
		this.model = model;
		this.controller = controller;
		blocks_id = new JComboBox<>(model.getBlockList());
		//exercise_id = new JComboBox<>(listExercises);
		exercise_id = new JComboBox<>(model.getExerciseList(1));
	}

	public void setGUI()
    {
		textAreaFont = new Font("", Font.PLAIN, 13);

		btnSave.addActionListener(this);
		btnNext.addActionListener(this);
        btnCheckAnswer = new JButton("Check answers");
        btnCheckAnswer.setEnabled(false);

        if(exercise_id.getItemCount() == 1)
        {
            btnNext.setEnabled(false);
        }

		btnPrevious.addActionListener(this);
        btnPrevious.setEnabled(false);
		exercise_id.addActionListener(this);
		blocks_id.addActionListener(this);
        btnCheckAnswer.addActionListener(this);

		questionField.setRows(23);
		questionField.setColumns(38);
		questionField.setLineWrap(true);
		questionField.setWrapStyleWord(true);

		questionField.setFont(textAreaFont);
		questionField.setBackground(new Color(219, 205, 197));

		jspQuestion = new JScrollPane(questionField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		panelQuestion = new JPanel();
		panelQuestion.add(jspQuestion);

        //main panel - layout vertical
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        //title panel
        panelTitle = new JPanel();
        //top panel
        panelTop = new JPanel();
        //bottom panel
        panelBottom = new JPanel();

        panelTitle.add(title_course);
        panelTop.add(labelBlock);
        panelTop.add(blocks_id);
        panelTop.add(labelExercise);
        panelTop.add(exercise_id);
        panelBottom.add(btnSave);
        panelBottom.add(btnNext);
        panelBottom.add(btnPrevious);
        panelBottom.add(btnCheckAnswer);
        //add to main panel
        panel.add(panelTitle);
        panel.add(panelTop);
        panel.add(panelBottom);


	}

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == exercise_id)
        {
           exerciseId();
        }
        else if (event.getSource() == btnSave)
        {
            btnSave();
        }
        else if (event.getSource() == btnNext)
        {
           btnNext();
        }
        else if (event.getSource() == btnPrevious)
        {
           btnPrevious();
        }
        else if(event.getSource() == blocks_id)
        {
            blocksId();
        }
        else if(event.getSource() == btnCheckAnswer)
        {
            btnCheckAnswer();
        }
    }// end method actionPerformed

	public String getExcercise()
    {
		return exercise_id.getSelectedItem().toString();
	}


	public int getBlockID()
    {
		return blocks_id.getSelectedIndex() + 1;

	}

    public String getBlockName()
    {
        return model.getBlockName(getBlockID());
    }


}// end abstact class View

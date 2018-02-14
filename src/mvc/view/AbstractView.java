package mvc.view;

import mvc.model.Model;

import javax.swing.*;
import javax.swing.border.Border;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;

public abstract class AbstractView extends JFrame implements Observer {

    protected Model model;
    protected JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");
    protected JLabel exercise_label = new JLabel("Exercise:");
    protected JLabel block_label = new JLabel("Block:");
    protected JButton btnNext = new JButton("Next");
    protected JButton btnPrevious = new JButton("Previous");
    protected JComboBox<String> blockBox = new JComboBox<>();
    protected JComboBox<String> exerciseBox = new JComboBox<>();
    protected JPanel panel;
    protected JPanel panelTitleAndTop;
    protected JPanel panelTitle;
    protected JPanel panelBottom;
    protected JPanel panelCenter;
    protected Font textAreaFont;
    protected int lastBlockIndex;
	protected int lastExerciseIndex;
	protected JTextArea questionTextArea;
	private GridBagLayout layout;
	private GridBagConstraints constraints;

	JPanel panelTop = new JPanel(new GridBagLayout());

    /**
     * Constructor for abstract view class to initialize the model
     *
     * @param Model model
     */
    public AbstractView(Model model) {
        /** initialize model */
        this.model = model;
    }

    /**
     * set default
     */
    public void setComponents() {

        /** initialize panels */
        panel = new JPanel();
        panelTitle = new JPanel();
        panelBottom = new JPanel();
        panelTitleAndTop = new JPanel();
        panelCenter = new JPanel();

        /** set actionlisteners to buttons*/
        btnNext.addActionListener(new NextButtonListener());
        btnPrevious.addActionListener(new PreviousButtonListener());
        /** combobox blockBox */
        blockBox = new JComboBox<>(model.getBlockList());
        blockBox.addItemListener(new BlockBoxListener());

        /** set blockBox to the first index */
        blockBox.setSelectedIndex(0);
        /** combobox exerciseBox */
        exerciseBox = new JComboBox<>(model.getExerciseList(blockBox.getSelectedIndex() + 1));
        exerciseBox.addItemListener(new ExerciseBoxListener());

        /** layout managers */
        panel.setLayout(new BorderLayout(5, 5));//Borderlayout to main panel
        this.setResizable(false);
        panelBottom.setLayout(new GridLayout(1, 2, 5, 5));
        panelCenter.setLayout(new BorderLayout());

        panelTitleAndTop.setLayout(new BoxLayout(panelTitleAndTop, BoxLayout.Y_AXIS));

        /** add title to panel */
        panelTitle.add(title_course);

        /**  */
        panelTitleAndTop.add(panelTitle);
        /** make panel top  */
        setPanelTop();
        panel.add(panelTitleAndTop, BorderLayout.NORTH);

        panel.add(panelCenter);

        /** add buttons to bottom panel */
        panelBottom.add(btnPrevious);
        panelBottom.add(btnNext);
        /** add bottom panel to main panel*/
        panel.add(panelBottom, BorderLayout.SOUTH);
        /** add main panel to frame */
        add(panel);

    }

    /**
     * Make panel top which contains label, comboBox block, label, comboBox exercise and question textarea.
     */
    public void setPanelTop() {
    	layout = new GridBagLayout();
    	constraints = new GridBagConstraints();
    	panelTop.setLayout(layout);
    	textAreaFont = new Font("", Font.PLAIN, 15);
    	constraints.fill = GridBagConstraints.BOTH;
    	setQuestionTextArea(new JTextArea(10, 34));
    	getQuestionTextArea().setFont(textAreaFont);
    	getQuestionTextArea().setLineWrap(true);
    	getQuestionTextArea().setMargin(new Insets(5, 10, 0, 10));
    	getQuestionTextArea().setWrapStyleWord(true);
    	//questionTextArea.setEditable(false);
    	JScrollPane scrollPane = new JScrollPane(getQuestionTextArea());
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    	int column = 0;
    	addComponent(block_label, 0, column++, 1, 1, 11, 120, 0, 0);
    	addComponent(blockBox, 0, column++, 1, 1, 11, 10, 0, 0);
    	addComponent(exercise_label, 0, column++, 1, 1, 11, 30, 0, 0);
    	addComponent(exerciseBox, 0, column++, 1, 1, 11, 10, 0, 0);
    	addComponent(scrollPane, 1, 0, 100, 100, 20, 0, 0, 0);
    	panelTitleAndTop.add(panelTop);
	}

    /**
     *
     * @param component
     * @param row
     * @param column
     * @param width
     * @param height
     * @param top sets the space on the top of the component
     * @param left sets the space left side of the component
     * @param bottom sets the space on the bottom of the component
     * @param right sets the space rigth side of the component
     */
    private void addComponent(Component component, int row, int column, int width, int height,
    		int top, int left, int bottom, int right) {
    	constraints.gridx = column;
    	constraints.gridy = row;
    	constraints.gridwidth = width;
    	constraints.gridheight = height;
    	constraints.insets = new Insets(top, left, bottom, right);
    	layout.setConstraints(component, constraints);
    	panelTop.add(component);
	}

    /**
     * sets exerciseBox to the next item
     */
    public void btnNext() {

        if (exerciseBox.getSelectedIndex() < exerciseBox.getItemCount() - 1) {
            exerciseBox.setSelectedIndex(exerciseBox.getSelectedIndex() + 1);
        }


    }// end method btnNext

    /**
     * sets exerciseBox to the previous item
     */
    public void btnPrevious() {
        if (exerciseBox.getSelectedIndex() > 0) {
            exerciseBox.setSelectedIndex(exerciseBox.getSelectedIndex() - 1);
        }

    }// end method btnPrevious

    /**
     * Update view and set question textArea to current state
     */
    public void updateView() {
    	getQuestionTextArea().setText(model.retrieveQuestion(String.valueOf(exerciseBox.getSelectedItem())));
    	getQuestionTextArea().setCaretPosition(0);
    }// end method updateView

    public void setVisible() {

	}

    /**
     * inner class for actionListener on the next button
     */
    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnNext();
        }
    }// end class NextButtonListener

    /**
     * inner class for actionListener on the previous button
     */
    private class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnPrevious();
        }
    }

    /**
     * inner class for itemlistener on the combobox exerciseBox
     */
    private class ExerciseBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            updateView();
        }
    }

    /**
     * Returns exercise number.
     * @return
     * string value
     */
    public String getExerciseNr() {
		return (String) exerciseBox.getSelectedItem();
	}

    /**
     * Returns block number.
     * @return
     * int value
     */
    public String getBlockName() {
		return String.valueOf(blockBox.getSelectedItem());
	}

    /**
     * Returns user name from the operating system.
     * @return
     */
    public String getUserName() {
		return System.getProperty("user.name");
		//return "azego";
	}

    public int getBlockNumber() {
		return Integer.valueOf(blockBox.getSelectedIndex() + 1);
	}

    /**
     *
     * @return question.
     */
    public JTextArea getQuestionTextArea() {
		return questionTextArea;
	}

    /**
     *
     * @param questionTextArea
     */
	public void setQuestionTextArea(JTextArea questionTextArea) {
		this.questionTextArea = questionTextArea;
	}

	/**
     * inner class for itemlistener on the combobox blockBox
     */
    private class BlockBoxListener implements ItemListener {

		@Override
        public void itemStateChanged(ItemEvent e) {
        	lastBlockIndex = blockBox.getSelectedIndex();
        	lastExerciseIndex = exerciseBox.getSelectedIndex();

            exerciseBox.setModel(new DefaultComboBoxModel<>(
            		model.getExerciseList(blockBox.getSelectedIndex() + 1)));

            try{
	            exerciseBox.setSelectedIndex(1);
	            exerciseBox.setSelectedIndex(0);
            }
            catch (Exception exception) {
            	exerciseBox.setSelectedIndex(0);
			}
		}
    }

	public String getAnswerText() {
		// TODO Auto-generated method stub
		return null;
	}



}// end class AbstractView





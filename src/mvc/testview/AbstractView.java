package mvc.testview;

import mvc.model.Model;

import javax.swing.*;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observer;

public abstract class AbstractView extends JFrame implements Observer {

    /** Model for access database */
    protected Model model;

    /** label contains the title course: Programmeren in JAVA met BlueJ */
    protected JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");

    /** label for exercise */
    protected JLabel exercise_label = new JLabel("Exercise:");

    /** label for block */
    protected JLabel block_label = new JLabel("Block:");

    /** default components */
    /** button for next exerciseBox */
    protected JButton btnNext = new JButton("Next");

    /** button for previous exerciseBox */
    protected JButton btnPrevious = new JButton("Previous");

    /** JComboBox which holds blocks */
    protected JComboBox<String> blockBox = new JComboBox<>();

    /** JComboBox which holds exercise number */
    protected JComboBox<String> exerciseBox = new JComboBox<>();

    /** panel to hold panel */
    protected JPanel panel;

    /** panel to hold titlelabel, labelblock, labelexcercise and comboboxes */
    protected JPanel panelTitleAndTop;

	/** panel to hold title label */
    protected JPanel panelTitle;

    /** Top panel to hold components */
    protected JPanel panelTop;

    /** Bottom panel to hold components */
    protected JPanel panelBottom;

    //todo javadoc on textAreaFont
    protected Font textAreaFont;
    protected int lastBlockIndex;
	protected int lastExerciseIndex;
	/** Text area to show question */
	protected JTextArea questionTextArea;

    /**
     * Constructor for abstract view class to initialize the model
     *
     * @param Model model
     */
    public AbstractView(Model model) {
        /** initialize model */
        this.model = model;

    }

    public void setComponents() {
        /** setup font */
        textAreaFont = new Font("", Font.PLAIN, 13);

        /** initialize panels */
        panel = new JPanel();
        panelTitle = new JPanel();
        panelBottom = new JPanel();
        panelTitleAndTop = new JPanel();

        /** set actionlisteners to buttons*/
        btnNext.addActionListener(new NextButtonLister());
        btnPrevious.addActionListener(new PreviousButtonListener());
        /** combobox blockBox */
        blockBox = new JComboBox<>(model.getBlockList());
        blockBox.addItemListener(new BlockBoxListener());

        /** set blockBox to the first index */
        blockBox.setSelectedIndex(0);
        /** combobox exerciseBox */
        exerciseBox = new JComboBox<>(
        		model.getExerciseList(blockBox.getSelectedIndex() + 1));
        exerciseBox.addItemListener(new ExerciseBoxListener());

        /** layout managers */
        panel.setLayout(new BorderLayout(5, 5));//Borderlayout to main panel
        panelBottom.setLayout(new GridLayout(1, 2, 5, 5));
        panelTitleAndTop.setLayout(new BoxLayout(panelTitleAndTop, BoxLayout.Y_AXIS));
        /** add title to panel */
        panelTitle.add(title_course);

        /**  */
        panelTitleAndTop.add(panelTitle);
        setPanelTop();
        panel.add(panelTitleAndTop, BorderLayout.NORTH);

        /** add buttons to bottom panel */
        panelBottom.add(btnNext);
        panelBottom.add(btnPrevious);
        /** add bottom panel to main panel*/
        panel.add(panelBottom, BorderLayout.SOUTH);
        /** add main panel to frame */
        add(panel);


    }

    /**
     * Make panel top which contains label and comboBox block, label and comboBox exercise
     */
    public void setPanelTop() {

    	panelTop = new JPanel();
    	panelTop.setLayout(new GridBagLayout());
    	panelTitleAndTop.add(panelTop);

    	GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 10);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        panelTop.add(block_label, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        panelTop.add(blockBox, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 30, 0, 10);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        panelTop.add(exercise_label, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 0;
        panelTop.add(exerciseBox, c);

        questionTextArea = new JTextArea();
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets( 20, 0, 0, 0);
        c.ipady = 400;
        c.ipadx = 400;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        panelTop.add(questionTextArea, c);

	}

    //TODO write javadoc
    public void btnNext() {

        if (exerciseBox.getSelectedIndex() < exerciseBox.getItemCount() - 1) {
            exerciseBox.setSelectedIndex(exerciseBox.getSelectedIndex() + 1);
        }


    }

    //TODO write javadoc
    public void btnPrevious() {

        if (exerciseBox.getSelectedIndex() > 0) {
            exerciseBox.setSelectedIndex(exerciseBox.getSelectedIndex() - 1);
        }

    }

    public void updateView() {
    }

    /**
     * inner class for actionListener on the next button
     */
    private class NextButtonLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnNext();
        }
    }

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

}






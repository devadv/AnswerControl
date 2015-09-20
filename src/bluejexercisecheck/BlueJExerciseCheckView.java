package bluejexercisecheck;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public abstract class BlueJExerciseCheckView extends JFrame {

    public static String[] exercises = {"1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "1.10", "1.11", "1.12",
        "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.22", "1.23", "1.24", "1.25", "1.26", "1.27",
        "1.28", "1.28", "1.29", "1.30", "1.31", "1.32", "1.33", "1.34", "1.35", "1.36", "2.1", "2.2", "2.3", "2.4", "2.5", "2.6",
        "2.7", "2.8", "2.9", "2.10", "2.11", "2.12", "2.13", "2.14", "2.15", "2.16", "2.17", "2.18", "2.19", "2.20", "2.21",
        "2.22", "2.23", "2.24", "2.25", "2.26", "2.27", "2.28", "2.29", "2.30", "2.31", "2.32", "2.33", "2.34", "2.35", "2.36",
        "2.37", "2.38", "2.39", "2.40", "2.41", "2.42", "2.43", "2.44", "2.45", "2.46", "2.47", "2.28", "2.29", "2.30", "2.31",
        "2.32", "2.33", "2.34", "2.35", "2.36", "2.37", "2.38", "2.39", "2.40", "2.41", "2.42", "2.43", "2.44", "2.45",
        "2.46", "2.47", "2.48", "2.49", "2.50", "2.61", "2.62", "2.63", "2.64", "2.65", "2.66", "26.7", "2.68", "2.69",
        "2.70", "2.81", "2.82", "2.83", "2.84", "2.85", "2.86", "2.87", "2.88", "2.89", "2.90", "2.91", "2.92", "2.93"};

    //public static String[] blocks = {"1.1 - 1.20", "1.21 - 1.36", "2.1 - 2.20", "etc"};
    protected JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");
    protected JButton inputQuestion = new JButton("Invoer vragen");
    protected JButton inputCorrectAnswer = new JButton("Invoer antwoorden");
    protected JComboBox<String> listExercises = new JComboBox<>(exercises);
    protected JLabel labelBlock = new JLabel("Blok");
    protected JComboBox<String> listBlocks = new JComboBox<>();

    protected JLabel labelExercise = new JLabel("Oefening:");
    protected JLabel labelQuestion = new JLabel("Vraag:");

    protected JButton btnSave = new JButton("Save");
    protected JButton btnNext = new JButton("Next");
    protected JButton btnPrevious = new JButton("Previous");
    protected Box box;
    protected JPanel panel;
    protected JPanel panelTitle;
    protected JPanel panelTop;
    protected JPanel panelQuestion;
    protected JPanel panelBottom;
    protected final List<String> arrayList_exercises;

    public BlueJExerciseCheckView() {
        arrayList_exercises = Arrays.asList(exercises);
        //box for textarea
        box = Box.createHorizontalBox();
        //main panel - layout vertical
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        //title panel 		
        panelTitle = new JPanel();
        //top panel
        panelTop = new JPanel();
        //question panel
        panelQuestion = new JPanel();
        //bottom panel
        panelBottom = new JPanel();

        //add components to panels	
        panelTitle.add(title_course);
        panelTop.add(labelBlock);
        panelTop.add(listBlocks);
        panelTop.add(labelExercise);
        panelTop.add(listExercises);
        panelQuestion.add(labelQuestion);

        panelBottom.add(btnSave);
        panelBottom.add(btnNext);
        panelBottom.add(btnPrevious);

        //add to main panel
        panel.add(panelTitle);
        panel.add(panelTop);
        panel.add(panelQuestion);
        panel.add(panelBottom);

        this.setResizable(false);
        this.setLocation(new Point(800, 200));
        this.getContentPane().add(panel);
        this.pack();

    }

    /**
     * abstract method must be implemented in the first concrete class
     */
    abstract public void setGUI();

    /**
     * action listener for the save button
     *
     * @param listenForSaveBtn ActionListener
     */
    public void addSaveActionListener(ActionListener listenForSaveBtn) {

        btnSave.addActionListener(listenForSaveBtn);

    }

    /**
     * action listener for the next button
     *
     * @param listenForNextBtn ActionListener
     */
    public void addNextActionListener(ActionListener listenForNextBtn) {

        btnNext.addActionListener(listenForNextBtn);

    }

    /**
     * action listener for the previous button
     *
     * @param listenForPreviousBtn ActionListener
     */
    public void addPreviousActionListener(ActionListener listenForPreviousBtn) {

        btnPrevious.addActionListener(listenForPreviousBtn);

    }

    /**
     * get the selected block
     * @return the selected block
     */
    public String getSelectedBlock() {
        return listBlocks.getSelectedItem().toString();
    }

    /**
     * get the selected exercise
     * @return the selected exercise
     */
    public String getSelectedExercise() {
        return listExercises.getSelectedItem().toString();

    }

    /**
     * get the selected block index
     * @return the selected block index
     */
    public int getSelectedBlockIndex() {
        return listBlocks.getSelectedIndex() + 1;
    }

    /**
     * get the selected exercise index
     * @return the selected exercise index
     */
    public int getSelectedExerciseIndex() {
        return listExercises.getSelectedIndex() + 1;
    }

    /**
     * set the JComboBox list
     *
     * @param blocks list of blocks
     */
    public void setBlocks(String[] blocks) {

        listBlocks.setModel(new DefaultComboBoxModel<>(blocks));

    }

    /**
     * set the index to next exercise
     *
     */
    public void setNextExercise() {
        if (listExercises.getSelectedIndex() < listExercises.getItemCount() - 1) {
            listExercises.setSelectedIndex(listExercises.getSelectedIndex() + 1);
        }

    }

    /**
     * set the index to previous exercise
     *
     */
    public void setPreviousExercise() {
        if (listExercises.getSelectedIndex() > 0) {
            listExercises.setSelectedIndex(listExercises.getSelectedIndex() - 1);
        }
    }

}

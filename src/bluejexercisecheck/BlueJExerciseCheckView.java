package bluejexercisecheck;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public abstract class BlueJExerciseCheckView extends JFrame 
{
    public static String[] exercises = {"1.1", "1.2", "1.3", "1.4","1.5","1.6","1.7","1.8","1.9","1.10","1.11","1.12","1.13","1.14","1.15",
 "1.16","1.17","1.18","1.19", "1.20","1.21", "1.22", "1.23","1.24","1.25","1.26","1.27","1.28",
 "1.28","1.29","1.30","1.31","1.32","1.33","1.34","1.35","1.36",
"2.1", "2.2", "2.3", "2.4","2.5","2.6","2.7","2.8","2.9","2.10","2.11","2.12","2.13","2.14","2.15",
"2.16","2.17","2.18","2.19", "2.20","2.21","2.22", "2.23","2.24","2.25","2.26","2.27","2.28",
"2.29","2.30", "2.31", "2.32", "2.33", "2.34", "2.35","2.36","2.37","2.38","2.39","2.40",
"2.41", "2.42", "2.43", "2.44","2.45","2.46","2.47","2.48", "2.49","2.50", "2.51","2.52","2.53",
"2.54", "2.55","2.56","2.57", "2.58", "2.59", "2.60", "2.61", 
"2.62", "2.63", "2.64","2.65","2.66","2.67","2.68","2.69","2.70","2.71", 
"2.72", "2.73", "2.74","2.75","2.76","2.77","2.78","2.79","2.80","2.81",
"2.82", "2.83", "2.84","2.85","2.86","2.87","2.88","2.89","2.90","2.91", "2.92", "2.93",
"3.1", "3.2", "3.3", "3.4","3.5","3.6","3.7","3.8","3.9","3.10","3.11","3.12","3.13","3.14","3.15",
"3.16","3.17","3.18","3.19", "3.20","3.21","3.22", "3.23", "3.24","3.25", "3.26 ","3.27","3.28",
"3.29","3.30","3.31","3.32","3.33","3.34","3.35","3.36","3.37","3.38","3.39","3.40","3.41", 
"3.42", "3.43","3.44","3.45","3.46",
"4.1", "4.2", "4.3", "4.4","4.5","4.6","4.7","4.8","4.9","4.10","4.11","4.12","4.13","4.14","4.15",
"4.16","4.17","4.18","4.19", "4.20","4.21","4.22", "4.23","4.24","4.25","4.26","4.27","4.28",
"4.29","4.30","4.31","4.32","4.33","4.34","4.35","4.36","4.37","4.38","4.39","4.40","4.41", 
"4.42", "4.43","4.44","4.45","4.46","4.47", "4.48","4.49","4.50","4.51", "4.52", "4.53",
"4.54","4.55","4.56","4.57", "4.58","4.59","4.60","4.61", "4.62", "4.63", "4.64",
"4.65","4.66","4.67","4.68","4.69","4.70","4.71", "4.72", "4.73", "4.74","4.75",
"4.76","4.77","4.78","4.79","4.80","4.81", "4.82", "4.83", "4.84","4.85","4.86","4.87",
"5.1", "5.2","5.3","5.4","5.5","5.6","5.7","5.8","5.9","5.10","5.11","5.12","5.13","5.14","5.15",
"5.16","5.17","5.18","5.19", "5.20","5.21","5.22", "5.23", "5.24","5.25","5.26","5.27","5.28",
"5.29","5.30","5.31","5.32","5.33","5.34","5.35","5.36","5.37","5.38","5.39","5.40","5.41", 
"5.42", "5.43", "5.44","5.45","5.46","5.47","5.48","5.49","5.50","5.61", "5.62", "4.63",
"5.64","5.65","5.66","5.67","5.68","5.69","5.70","5.71", "5.72", "5.73",
"6.1", "6.2", "6.3", "6.4","6.5","6.6","6.7","6.8","6.9","6.10","6.11","6.12","6.13","6.14",
"6.15","6.16","6.17","6.18","6.19","6.20","6.21","6.22","6.23","6.24","6.25","6.26","6.27",
"6.28","6.29", "6.30","6.31", "6.32", "6.33", "6.34","6.35","6.36","6.37","6.38","6.39","6.40",
"6.41", "6.42","6.43", "6.44","6.45","6.46","6.47","6.48","6.49","6.50","6.51", "6.52", "6.53",
"6.54","6.55","6.56",
"7.1", "7.2", "7.3", "7.4", "7.5","7.6","7.7","7.8","7.9","7.10","7.11","7.12","7.13","7.14",
"7.15","7.16","7.17","7.18","7.19", "7.20","7.21","7.22", "7.23","7.24","7.25","7.26","7.27",
"7.28","7.29","7.30","7.31","7.32","7.33","7.34","7.35","7.36","7.37","7.38","7.39","7.40",
"7.41", "7.42","7.43","7.44","7.45","7.46","7.47","7.48","7.49", "7.50","7.51", "7.52",
 "7.53", "7.54","7.55","7.56","7.57",
"8.1", "8.2", "8.3", "8.4","8.5","8.6","8.7","8.8","8.9","8.10","8.11","8.12","8.13","8.14",
"8.15","8.16","8.17","8.18","8.19",
"9.1", "9.2", "9.3", "9.4","9.5","9.6","9.7","9.8","9.9","9.10","9.11","9.12","9.13","9.14",
"9.15","9.16",
"10.1", "10.2", "10.3", "10.4","10.5","10.6","10.7","10.8","10.9","10.10","10.11","10.12",
"10.13","10.14","10.15","10.16","10.17","10.18","10.19", "10.20","10.21","10.22", "10.23",
"10.24","10.25","10.26","10.27","10.28","10.29","10.30","10.31","10.32","10.33","10.34",
"10.35","10.36","10.37","10.38","10.39","10.40","10.41", "10.42", "10.43", "10.44","10.45",
"10.46","10.47","10.48","10.49","10.50","10.51", "10.52", "10.53", "10.54","10.55","10.56"
,"10.57","10.58","10.59","10.60","10.61", "10.62", "10.63", "10.64","10.65","10.66","10.67",
"10.68","10.69","10.70","10.71","10.72",
"11.1", "11.2", "11.3", "11.4","11.5","11.6","11.7","11.8","11.9","11.10","11.11","11.12",
"11.13","11.14","11.15","11.16","11.17","11.18","11.19", "11.20","11.21","11.22", "11.23",
"11.24","11.25","11.26","11.27","11.28","11.29","11.30","11.31","11.32","11.33","11.34",
"11.35","11.36","11.37","11.38","11.39","11.40","11.41", "11.42", "11.43","11.44","11.45",
"11.46","11.47","11.48","11.49", "11.40","11.41", "11.42", "11.43", "11.44","11.45","11.46",
"11.47","11.48","11.49","4.51","11.51", "11.52", "11.53","11.54","11.55","11.56","11.57",
"11.58","11.59","11.60","11.61", "11.62", "11.63", "11.64","11.65","11.66","11.67","11.68",
"11.69","11.70","11.71", "11.72", "11.73", "11.74","11.75","11.76","11.77","11.78","11.79",
"12.1", "12.2", "12.3", "12.4","12.5","12.6","12.7","12.8","12.9","12.10","12.11","12.12",
"12.13","12.14","12.15","12.16","12.17","12.18","12.19", "12.20","12.21","12.22", "12.23", 
"12.24","12.25","12.26","12.27","12.28","12.29","12.30","12.31","12.32","12.33","12.34",
"12.35","12.36","12.37","12.38","12.39","12.40","12.41", "12.42", "12.43","12.44","12.45",
"12.46","12.47","12.48","12.49","12.50","12.51", "12.52", "12.53","12.54",
"13.1", "13.2", "13.3", "13.4","13.5","13.6","13.7","13.8","13.9","13.10","13.11","13.12",
"13.13","13.14","13.15","13.16","13.17","13.18",
"14.1", "14.2", "14.3", "14.4","14.5","14.6","14.7","14.8","14.9","14.10","14.11","14.12",
"14.13","14.14","14.15","14.16","14.17","14.18","14.19", "14.20","14.21","14.22", "14.23", 	  
"14.24","14.25","14.26","14.27","14.28"};

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

    public BlueJExerciseCheckView() 
    {
        this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE ); 
        
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
     * closing listener for the window
     *
     * @param windowAdapter WindowAdapter
     */
    public void addWindowClosingListener( WindowAdapter windowAdapter) {

         this.addWindowListener( windowAdapter );
    }
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
     *
     * @return the selected block
     */
    public String getSelectedBlock() {
        return listBlocks.getSelectedItem().toString();
    }

    /**
     * get the selected exercise
     *
     * @return the selected exercise
     */
    public String getSelectedExercise() {
        return listExercises.getSelectedItem().toString();

    }

    /**
     * get the selected block index
     *
     * @return the selected block index
     */
    public int getSelectedBlockIndex() {
        return listBlocks.getSelectedIndex() + 1;
    }

    /**
     * get the selected exercise index
     *
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

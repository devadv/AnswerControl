package mvc.testview;

import mvc.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observer;

public abstract class AbstractView extends JFrame implements Observer {
    /**
     * Model for access database
     */
    protected Model model;
    /**
     * title label
     */
    protected JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");
    /** default components */
    /**
     * button for next exerciseBox
     */
    protected JButton btnNext = new JButton("Next");
    /**
     * button for previous exerciseBox
     */
    protected JButton btnPrevious = new JButton("Previous");
    /**
     * combobox which holds exerciseBox number
     */
    protected JComboBox<String> exerciseBox = new JComboBox<>();
    /**
     * panel to hold panel
     */
    protected JPanel panel;
    /**
     * panel to hold title label
     */
    protected JPanel panelTitle;
    /**
     * Top panel to hold components
     */
    protected JPanel panelTop;
    /**
     * Bottom panel to hold components
     */
    protected JPanel panelBottom;
    protected Font textAreaFont;

    /**
     * model
     */


    public AbstractView(Model model) {
        /** initialize model */
        this.model = model;
        exerciseBox = new JComboBox<>(model.getExerciseList(1));
    }

    public void setComponents() {
        /** setup font */
        textAreaFont = new Font("", Font.PLAIN, 13);
        /** initialize panels */
        panel = new JPanel();
        panelTitle = new JPanel();
        panelTop = new JPanel();
        panelBottom = new JPanel();
        /** set actionlisteners to buttons*/
        btnNext.addActionListener(new NextButtonLister());
        btnPrevious.addActionListener(new PreviousButtonListener());
        /** combobox exerciseBox */
        exerciseBox = new JComboBox<>(model.getExerciseList(1));
        exerciseBox.addItemListener(new ExerciseBoxListener());
        /** layout managers */
        panel.setLayout(new BorderLayout(5, 5));//Borderlayout to main panel
        panelBottom.setLayout(new GridLayout(1, 2, 5, 5));
        /** add title to panel */
        panelTitle.add(title_course);
        /** add titlepanel to main panel*/
        panel.add(panelTitle);
        /** add execercise combobox to top panel */
        panelTop.add(exerciseBox);
        /** add top panel to main panel */
        panel.add(panelTop);
        /** add buttons to bottom panel */
        panelBottom.add(btnNext);
        panelBottom.add(btnPrevious);
        /** add bottom panel to main panel*/
        panel.add(panelBottom, BorderLayout.SOUTH);
        /** add main panel to frame */
        add(panel);


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

    public void updateView(ItemEvent event) {
        System.out.println("item changed to " + event.getItem());
    }

    //TODO write javadoc
    private class NextButtonLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnNext();
        }


    }

    //TODO write javadoc
    private class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            btnPrevious();
        }
    }

    //TODO write javadoc
    private class ExerciseBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            updateView(e);
        }
    }
}






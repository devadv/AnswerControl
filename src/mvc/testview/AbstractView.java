package mvc.testview;

import mvc.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

public abstract class AbstractView extends JFrame implements Observer {
    /** Model for access database */
    protected Model model;
    /** title label */
    protected JLabel title_course = new JLabel("Programmeren in JAVA met BlueJ");
    /** default components */
    /** button for next exercise */
    protected JButton btnNext = new JButton("Next");
    /** button for previous exercise */
    protected JButton btnPrevious = new JButton("Previous");
    /** combobox which holds exercise number */
    protected JComboBox<String> exercise;
    /** panel to hold panel */
    protected JPanel panel;
    /** panel to hold title label */
    protected JPanel panelTitle;
    /** Top panel to hold components */
    protected JPanel panelTop;
    /** Bottom panel to hold components */
    protected JPanel panelBottom;
    protected Font textAreaFont;

    /** model */



    public AbstractView(Model model){
        /** initialize model */
        this.model = model;
        exercise = new JComboBox<>(model.getExerciseList(1));
    }

    public void setComponents(){
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
        /** combobox exercise */
        exercise = new JComboBox<>(model.getExerciseList(1));
        /** layout managers */
        panel.setLayout(new BorderLayout(5,5));//Borderlayout to main panel
        panelBottom.setLayout(new GridLayout(1,2,5,5));
        /** add title to panel */
        panelTitle.add(title_course);
        /** add titlepanel to main panel*/
        panel.add(panelTitle);
        /** add execercise combobox to top panel */
        panelTop.add(exercise);
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


    private class NextButtonLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("next");
        }
    }

    private class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("previous");
        }
    }


}

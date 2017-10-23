package mvc.testview;

import mvc.model.Model;

import javax.swing.*;
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
    /** model */



    public AbstractView(Model model){
        /** initialize model */
        this.model = model;
    }

    public void setComponents(){
        /** initialize panels */
        panel = new JPanel();
        panelTitle = new JPanel();
        panelTop = new JPanel();
        panelBottom = new JPanel();
        /** set actionlisteners*/
        btnNext.addActionListener(new NextButtonLister());
        btnPrevious.addActionListener(new PreviousButtonListener());
        /** combobox exercise */
        exercise = new JComboBox<>(model.getExerciseList(1));
        /** add title to panel */
        panelTitle.add(title_course);
        /** add titlepanel to main panel*/
        panel.add(panelTitle);
        /** add buttons to bottom panel*/
        panelBottom.add(btnNext);
        panelBottom.add(btnPrevious);
        /** add bottom panel to main panel*/
        panel.add(panelBottom);
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

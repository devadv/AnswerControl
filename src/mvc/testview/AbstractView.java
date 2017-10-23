package mvc.testview;

import mvc.model.Model;

import javax.swing.*;
import java.util.Observer;

public abstract class AbstractView extends JFrame implements Observer {
    /** Model for access database */
    private final Model model;
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


    public AbstractView(Model model){
        this.model = model;
    }

    public void setComponents(){

        panel = new JPanel();



    }




}

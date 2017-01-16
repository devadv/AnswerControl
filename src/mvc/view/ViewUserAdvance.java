
package mvc.view;

import java.awt.Dimension;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import mvc.controller.iCRUD;
import mvc.model.Model;

public class ViewUserAdvance extends View
{
    public ViewUserAdvance(Model model, iCRUD controller)
    {
        super(model, controller);
        this.model = model;
        this.controller = controller;
        setGUI();
    }
    
    public void setGUI()
    {
        String[] columnName = model.getBlockList();
        
        
        String[] columnNames = {"First Name",
                        "Last Name",
                        "Sport",
                        "# of Years",
                        "Vegetarian"};
    
        Object[][] data = { {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
                            {"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
    {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
    {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
    {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)},
                 {"Kathy", "Smith",
     "Snowboarding", new Integer(5), new Boolean(false)},
    {"John", "Doe",
     "Rowing", new Integer(3), new Boolean(true)},
    {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
    {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
    {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)}
};
        
        JFrame frame = new JFrame("User Advance");
        JPanel panel = new JPanel();
        
        JTable userAdvance = new JTable(data, columnNames);
        
        JScrollPane scrollPane = new JScrollPane(userAdvance);
        userAdvance.setPreferredScrollableViewportSize(new Dimension(900, 500));
        userAdvance.setFillsViewportHeight(true);
        panel.add(scrollPane);
        
        frame.add(panel);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocation(500, 400);
        frame.setSize(900, 500);
        frame.setVisible(true);
        
    }

    @Override
    public void exerciseId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnSave() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnPrevious() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void blocksId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnCheckAnswer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

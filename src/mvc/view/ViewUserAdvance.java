
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
        super(model, controller, "ViewInputQuestion");
        this.model = model;
        this.controller = controller;
        setGUI();
    }

    public void setGUI()
    {
        String[] columnName = model.getColumnNames();
        Object[][] userAnswer = model.getUserProgress();

        JFrame frame = new JFrame("User Advance");
        JPanel panel = new JPanel();

        JTable userAdvance = new JTable(userAnswer, columnName);

        JScrollPane scrollPane = new JScrollPane(userAdvance);
        userAdvance.setPreferredScrollableViewportSize(new Dimension(1550, 200));
        userAdvance.setFillsViewportHeight(true);
        userAdvance.setAutoResizeMode(0);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocation(300, 400);
        frame.setSize(1600, 500);
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

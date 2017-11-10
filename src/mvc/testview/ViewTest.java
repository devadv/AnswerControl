package mvc.testview;

import mvc.model.Model;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.util.Observable;

public class ViewTest extends AbstractView {

    private JTextArea answerField;

    public ViewTest(Model model) {
        super(model);
        setComponents();
        this.setTitle("TestView");
        this.setSize(600, 800);
        this.setLocation(800, 200);
        this.setVisible(true);

    }

    @Override
    public void setComponents() {
        super.setComponents();
        
        answerField = new JTextArea(25, 38);
        answerField.setLineWrap(true);
        answerField.setWrapStyleWord(true);
        answerField.setFont(textAreaFont);
        JScrollPane jspAnswer = new JScrollPane(answerField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        JPanel panelAnswer = new JPanel();
        panelAnswer.add(jspAnswer);
        panel.add(panelAnswer, BorderLayout.CENTER);
        answerField.setText(model.retrieveQuestion(exerciseBox.getSelectedItem().toString()));
    }

    @Override
    public void update(Observable o, Object arg) {
    	updateView();
    }

    @Override
    public void btnNext() {
        super.btnNext();
    }

    @Override
    public void btnPrevious() {
        super.btnPrevious();
    }

    @Override
    public void updateView() {
    	answerField.setText(model.retrieveQuestion(exerciseBox.getSelectedItem().toString()));

    }
}

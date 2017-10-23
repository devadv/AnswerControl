package mvc.testview;

import mvc.model.Model;

import javax.swing.*;
import java.util.Observable;

public class ViewTest extends AbstractView {

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
        exercise.setModel(new DefaultComboBoxModel<>((model.getExerciseList(1))));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

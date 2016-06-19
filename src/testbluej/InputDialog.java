/*
 *
 */
package testbluej;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputDialog extends JFrame
{
    public InputDialog()
    {
       super("Input"); 
    }
    
    public int getInput(String message)
    {
        setLayout(new GridLayout(1, 2));
        
        JLabel messageJLabel = new JLabel(message);
        JTextField inputJTextField = new JTextField(2);
        String number = inputJTextField.getText();
        
        return Integer.parseInt(number);
    }
}

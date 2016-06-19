/*
 *
 */
package testbluej;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


/**
 *
 */
public class GUI_InputQuestionsSolutions extends JFrame
{
    
    // Create and initialize a PerformingTask object
    PerformingTasks performingTasks = new PerformingTasks();
    InputDialog inputDialog = new InputDialog();
        
    /**
     * no-argument constructor set up the GUI
     */
    public GUI_InputQuestionsSolutions()
    {
        super("BlueJ fill database");
        
        addWindowListener(new WindowClosingAdapter());// add handler
        
        createMenuBar();// create menu bar
        createToolBar();// create tool bar
        createTextAreas();// create text areas
        
    }// end constructor GUI_InputQuestions

    // Closing window
    private class WindowClosingAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent we) 
        {
            performingTasks.exit();
        }
    }// end class WindowClosingAdapter
    
    JTextArea questionJTextArea;
    JTextArea solutionTextArea;
    // create text areas
    private void createTextAreas()
    {
         //create panels to add labels and text areas
        JPanel totalJpanel = new JPanel();
        totalJpanel.setLayout(new GridLayout(2, 1, 5, 10));
        
        JPanel northJPanel = new JPanel();
        northJPanel.setLayout(new BorderLayout());
        JLabel questionJLabel = new JLabel("Question input:");
        
        questionJTextArea = new JTextArea("hello world", 4, 1);
        questionJTextArea.setLineWrap(true);
        JScrollPane questionJScrollPane = new JScrollPane(questionJTextArea);
        questionJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        northJPanel.add(questionJLabel, BorderLayout.NORTH);
        northJPanel.add(questionJScrollPane, BorderLayout.CENTER);
        
        JPanel southJPanel = new JPanel();
        southJPanel.setLayout(new BorderLayout());
        JLabel solutionjlJLabel = new JLabel("Solution input:");
        
        solutionTextArea = new JTextArea("Hello world 2", 4, 1);
        solutionTextArea.setLineWrap(true);
        JScrollPane solutionJScrollPane = new JScrollPane(solutionTextArea);
        solutionJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        southJPanel.add(solutionjlJLabel, BorderLayout.NORTH);
        southJPanel.add(solutionJScrollPane, BorderLayout.CENTER);
        
        totalJpanel.add(northJPanel);
        totalJpanel.add(southJPanel);
        
        add(totalJpanel, BorderLayout.CENTER);
    }    
        
    // Create a JMenu bar that contains the necessary methods
    private void createMenuBar()
    {
        JMenu fileMenu = new JMenu("File");// create file menu
        
        JMenuItem exitItem = new JMenuItem("Exit");// create exit item
        fileMenu.add( exitItem );// add exit item to file menu
        exitItem.addActionListener(
            new ActionListener() // anonymous inner class
            {
                @Override // terminate application when user clicks exitItem
                public void actionPerformed(ActionEvent e) 
                {
                    performingTasks.exit();
                }
            } 
        );
        
        JMenuBar bar = new JMenuBar();// create menu bar
        setJMenuBar(bar);// add menu bar to application
        bar.add(fileMenu);// add file menu to menu bar
        
        JMenu viewMenu = new JMenu("View");// create view menu
        JMenuItem textColor = new JMenuItem("Text color");// create menu item
        viewMenu.add(textColor);// add text color item to view menu
        textColor.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    
                }
            }
        );
        
        JMenuItem backGroundColor = new JMenuItem("Back ground color");// create menu item
        viewMenu.add(backGroundColor);// add back ground item to view menu
        
        JMenuItem fontSize = new JMenuItem("Font size");// create menu item
        fontSize.setToolTipText("Change font of text");// add tool tip to font color item
        viewMenu.add(fontSize);// add font color item to view menu
        fontSize.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String name = questionJTextArea.getFont().getName();
                int fontSize = inputDialog.getInput("Enter font size:");
                Font font = new Font(name, Font.PLAIN, fontSize);
                questionJTextArea.setFont(font);
                solutionTextArea.setFont(font);
            }
        }
        );
        
        
        
        bar.add(viewMenu);// add view menu to menu bar       
        
    }// end method createMenuBar
    
    // Create a Tool bar that contains the necessary methods
    private void createToolBar()
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        // add appliction-exit image to Icon interface that paints Icons from Images
        ImageIcon imageExit = new ImageIcon("application-exit.png");
        
        JButton exitButton = new JButton(imageExit);// add imageExit to jButton
        toolBar.add(exitButton);// add exitButton to tool bar
        exitButton.setToolTipText("Exit the application");// add tool tip text to exit button
        exitButton.addActionListener(
            new ActionListener()// anonymous inner class
            {
                @Override // terminate application when user clicks exitButton
                public void actionPerformed(ActionEvent e) 
                {
                    performingTasks.exit();
                }
            }
        );
        
        // add save image to Icon interface that paints Icons from Images
        ImageIcon imageSave = new ImageIcon("save.png"); 
        JButton saveButton = new JButton(imageSave);// add imageSave to JButton
        toolBar.add(saveButton);// add saveButton to toolbar
        saveButton.setToolTipText("Save to database");// add tool tip text to save button
        saveButton.addActionListener(
            new ActionListener()// anonymous inner class
            {
                @Override// save to database when user clicks save button
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.println("Question and solution are saved to database.");
                }
            }
        );
        
        
        ImageIcon imagePrevious= new ImageIcon("previous.png");
        
        JButton previousButton = new  JButton(imagePrevious);
        toolBar.add(previousButton);
        previousButton.setToolTipText("Go to previous row in database");
        previousButton.addActionListener(
            new ActionListener() 
            {
                @Override// go to previous row when user clicks privious button
                public void actionPerformed(ActionEvent e) 
                {
                    // call method previous row
                }
            }
        );
        
        ImageIcon imageNext = new ImageIcon("next.png");
        JButton nextButton = new JButton(imageNext);
        toolBar.add(nextButton);
        nextButton.setToolTipText("Go to next row in database");
        nextButton.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //call methoc next row
                }
            }
        );
        
        JLabel blockJLabel = new JLabel("Block number:");
        blockJLabel.setFont(null);
        toolBar.add(blockJLabel);
        
        JComboBox blockJComboBox = new JComboBox();
        toolBar.add(blockJComboBox);
        blockJComboBox.setToolTipText("Set block number.");
        blockJComboBox.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    
                }
            }
        );
        
        JLabel exerciseJLabel = new JLabel("Exercise number:");
        toolBar.add(exerciseJLabel);
        
        JComboBox exerciseJComboBox = new JComboBox();
        toolBar.add(exerciseJComboBox);
        exerciseJComboBox.setToolTipText("Set exercise number.");
        exerciseJComboBox.addActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {

                }
            }
        );
        
        
        
        add(toolBar, BorderLayout.NORTH);
        
    }// end method createToolBar
 
}// end class GUI_InputQuestionsSolutions

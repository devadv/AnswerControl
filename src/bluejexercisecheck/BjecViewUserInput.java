package bluejexercisecheck;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;



/**
 * Gui for input of user answers
 * @author david
 */
@SuppressWarnings("serial")
public class BjecViewUserInput extends BlueJExerciseCheckView
{
	
	private JPanel panelQuestion;
	private JPanel panelAnswer;
	
	private JTextArea textAreaQuestion;
	private JTextArea textAreaAnswer;
	
	private JScrollPane jspQuestion;
	private JScrollPane jspAnswer;
	
	private JLabel labelAnswer;
	
	
	public BjecViewUserInput()
	{
		super();
		setGUI();
	}
	
	@Override
	public void setGUI()
	{
		this.setTitle("BlueJ Exercise Check  -  User input");
		panelQuestion = new JPanel();
		panelAnswer = new JPanel();
		
		textAreaQuestion = new JTextArea(8 ,30);
		textAreaQuestion.setEditable(false);
		textAreaQuestion.setLineWrap(true);
		textAreaQuestion.setWrapStyleWord(true);
		textAreaQuestion.setText("Vraag weergave");

		textAreaAnswer = new JTextArea(12, 30);
		textAreaAnswer.setLineWrap(true);
		textAreaAnswer.setWrapStyleWord(true);
		textAreaAnswer.setText("Antwoord invoeren");
				
		
		labelAnswer = new JLabel("Antwoord:");
		

		jspQuestion = new JScrollPane(textAreaQuestion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspAnswer = new JScrollPane(textAreaAnswer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelQuestion.add(jspQuestion);
		panelQuestion.add(labelQuestion);
		
		
		panelAnswer.add(labelAnswer);
		panelAnswer.add(jspAnswer);		
		
	
		//panel.add(labelQuestion);
		
		
		

		panel.add(panelQuestion);
		
		panel.add(panelAnswer);
		
		panel.add(panelBottom);


	}
	

	/* used by printHierarchy */
	private static String getCompDescr(Component comp)
	{
		String rv;
		if (comp instanceof JLabel)
		{
			rv = ((JLabel) comp).getText();
			if (rv.length() > 15)
			{
				rv = rv.substring(0, 13) + "..";
			}
		}
		else
		{
			rv = "";
		}
		return rv;
	}
	
	/* Used by printHierarchy */
	private static void printHierRec(Container container, int level)
	{
		Component[] subcomponent = container.getComponents();
		
		StringBuilder sp = new StringBuilder();
		for (int i = 0; i< level; i++)
			sp.append("  ");

		
		for (int i = 0; i < subcomponent.length; i++)
		{
						
			System.out.print(sp  + subcomponent[i].getClass().getSimpleName());
			if (subcomponent[i] instanceof Container)
			{
				Container subcontainer = (Container) subcomponent[i];
				String lo = ( subcontainer.getLayout() != null ) ? 
								subcontainer.getLayout().getClass().getSimpleName() : "";
				String compDescr = getCompDescr(subcomponent[i]);

				//System.out.println(" -- is container --" + lo);
				System.out.println("  " + lo + "   " +  compDescr );
								
				printHierRec((Container) subcomponent[i], level + 1);
			}
			else
			{
				System.out.println(" -- is geen container");
			}
		}

	}
	

	/**
	 * Prints GUI hierarchy tree on the console, starting from this object.
	 */
	private void printHierarchy()
	{
		Container container = null;
		if (this instanceof JFrame)
		{
			System.out.println("JFrame " + this.getClass().getName() + "  "
						+ this.getLayout().getClass().getSimpleName());

			container = this.getContentPane();
			System.out.println(this.getContentPane().getClass().getSimpleName() + " " +
					this.getContentPane().getLayout() +	" = content pane" );
		}
		else
		{
			container = this;
		}
		
		printHierRec(container, 1);
	
		
	}

	

	/**
	 * Main method for testing 
	 */
	public static void main(String[] args)
	{
		System.out.println("Show the userInput window for testing...\n");
		BjecViewUserInput userInput = new BjecViewUserInput();
		userInput.setSize(400, 600);
		userInput.setDefaultCloseOperation(EXIT_ON_CLOSE);
		userInput.setVisible(true);
		
		userInput.printHierarchy();
	}

}

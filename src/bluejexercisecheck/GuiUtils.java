package bluejexercisecheck;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GuiUtils
{



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
	public static void printHierarchy(Container container)
	{
		if (container instanceof JFrame)
		{
			System.out.println("JFrame " + container.getClass().getName() + "  "
						+ container.getLayout().getClass().getSimpleName());
	
			container = ((JFrame) container).getContentPane();
			System.out.println(container.getClass().getSimpleName() + " " +
					container.getLayout() +	" = content pane" );
		}
		
		printHierRec(container, 1);
		
	}

}
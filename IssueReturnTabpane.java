import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class IssueReturnTabpane extends JPanel{
	JTabbedPane jsp;
	
	public IssueReturnTabpane()
	{
		setLayout(new BorderLayout());
		jsp=new JTabbedPane();
		jsp.add("book issue tab",new BookIssueForm());
		jsp.add("book return tab",new BookReturnForm());
		
		add(jsp,BorderLayout.CENTER);
				
	}

}

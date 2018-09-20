import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class UserPane extends JPanel

{

	JTabbedPane jsp1;
	
	public UserPane()
	{
		setLayout(new BorderLayout());
		jsp1=new JTabbedPane();
		jsp1.addTab("Book tab", new IssueReturnTabpane());
		jsp1.addTab("Magazine tab ", new MagIssueReturnPane());
		jsp1.addTab("RTB tab", new RTBIssueReturnTabPane());

		add(jsp1,BorderLayout.CENTER);
		

	}
}

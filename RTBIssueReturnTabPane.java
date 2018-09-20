import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class RTBIssueReturnTabPane extends JPanel

{

	JTabbedPane jsp1;
	
	public RTBIssueReturnTabPane()
	{
		setLayout(new BorderLayout());
		jsp1=new JTabbedPane();
		jsp1.addTab("RTBIssue tab", new RTBIssueForm());
		jsp1.addTab("RTBReturn tab", new RTBReturnForm());

		add(jsp1,BorderLayout.CENTER);
		

	}
}

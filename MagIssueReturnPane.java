import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MagIssueReturnPane extends JPanel
{

	JTabbedPane jsp1;
	
	public MagIssueReturnPane()
	{
		setLayout(new BorderLayout());
		jsp1=new JTabbedPane();
		jsp1.addTab("MagIssue tab", new MagazineIssue());
		jsp1.addTab("MagReturn tab", new MagazineReturn());

		add(jsp1,BorderLayout.CENTER);
		

	}
}

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class FacStuTabPane extends JPanel
{
	JTabbedPane jsp;
	
	public FacStuTabPane()
		{
		setLayout(new BorderLayout());
			jsp=new JTabbedPane();
			jsp.addTab("Faculty tab", new FacRegForm());
			jsp.addTab("Student tab", new StuRegForm());
	
			add(jsp,BorderLayout.CENTER);
		}
}


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class AdminPane extends JPanel

{

	JTabbedPane jsp1;
	
	public AdminPane()
	{
		setLayout(new BorderLayout());
		jsp1=new JTabbedPane();
		jsp1.addTab("Book  Form tab", new BookForm());
		jsp1.addTab("Magazine Form tab ", new MagzineSection());
		jsp1.addTab("RTB Form tab", new RTBForm());

		add(jsp1,BorderLayout.CENTER);
		

	}
}

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.imageio.plugins.png.PNGImageWriter;
import com.sun.xml.internal.ws.api.server.Container;


public class WelcomePage extends JApplet implements ActionListener {

	//Image picture;
	
	JLabel lblogo,lbtitle,lbimg;
	JButton btstart;
	public static JButton btlog;
	JPanel Pnorth,Pwest;
	public static JPanel Pcenter,Psouth;
	public static java.awt.Container pane;
	
	public void init()
	{
		pane=this.getContentPane();
		Pnorth=new JPanel();
		Pnorth.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		lblogo=new JLabel(new ImageIcon("sakshi.jpeg"));
		lbtitle=new JLabel("COLLEGE MANAGEMENT SYSTEM");
		lbtitle.setFont(new Font("Algerian", Font.BOLD, 40));
		
		Pnorth.add(lblogo);
	
		Pnorth.add(lbtitle);
		
		/*int w=this.getSize().width;
		int h=this.getSize().height;*/
		
		
		lbimg=new JLabel(new ImageIcon("18.jpg"));
		lbimg.setPreferredSize(new Dimension(1700,500));

		Pcenter=new JPanel();
		Pcenter.setLayout(new FlowLayout());
		Pcenter.add(lbimg);
		
		Psouth=new JPanel();
		Psouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btstart=new JButton("START");
		btstart.setPreferredSize(new Dimension(150,50));
		Psouth.add(btstart);
		
		btlog=new JButton("Logout");
		btlog.setPreferredSize(new Dimension(150,50));
		
		add(Pnorth,BorderLayout.NORTH);
		add(Pcenter,BorderLayout.CENTER);
		add(Psouth,BorderLayout.SOUTH);
		//add(Pwest,BorderLayout.WEST);
		btstart.addActionListener(this);
		btlog.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object src= e.getSource();
		if(src==btstart)
		{
			remove(Pcenter);
			remove(Psouth);
			Pcenter=new RegSignTabPane();
			add(Pcenter,BorderLayout.CENTER);
			validate();
			repaint();
		}
		else
			System.exit(0);
	}
}

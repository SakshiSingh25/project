import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class sakshiprj extends JFrame implements ActionListener {
	JPanel panelc,m;
	JLabel lname,lby,lb;
	
	JButton bts;
	
	public sakshiprj(){
		m=new JPanel();

		m.setPreferredSize(new Dimension(1800,1500));
		setLayout(new BorderLayout());
	panelc=new JPanel();	
		
	Image2 Panel1 = new Image2(new BorderLayout());

	JPanel panel = new JPanel();
	panel.setOpaque(false);
	
	lname=new JLabel("Flight Management System");


	Font F1=new Font("comic sans", Font.BOLD, 70);
	lname.setFont(F1);
	
	lby=new JLabel("Sakshi Singh");


	Font F2=new Font("comic sans", Font.BOLD, 20);
	lby.setFont(F2);
	
	bts=new JButton("Enter");
	
	
	lb=new JLabel("By  :-");

	lb.setFont(F2);


	lname.setBounds(150,10,1000, 100);

	bts.setBounds(450,400,300, 40);
	lby.setBounds(900,600,200, 100);
	lb.setBounds(900,550,200, 100);



    Panel1.add(lname);


    Panel1.add(bts);
    Panel1.add(lby);
    Panel1.add(lb);


    
    bts.addActionListener(this);

    

    panel.setPreferredSize(new Dimension(1500,1000));
    panelc.setPreferredSize(new Dimension(1500,1000));

    Panel1.add(panel);
    panelc.add(Panel1);


	
	m.add(Panel1);
	add(m);
	
	

	setSize(500, 500);

	setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
new sakshiprj();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();

			if(src==bts){
				remove(m);	
				m =new UsernamePass();
				add(m);
					validate();
			}}}
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Registration extends JPanel implements ActionListener {
	JPanel m;
	JLabel lname,lage,laddress,lreg,lmail,lmob,lusername,lpass;
	JTextField tname,tage,taddress,tmail,tmob,tusername,tpass;
	JComboBox gender;
	JButton btr,btbook;
	
	public Registration()
	{	m=new JPanel();
		setLayout(new BorderLayout());
		m.setPreferredSize(new Dimension(1300,1000));
		Image1 Panel1 = new Image1(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		lname=new JLabel("Name");
		lreg=new JLabel("<html><b><font color=\"#C71585\">Registration</font></b></html>");
		lage=new JLabel("Age");
		laddress=new JLabel("Address");
		lmail=new JLabel("Mail ID");
		lmob=new JLabel("Mobile No");
		lusername=new JLabel("Username");
		lpass=new JLabel("Password");
		
		tname=new JTextField();
		tage=new JTextField();
		taddress=new JTextField();
		tmail=new JTextField();
		tmob=new JTextField();
		tusername=new JTextField();
		tpass=new JTextField();
		btr=new JButton("Register");
		btbook=new JButton("Go Back");
		Font F1=new Font("comic sans", Font.BOLD+Font.ITALIC, 45);
		
		lreg.setFont(F1);
		lreg.setBounds(200,0,1000,40);
		lname.setBounds(130,100,100, 40);
		tname.setBounds(200,100,300, 40);
		lage.setBounds(130,150,100, 40);
		tage.setBounds(200,150,300, 40);
		laddress.setBounds(130,200,100, 40);
		taddress.setBounds(200,200,300, 40);
		lmail.setBounds(130,250,100, 40);
		tmail.setBounds(200,250,300, 40);
		lmob.setBounds(130,300,100, 40);
		tmob.setBounds(200,300,300, 40);
		btr.setBounds(200,350,300, 40);
		btbook.setBounds(200,400,300, 40);
		lusername.setBounds(130,450,100, 40);
		tusername.setBounds(200,450,300, 40);
		lpass.setBounds(130,500,100, 40);
		tpass.setBounds(200,500,300, 40);
		
		
		String[] sItem3={"Male","Female"};
		gender = new JComboBox(sItem3);
		gender.setBounds(500,100,100,20);
		
         
	

        Panel1.add(lreg);
        Panel1.add(gender);
        Panel1.add(lname);
        Panel1.add(tname);
        Panel1.add(lage);
        Panel1.add(tage);
        Panel1.add(laddress);
        Panel1.add(taddress);
        Panel1.add(lmail);
        Panel1.add(tmail);
        Panel1.add(lmob);
        Panel1.add(tmob);
        Panel1.add(lusername);
        Panel1.add(tusername);
        Panel1.add(lpass);
        Panel1.add(tpass);
        Panel1.add(btr);
        Panel1.add(btbook);
        
        
        

        panel.setPreferredSize(new Dimension(1300,1000));

	    Panel1.add(panel, BorderLayout.CENTER);

	    btr.addActionListener(this);
	    btbook.addActionListener(this);
		m.add(Panel1);
		add(m);

		setSize(800, 600);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent f) {
		Object src=f.getSource();
		if(src==btr){
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				
				stmt.executeUpdate("create database if not exists register");
				stmt.executeUpdate("use flightDB");
				
				stmt.executeUpdate("create table if not exists register(empid int auto_increment,name varchar(100),age int,address varchar(100),mobile int,mail varchar(100),user varchar(100),pass varchar(100),primary key(empid))");
				
				PreparedStatement pstmt=con.prepareStatement("insert into register (name,age,address,mobile,mail,user,pass) values(?,?,?,?,?,?,?)");
				pstmt.setString(1,tname.getText());
				pstmt.setInt(2, Integer.parseInt(tage.getText()));
				pstmt.setString(3, taddress.getText());
			
				pstmt.setLong(4, Long.parseLong(tmob.getText()));
				pstmt.setString(5, tmail.getText());
				pstmt.setString(6,tusername.getText());
				pstmt.setString(7,tpass.getText());
				
				
				
				pstmt.executeUpdate();
				con.close();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		if(src==btbook)
		{
			remove(m);	
			m=new UsernamePass();
			add(m);
				validate();
	}
		
	}
	public static void main(String[] args) {
		
		new Registration();

			}

	

}

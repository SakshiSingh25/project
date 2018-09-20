import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class printdetails extends JPanel implements ActionListener {
	JTextField tuser,tf,tt,ttime,tp,tflight,tday,tname,tage,tadd,tmob,tmail,tair;
	JLabel luser,lf,lt,ltime,lp,lflight,lday,lname,lage,ladd,lmob,lmail,lreg,lair;
	JButton btgetdetails;
	JPanel Panel1;
public printdetails(){
	setLayout(new BorderLayout());
	Panel1 = new JPanel();
	
	Image6 Panel1 = new Image6(new BorderLayout());
	JPanel panel = new JPanel();
	panel.setOpaque(false);
	tuser=new JTextField();
	tuser.setText(UsernamePass.user);
	tf=new JTextField();
	tt=new JTextField();
	tp=new JTextField();
	tflight=new JTextField(10);
	tday=new JTextField(10);
	ttime=new JTextField(10);
	tname=new JTextField(10);
	tage=new JTextField(10);
	tmob=new JTextField(10);
	tadd=new JTextField(10);
	tmail=new JTextField(10);
	tair=new JTextField(10);
	
	lreg=new JLabel("Flight Details");
	luser=new JLabel("Username");
	lf=new JLabel("From");
	lt=new JLabel("To");
	ltime=new JLabel("Time");
	lp=new JLabel("Price");
	lflight=new JLabel("Flight no");
	lday=new JLabel("Day");
	lname=new JLabel("Name");
	lage=new JLabel("Age");
	ladd=new JLabel("Address");
	lmob=new JLabel("Mobile");
	lmail=new JLabel("Mail");
	lair=new JLabel("Airline");
	
	btgetdetails=new JButton("Get Details");
	
Font F1=new Font("comic sans", Font.BOLD, 70);
Font F2=new Font("comic sans", Font.BOLD, 20);
	
	lreg.setFont(F1);
	
	lreg.setBounds(250,0,1000,70);
	lreg.setForeground(Color.white);
	Panel1.add(lreg);
	luser.setFont(F2);
	
	luser.setBounds(50,110,100, 50);
	tuser.setBounds(200,120,100,30);
	
	btgetdetails.setBounds(400,120,100, 20);
	
	
	lname.setBounds(50,200,100, 50);
	tname.setBounds(50,250,100,30);
	
	lage.setBounds(200,200,100, 40);
	tage.setBounds(200,250,100,30);
	
	ladd.setBounds(350,200,100, 40);
	tadd.setBounds(350,250,100,30);
	
	lmail.setBounds(500,200,100, 40);
	tmail.setBounds(500,250,100,30);
	
	lmob.setBounds(650,200,100, 40);
	tmob.setBounds(650,250,100,30);
	
	
	lair.setBounds(50,400,100, 50);
	tair.setBounds(50,450,100,30);
	
	lflight.setBounds(200,400,100, 40);
	tflight.setBounds(200,450,100,30);
	
	lf.setBounds(350,400,100, 40);
	tf.setBounds(350,450,100,30);
	
	lt.setBounds(500,400,100, 40);
	tt.setBounds(500,450,100,30);
	
	ltime.setBounds(650,400,100, 40);
	ttime.setBounds(650,450,100,30);

	lday.setBounds(800,400,100, 40);
	tday.setBounds(800,450,100,30);

	lp.setBounds(950,400,100, 40);
	tp.setBounds(950,450,100,30);

	
	
	 Panel1.add(lreg);
	 Panel1.add(luser);
     Panel1.add(tuser);
      Panel1.add(btgetdetails);
    
     Panel1.add(lname);
     Panel1.add(tname);
     Panel1.add(lage);
     Panel1.add(tage);
     Panel1.add(ladd);
     Panel1.add(tadd);
     Panel1.add(lmail);
     Panel1.add(tmail);
     Panel1.add(lmob);
     Panel1.add(tmob);
	
     Panel1.add(lair);
     Panel1.add(tair);
     Panel1.add(lflight);
     Panel1.add(tflight);
     Panel1.add(lf);
     Panel1.add(tf);
     Panel1.add(ltime);
     Panel1.add(ttime);
     Panel1.add(lt);
     Panel1.add(tt);
     Panel1.add(lday);
     Panel1.add(tday);
     Panel1.add(lp);
     Panel1.add(tp);
	

	add(Panel1);
	 panel.setPreferredSize(new Dimension(1300,1000));

	    Panel1.add(panel, BorderLayout.CENTER);
	btgetdetails.addActionListener(this);
	setSize(800, 600);
	setVisible(true);
	}

public void actionPerformed(ActionEvent e) {
	String user=tuser.getText();

	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
		
		Statement stmt=con.createStatement();
		stmt.execute("use flightDB");

		PreparedStatement pstmt=con.prepareStatement("select * from printinfo where username=?");
		pstmt.setString(1,user );
		
		
	
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			tf.setText(rs.getString("from1"));
			tt.setText(rs.getString("to1"));
			tp.setText(rs.getString("price"));
			tday.setText(rs.getString("day"));
			tflight.setText(rs.getString("flightno"));
			ttime.setText(rs.getString("time"));
			tair.setText(rs.getString("air1"));
		}
		PreparedStatement pstmt1=con.prepareStatement("select * from register where user=?");
		pstmt1.setString(1,user );
		
	
		ResultSet rs1=pstmt1.executeQuery();
		
		while(rs1.next())
		{
			tname.setText(rs1.getString("name"));
			tage.setText(rs1.getString("age"));
			tadd.setText(rs1.getString("address"));
			tmail.setText(rs1.getString("mail"));
			tmob.setText(rs1.getString("mobile"));
		}
		con.close();
	}
		catch (ClassNotFoundException ee) {
		// TODO Auto-generated catch block
		ee.printStackTrace();
	}
	catch (SQLException ee)
	{
		ee.printStackTrace();
	}
	
}
public static void main(String[] args) {
	
	new printdetails();

		}

}
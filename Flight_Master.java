import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Panel;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Flight_Master extends JApplet implements ActionListener,ItemListener
{
	JLabel lfrom,lprice,lto,lcat,lair,lflightno;
	JTextField tfrom,tprice,tto,tair,tflightno;
	JButton bti,btu,btd;
	JComboBox tcat,cr;
	JPanel p1;
	
	public Flight_Master() 
	{
		setLayout(new FlowLayout());
		lflightno=new JLabel("Flight No");
		lfrom=new JLabel("from");
		lprice=new JLabel("price");
		lto=new JLabel("to");
		lcat=new JLabel("category");
		lair=new JLabel("airline");
		
		tflightno=new JTextField(10);
		tfrom=new JTextField(10);
		tprice=new JTextField(10);
		tto=new JTextField(10);
		tcat=new JComboBox();
		tcat.addItem("Select Flight Type");
		tcat.addItem("International");
		tcat.addItem("Domestic");
		tair=new JTextField(10);
		
		bti=new JButton("Insert");
		btu=new JButton("Update");
		
		btd=new JButton("delete");
		cr=new JComboBox();
		cr.addItem("Flightno");

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stmt=con.createStatement();
			
		stmt.executeUpdate("create database if not exists flightDB");
			stmt.executeUpdate("use flightDB");
		stmt.executeUpdate("create table if not exists flight_master(flightno varchar(100) ,from1 varchar(100),price int,to1 varchar(100),cat1 varchar(100),air1 varchar(100))");
		//stmt.executeUpdate("insert into flight_master (flightno,from1,price,to1,cat1,air1) values('',00,'destination','category','airline')");
			ResultSet rs=stmt.executeQuery("select flightno from flight_master");
			
			while(rs.next())
			{
				cr.addItem(""+rs.getString(1));
			}
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		add(lflightno);
		add(tflightno);
		add(cr);
		
		add(lfrom);
		add(tfrom);
		
		add(lprice);
		add(tprice);
		
		add(lto);
		add(tto);
		
		add(lcat);
		add(tcat);
		
		add(lair);
		add(tair);
		
		add(bti);
		add(btu);
		add(btd);
		bti.addActionListener(this);
		//add(btd=new JButton("Delete"));
		btd.addActionListener(this);
		
		btu.addActionListener(this);
		tcat.addItemListener(this);
		
}
	void delete()
	{
		
		

		 String flightno=(String)cr.getSelectedItem().toString();
		 
		
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("use flightDB");
			PreparedStatement pstmt=con.prepareStatement("delete from flight_master where flightno=?");
			pstmt.setString(1,flightno);
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

	@Override
	public void actionPerformed(ActionEvent ae) 
	{Object src=ae.getSource();
		if(src==bti){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate("create database if not exists flightDB");
			stmt.execute("use flightDB");
		stmt.executeUpdate("create table if not exists flight_master(flightno varchar(255),from1 varchar(100),price int,to1 varchar(100),cat1 varchar(100),air1 varchar(100),primary key(flightno))");
		
			PreparedStatement pstmt=con.prepareStatement("insert into flight_master values(?,?,?,?,?,?)");
			pstmt.setString(1,tflightno.getText());
			pstmt.setString(2, tfrom.getText());
			pstmt.setInt(3,Integer.parseInt(tprice.getText()));
			pstmt.setString(4, tto.getText());
			pstmt.setString(5, tcat.getSelectedItem().toString());
			pstmt.setString(6, tair.getText());
			
			
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

		if(src==btu){
			
			

			 String flightno=(String)cr.getSelectedItem().toString();
			 
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				stmt.executeUpdate("use flightDB");
				PreparedStatement pstmt=con.prepareStatement("update flight_master set from1=?,price=?,to1=?,cat1=?,air1=?,flightno=? where flightno=?");
				pstmt.setString(1, tfrom.getText());
				pstmt.setInt(2,Integer.parseInt(tprice.getText()));
				pstmt.setString(3, tto.getText());
				pstmt.setString(4, tcat.getSelectedItem().toString());
				pstmt.setString(5, tair.getText());
				pstmt.setString(6, tflightno.getText());
				pstmt.setString(7,flightno);
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
		else
		{
			delete();	
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{	

		 String flightno=(String)cr.getSelectedItem().toString();
		 String cat=(String)tcat.getSelectedItem().toString();
		
		
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("use flightDB");
			
			PreparedStatement pstmt=con.prepareStatement("select * from flight_master where flightno=?");
			pstmt.setString(1, flightno);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{	tfrom.setText(rs.getString("from1"));
				tprice.setText(rs.getInt("price")+"");
				tto.setText(rs.getString("to1"));
			
				tair.setText(rs.getString("air1"));
			}
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	}

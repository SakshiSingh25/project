import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sun.java2d.loops.GeneralRenderer;


public class BookIssueForm extends JPanel implements ActionListener,ItemListener{

JLabel lbid,lbbookid,lbtitle,lbissue,lbreturn,lbcover,lbcolor;
JTextField txid,txtitle,txissue,txreturn;
JComboBox jcbid;
JButton btsub;
JPanel P1,P2,P3,PCenter,PSouth;


	public BookIssueForm()
	{
		lbid=new JLabel("ID");
		lbbookid=new JLabel("Book_ID");
		lbtitle=new JLabel("Title");
		lbissue=new JLabel("Issue Date");
		lbreturn=new JLabel("Return Date");
		lbcover=new JLabel(new ImageIcon("7.jpg"));
		lbcolor=new JLabel("color");
		
		txid=new JTextField(SignInForm.Id+"");
		txid.setEnabled(false);
		txtitle=new JTextField(10);
		txtitle.setEditable(false);
		txissue=new JTextField(10);
		txreturn=new JTextField(10);
		
		btsub=new JButton("SUBMIT");
		
		jcbid =new JComboBox();
		jcbid.addItem("select Book Id");
		try{
			
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				
				stmt.execute("use LibraryDB");
					
				PreparedStatement pstmt=con.prepareStatement("select book_id from BookFormTb");
				ResultSet rs= pstmt.executeQuery();
				while(rs.next())
				{
					jcbid.addItem(rs.getInt(1)+"");
				}
				
				con.close();
			
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			} 
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
				
		P1=new JPanel();
		P1.setLayout(new GridLayout(2,2));
		P1.add(lbid);
		P1.add(txid);
		P1.add(lbbookid);
		P1.add(jcbid);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(2,4));
		P2.add(lbtitle);
		P2.add(txtitle);
		P2.add(lbcover);
		P2.add(lbcolor);
		
		P3=new JPanel();
		P3.setLayout(new GridLayout(3,2));
		P3.add(lbissue);
		P3.add(txissue);
		P3.add(lbreturn);
		P3.add(txreturn);
		P3.add(btsub);
			
		PCenter=new JPanel();
		PSouth=new JPanel();
		PSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		PCenter.setLayout(new GridLayout(3,1));
		PCenter.add(P1);
		PCenter.add(P2);
		PCenter.add(P3);
		setLayout(new BorderLayout());
		//PCenter.setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		add(PSouth, BorderLayout.SOUTH);
		
		btsub.addActionListener(this);
		jcbid.addItemListener(this);
		txtitle.setEnabled(false);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object src=ae.getSource();
		
		if(src==btsub)
		{
			if(jcbid.getSelectedIndex() !=0)
			{
			int id=  Integer.parseInt(jcbid.getSelectedItem().toString());
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				try
				{
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists LibraryDB");
					stmt.execute("use LibraryDB");
					stmt.executeUpdate("Create table if not exists BookIssueTb(Id int ,book_ID int ,issue_date date,return_date date)");
					
					PreparedStatement pstmt=con.prepareStatement("Insert into BookIssueTb(Id,book_ID,issue_date,return_date)values(?,?,?,?)");
					pstmt.setInt(1, Integer.parseInt(txid.getText()));
					pstmt.setInt(2, id);
					java.util.Date dt=new Date(txissue.getText());
					java.sql.Date issuedt=new java.sql.Date(dt.getYear(), dt.getMonth(),dt.getDate());
					
					java.util.Date dt1=new Date(txreturn.getText());
					java.sql.Date retdt=new java.sql.Date(dt1.getYear(), dt1.getMonth(),dt1.getDate());
					
					pstmt.setDate(3, issuedt);
					pstmt.setDate(4, retdt);
					pstmt.executeUpdate();
					con.close();
					
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if(jcbid.getSelectedIndex() !=0)
		{
		int id=  Integer.parseInt(jcbid.getSelectedItem().toString());
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.execute("use LibraryDB");
		
			PreparedStatement pstmt=con.prepareStatement("select title,coverpage,book_color from BookFormTb where Book_ID =? ");
			pstmt.setInt(1, id);
			InputStream fin=null;
			ResultSet rs= pstmt.executeQuery();
			while (rs.next())
			{
				txtitle.setText(rs.getString(1));
				fin=rs.getBinaryStream(2);
				byte barr[]=new byte[256*256];
				fin.read(barr);
				lbcover.setIcon(new ImageIcon(barr));	
				
				int rgb=Integer.parseInt(rs.getString(3));
				lbcolor.setForeground(new Color(rgb));
			}
		
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		catch (Exception e1) 
		{
		e1.printStackTrace();
		}
		}
		
	}
}






















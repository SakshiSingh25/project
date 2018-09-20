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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sun.java2d.loops.GeneralRenderer;


public class RTBIssueForm extends JPanel implements ActionListener,ItemListener{

JLabel lbid,lbbookid,lbtitle,lbissue,lbreturn,lbissuedt;
JTextField txid,txtitle,txissue,txreturn,txissuedt;
JComboBox jcbid;
JButton btsub;
JPanel P1,P2,P3,PCenter;


	public RTBIssueForm()
	{
		lbid=new JLabel("ID");
		lbbookid=new JLabel("Book_ID");
		lbtitle=new JLabel("Title");
		lbissuedt=new JLabel("Issue Date");
		lbissue=new JLabel("Issue Time");
		lbreturn=new JLabel("Return Time");
		
		txid=new JTextField(SignInForm.Id+"");
		txid.setEnabled(false);
		txtitle=new JTextField(10);
		txtitle.setEditable(false);
		txissuedt=new JTextField(10);
		txissue=new JTextField(10);
		txreturn=new JTextField(10);
		
		java.util.Date dt=new java.util.Date();
		DateFormat sdf= SimpleDateFormat.getDateInstance();
		
		txissuedt.setText(sdf.format(dt)+"");
		sdf=SimpleDateFormat.getTimeInstance(DateFormat.MEDIUM);
		txissue.setText(sdf.format(dt)+"");
		txreturn.setText(sdf.format(dt)+"");
		btsub=new JButton("SUBMIT");
			jcbid =new JComboBox();
		jcbid.addItem("select Book Id");
		try{
			
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				
				stmt.execute("use LibraryDB");
					
				PreparedStatement pstmt=con.prepareStatement("select book_id from RTBFormTb");
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
		P2.add(lbtitle);
		P2.add(txtitle);
		
		P3=new JPanel();
		P3.setLayout(new GridLayout(4,2));
		P3.add(lbissuedt);
		P3.add(txissuedt);
		P3.add(lbissue);
		P3.add(txissue);
		P3.add(lbreturn);
		P3.add(txreturn);
		P3.add(btsub);
			
		PCenter=new JPanel();
		
		
		PCenter.setLayout(new GridLayout(3,1));
		PCenter.add(P1);
		PCenter.add(P2);
		PCenter.add(P3);
		setLayout(new BorderLayout());
		//PCenter.setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		
		
		btsub.addActionListener(this);
		jcbid.addItemListener(this);
		txtitle.setEnabled(false);
		txissuedt.setEditable(false);
		txissue.setEditable(false);
		
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
					stmt.executeUpdate("Create table if not exists RTBIssueTb(Id int ,book_ID int ,issue_date date,issue_time time,return_time time)");
					
					PreparedStatement pstmt=con.prepareStatement("Insert into RTBIssueTb(Id,book_ID,issue_date,issue_time,return_time)values(?,?,?,?,?)");
					pstmt.setInt(1, Integer.parseInt(txid.getText()));
					pstmt.setInt(2, id);
					
				/*	String dt=txissue.getText();
					String yy=dt.substring(0, 4);
					String mm=dt.substring(5, 7);
					String dd=dt.substring(8);
					java.sql.Date issuedt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					String dt1=txreturn.getText();
					mm=dt.substring(5, 7);
					yy=dt.substring(0, 4);
					 dd=dt.substring(8);
					java.sql.Date retdt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					*/
					java.util.Date dt2=new Date(txissuedt.getText());
					java.sql.Date issuedt=new java.sql.Date(dt2.getDate());//dt1.getYear(), dt1.getMonth(),dt1.getDate());
					
					java.util.Calendar cl=Calendar.getInstance();
					
					String issueTime=txissue.getText();
					int hh=Integer.parseInt(issueTime.substring(0, 2));
					int mm=Integer.parseInt(issueTime.substring(3, 5));
					int ss=Integer.parseInt(issueTime.substring(6, 8));
					java.sql.Time issuetime=new java.sql.Time(hh,mm,ss);
					
					String returnTime=txreturn.getText();
					int hh1=Integer.parseInt(returnTime.substring(0, 2));
					int mm1=Integer.parseInt(returnTime.substring(3, 5));
					int ss1=Integer.parseInt(returnTime.substring(6, 8));
					
					java.sql.Time rettime=new java.sql.Time(hh1,mm1,ss1);//dt1.getYear(), dt1.getMonth(),dt1.getDate());
					
					pstmt.setDate(3, issuedt);
					pstmt.setTime(4, issuetime);
					pstmt.setTime(5, rettime);		
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
		
			PreparedStatement pstmt=con.prepareStatement("select title from RTBFormTb where Book_ID =? ");
			pstmt.setInt(1, id);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next())
			{
				txtitle.setText(rs.getString(1));
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

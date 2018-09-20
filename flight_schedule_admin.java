
import java.awt.FlowLayout;

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
import javax.swing.JTextField;


public class flight_schedule_admin extends JApplet implements ActionListener,ItemListener {
	JComboBox cr,crday;
	JTextField ttime;
	JButton btu,btupdte,btdelete;
	public flight_schedule_admin()
	{	
		crday=new JComboBox();
		crday.addItem("Select Day");
		crday.addItem("Sunday");
		crday.addItem("Monday");
		crday.addItem("Tuesday");
		crday.addItem("Wednesday");
		crday.addItem("Thursday");
		crday.addItem("Friday");
		crday.addItem("Saturday");
		ttime=new JTextField(10);
		btu=new JButton("Insert");
		btupdte=new JButton("Update");
		btdelete=new JButton("Delete");
		cr=new JComboBox();
		cr.addItem("Flightno");
		cr.addItemListener(this);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stmt=con.createStatement();
			stmt.execute("use flightDB");
			ResultSet rs=stmt.executeQuery("select flightno from flight_master");
			
			while(rs.next())
			{
				cr.addItem(rs.getString(1));
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
	setLayout(new FlowLayout());
		add(cr);
		add(crday);
		add(ttime);
		add(btu);
		add(btupdte);
		add(btdelete);
		btu.addActionListener(this);
		btupdte.addActionListener(this);
		btdelete.addActionListener(this);
	
	}
	public void actionPerformed(ActionEvent ae) {
		Object src=ae.getSource();
		if(src==btu){
			if(cr.getSelectedIndex()==0)
				return;
			
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				stmt.execute("use flightDB");
				stmt.executeUpdate("create table if not exists flightscheduleTB(flightno varchar(255),day varchar(20),time varchar(100))");
				PreparedStatement pstmt=con.prepareStatement("insert into flightscheduleTB values(?,?,?)");
				
				pstmt.setString(1,cr.getSelectedItem().toString());
				pstmt.setString(2, crday.getSelectedItem().toString());
				pstmt.setString(3, ttime.getText());
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
		if(src==btupdte)
		{
			
				
				

				 String flightno=(String)cr.getSelectedItem().toString();
				 
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					
					Statement stmt=con.createStatement();
					stmt.executeUpdate("use flightDB");
					PreparedStatement pstmt=con.prepareStatement("update flightscheduleTB set day=?,time=? where flightno=?");
					pstmt.setString(1,cr.getSelectedItem().toString());
					pstmt.setString(2, ttime.getText());
					pstmt.setString(3,flightno);
					pstmt.executeUpdate();
					
					con.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}}
		
		}
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{	

		 String flightno=(String)cr.getSelectedItem().toString();

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt=con.createStatement();
			stmt.executeUpdate("use flightDB");
			
			PreparedStatement pstmt=con.prepareStatement("select * from flightscheduleTB where flightno=?");
			pstmt.setString(1, flightno);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{	ttime.setText(rs.getString("time"));
				crday.addItem(rs.getString("day"));
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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MagazineIssue extends JPanel implements ActionListener,ItemListener
{
	JLabel lbuserid,lbcategory,lbmagid,lbtitle,lbauthor,lbissue,lbreturn,lbprice;
	JTextField txuserid,txtitle,txauthor,txissue,txreturn,txprice;
	JComboBox jcategory,jmagid;
	JButton btsub;
	JPanel P1,P2,P3,P4,P5,PCenter;
	
	
	public MagazineIssue()
	{
		lbuserid=new JLabel(" User ID");
		lbcategory=new JLabel("Category");
		lbmagid=new JLabel("Mag_id");
		lbtitle=new JLabel("Title");
		lbauthor=new JLabel("Author");
		lbissue=new JLabel("Issue Date");
		lbreturn=new JLabel("Return Date");
		lbprice=new JLabel("Price");
		
		txuserid=new JTextField(SignInForm.Id+"");
		txuserid.setEnabled(false);
		
		txtitle=new JTextField(10);
		txauthor=new JTextField(10);
		txauthor=new JTextField(10);
		txissue=new JTextField(10);
		txreturn=new JTextField(10);
		txprice=new JTextField(10);
		
		jcategory=new JComboBox();
		jcategory.addItem("select Category");
		jcategory.addItem("Food");
		jcategory.addItem("Cooking");
		jcategory.addItem("health");
		jcategory.addItem("Kids");
		jcategory.addItem("business");
		
		jmagid=new JComboBox();
		jmagid.addItem("select magzine id");
		
		btsub=new JButton("submit");
		
		P1=new JPanel();
		P1.add(lbuserid);
		P1.add(txuserid);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(2,2));
		P2.add(lbcategory);
		P2.add(jcategory);
		P2.add(lbmagid);
		P2.add(jmagid);
		
		P3=new JPanel();
		P3.setLayout(new GridLayout(6,2));
		P3.add(lbtitle);
		P3.add(txtitle);
		P3.add(lbauthor);
		P3.add(txauthor);
		P3.add(lbissue);
		P3.add(txissue);
		P3.add(lbreturn);
		P3.add(txreturn);
		P3.add(lbprice);
		P3.add(txprice);
		
		P4=new JPanel();
		P4.add(btsub);
		
		PCenter=new JPanel();
		
		PCenter.setLayout(new GridLayout(4,1));
		PCenter.add(P1);
		PCenter.add(P2);
		PCenter.add(P3);
		PCenter.add(P4);
		setLayout(new BorderLayout());
		//PCenter.setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		
		btsub.addActionListener(this);
		jcategory.addItemListener(this);
		jmagid.addItemListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object src= ae.getSource();
		if(src==btsub)
		{
			if(jmagid.getSelectedIndex()!=0)
			{
				int id= Integer.parseInt(jmagid.getSelectedItem().toString());
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				try
				{
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists LibraryDB");
					stmt.execute("use LibraryDB");
					stmt.executeUpdate("Create table if not exists MagazineIssueTb(Id int ,book_ID int ,issue_date date,return_date date)");
					
					PreparedStatement pstmt=con.prepareStatement("Insert into MagazineIssueTb(Id,book_ID,issue_date,return_date)values(?,?,?,?)");
					pstmt.setInt(1, Integer.parseInt(txuserid.getText()));
					pstmt.setString(2, jmagid.getSelectedItem().toString());
					
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
		Object src=e.getSource();
		if(src==jcategory && jcategory.getSelectedIndex()!=0 && e.getStateChange()==ItemEvent.SELECTED)
		{
		jmagid.removeAllItems();
		jmagid.addItem("Select Magazine id");
		
			String category= jcategory.getSelectedItem().toString();
					
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			
			stmt.execute("use LibraryDB");
			PreparedStatement pstmt=con.prepareStatement("Select book_ID from MagzineSecTb where category=?");
			pstmt.setString(1, category);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next())
			{
				jmagid.addItem(rs.getInt(1)+"");
			}
		
		
		}
		
		catch (SQLException e1)
		{
			e1.printStackTrace();
		} 
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		}
		else
			
			if(src==jmagid && jmagid.getSelectedIndex()!=0 && e.getStateChange()==ItemEvent.SELECTED)
			{
				int id= Integer.parseInt(jmagid.getSelectedItem().toString());
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					
					stmt.execute("use LibraryDB");
					PreparedStatement pstmt=con.prepareStatement("select title ,author,price from MagzineSecTb where Book_id=?");
					pstmt.setInt(1, id);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
						txtitle.setText(rs.getString(1));
						txauthor.setText(rs.getString(2));
						txprice.setText(rs.getString(3));
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				} 
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
		}
	
}
}
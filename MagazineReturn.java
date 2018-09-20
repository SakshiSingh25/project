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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MagazineReturn extends JPanel implements ActionListener,ItemListener,FocusListener{
	JLabel lbuserid,lbmagid,lbcategory,lbtitle,lbreturndt,lbissuedt,lbcurretdt,lbdelay,lbfine;
	JTextField txuserid,txtitle,txreturndt,txissuedt,txcurretdt,txdelay,txfine;
	JComboBox jmagid,jcategory;
	JButton btsub;
	JPanel P1,P2,P3,P4,P5,PCenter;
	 
	public MagazineReturn()
	{
		lbuserid=new JLabel("user id");
		lbmagid=new JLabel("magzine id");
		lbcategory=new JLabel("Category");
		lbtitle=new JLabel("Title");
		lbissuedt=new JLabel("Issue Date");
		lbreturndt=new JLabel("Return Date");
		lbcurretdt=new JLabel("Current Date");
		lbdelay=new JLabel("no. of delays");
		lbfine=new JLabel("Fine");
		
		txuserid=new JTextField(SignInForm.Id+"");
		txuserid.setEnabled(false);
		
		txtitle=new JTextField(10);
		txissuedt=new JTextField(10);
		txreturndt=new JTextField(10);
		java.util.Date dt=new java.util.Date();
		DateFormat sdf= SimpleDateFormat.getDateInstance();
		txcurretdt=new JTextField(sdf.format(dt)+"");
		
		txdelay=new JTextField(10);
		txfine=new JTextField(10);
		
		jcategory=new JComboBox();
		jmagid=new JComboBox();
	    jmagid.addItem("select Book Id");
		try{
			
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				
				stmt.execute("use LibraryDB");
					
				PreparedStatement pstmt=con.prepareStatement("select book_id from MagzineSecTb");
				ResultSet rs= pstmt.executeQuery();
				while(rs.next())
				{
					jmagid.addItem(rs.getInt(1)+"");
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
		
		
		btsub=new JButton("SUBMIT");
				
		P1=new JPanel();
		P1.add(lbuserid);
		P1.add(txuserid);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(2,2));
		P2.add(lbmagid);
		P2.add(jmagid);
		P2.add(lbcategory);
		P2.add(jcategory);
		
		P3=new JPanel();
		P3.setLayout(new GridLayout(4,1));
		P3.add(lbtitle);
		P3.add(txtitle);
		P3.add(lbissuedt);
		P3.add(txissuedt);
		P3.add(lbreturndt);
		P3.add(txreturndt);
		P3.add(lbcurretdt);
		P3.add(txcurretdt);
		
		P4=new JPanel();
		P4.setLayout(new GridLayout(1,4));
		P4.add(lbdelay);
		P4.add(txdelay);
		P4.add(lbfine);
		P4.add(txfine);
		
		P5=new JPanel();
		P5.add(btsub);
		
		PCenter=new JPanel();
		
		
		PCenter.setLayout(new GridLayout(5,1));
		PCenter.add(P1);
		PCenter.add(P2);
		PCenter.add(P3);
		PCenter.add(P4);
		PCenter.add(P5);
		setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		
		
		//txdelay.addFocusListener(this);
	jmagid.addItemListener(this)	;
		btsub.addActionListener(this);
	//	txuserid.addFocusListener(this);
		fill_Book_id();
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	void fill_Book_id()
	{
		txuserid.setText("");
		txtitle.setText("");
		txreturndt.setText("");
		txissuedt.setText("");
		txcurretdt.setText("");
		txreturndt.setText("");
		txfine.setText("");
		txdelay.setText("");
		
		jmagid.removeAllItems();
		jmagid.addItem("select magzine id");
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.execute("use LibraryDB");
			
			PreparedStatement pstmt=con.prepareStatement("select Book_ID from MagazineIssueTb where ID=?");
			pstmt.setInt(1, Integer.parseInt(txuserid.getText()));
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				jmagid.addItem(rs.getInt(1)+"");
			}
		
			con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
}

	@Override
	public void focusLost(FocusEvent fe) {
		// TODO Auto-generated method stub
		Object src=fe.getSource();
		if(src==txuserid)
		{
		jmagid.removeAllItems();
		jmagid.addItem("select magzine id");
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.execute("use LibraryDB");
			
			PreparedStatement pstmt=con.prepareStatement("select Book_ID from MagazineIssueTb where ID=?");
			pstmt.setInt(1, Integer.parseInt(txuserid.getText()));
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				jmagid.addItem(rs.getInt(1)+"");
			}
		
			con.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		}
		else
			if(src==txdelay)
			{
				txfine.setText(Integer.parseInt(txdelay.getText())*2+"");
			}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object src=ae.getSource();
		if(src==btsub)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				try
				{
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists LibraryDB");
					stmt.execute("use LibraryDB");
					stmt.executeUpdate("Create table if not exists MagazineReturnTb(Id int ,book_ID int,category varchar(255),Title varchar(255) ,issue_date date,return_date date,curr_return_date date,delay long,fine int)");
					
					PreparedStatement pstmt=con.prepareStatement("Insert into MagazineReturnTb(Id,book_ID,category,Title,issue_date,return_date,curr_return_date,delay,fine)values(?,?,?,?,?,?,?,?,?)");
					pstmt.setInt(1, Integer.parseInt(txuserid.getText()));
					pstmt.setInt(2, Integer.parseInt(jmagid.getSelectedItem().toString()));
					pstmt.setString(3,jcategory.getSelectedItem().toString());
					pstmt.setString(4, txtitle.getText());
					System.out.println(txissuedt.getText());
					
					String dt=txissuedt.getText();
					String yy=dt.substring(0, 4);
					String mm=dt.substring(5, 7);
					String dd=dt.substring(8);
					java.sql.Date issuedt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					String dt1=txreturndt.getText();
					mm=dt.substring(5, 7);
					yy=dt.substring(0, 4);
					 dd=dt.substring(8);
					java.sql.Date retdt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					String dt2=txcurretdt.getText();
					yy=dt.substring(0, 4);
					mm=dt.substring(5, 7);
					dd=dt.substring(8);
					java.sql.Date curretdt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					pstmt.setDate(5, issuedt);
					pstmt.setDate(6, retdt);
					pstmt.setDate(7, curretdt);
					
					pstmt.setInt(8, Integer.parseInt(txdelay.getText()));
					pstmt.setInt(9, Integer.parseInt(txfine.getText()));
					pstmt.executeUpdate();
					
					
					pstmt=con.prepareStatement("delete from BookIssueTb where Id=? and book_ID=?");// to delete record from book issue tb with ref to id and bookid
					pstmt.setInt(1, Integer.parseInt(txuserid.getText()));
					pstmt.setInt(2, Integer.parseInt(jmagid.getSelectedItem().toString()));
					pstmt.executeUpdate();
		
					/*pstmt.executeUpdate("create table if not exits BookTransitionTb(id int, book_id int, issue_date date, curr_return_date date)");
					pstmt =con.prepareStatement("select id,book_id,issue_date,curr_return_date from BookReturntb where id=? ");
					pstmt.setInt(1,id);
					*/
					
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



		
		


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object src= e.getSource();
		if(src==jmagid && jmagid.getSelectedIndex()!=0 && e.getStateChange()==ItemEvent.SELECTED)
		{
			int id= Integer.parseInt(jmagid.getSelectedItem().toString());
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				
				stmt.execute("use LibraryDB");
				PreparedStatement pstmt=con.prepareStatement("select category,title,author from MagzineSecTb where Book_id=?");
				pstmt.setInt(1, id);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					txtitle.setText(rs.toString());
					
					//jcategory.getSelectedItem().toString();
					jcategory.addItem(rs.getString(1)+"");
					
				}
				pstmt=con.prepareStatement("select return_date ,issue_date from MagazineIssueTb where Book_ID=? ");
				pstmt.setInt(1,id);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					txissuedt.setText(rs.getObject(2)+"");
					txreturndt.setText(rs.getDate(1)+"");
				}
				con.close();
				
				java.util.Date curDt=new java.util.Date();
				
				String dt=txreturndt.getText();
				String yy=dt.substring(0, 4);
				String mm=dt.substring(5, 7); 
				String dd=dt.substring(8);
				
				java.util.Date retDt=new java.util.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
				
				System.out.println("return date is "+retDt.toString()+"\n Current Date is "+curDt);
				
				//DateFormat sdf= SimpleDateFormat.getDateInstance();
				//System.out.print(sdf.format(retDt));
				//System.out.print(retDt);
				
				if(retDt.before(curDt))
				{
				long diff=curDt.getTime()-retDt.getTime();
				long days=diff/(1000*60*60*24);
				System.out.print(days);
				txdelay.setText(days+"");
				
				txfine.setText(days*2 +"");
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

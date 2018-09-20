import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BookReturnForm extends JPanel implements ActionListener,ItemListener,FocusListener{
	JLabel lbid,lbbid,lbtitle,lbissue,lbreturn,lbcurrent,lbdelay,lbfine,lbbookretutn;
	JTextField txid,txtitle,txissue,txreturn,txcurrent,txdelay,txfine;
	JComboBox jcbid;
	JButton btsub;	
	JPanel P1,P2,P3,PCenter,PSouth;
	
	
	public BookReturnForm()
	{
		lbbookretutn=new JLabel("BOOK RETURN FORM");
		lbid=new JLabel("Candidate ID");
		lbbid=new JLabel("Book ID");
		lbtitle=new JLabel("Title");
		lbissue=new JLabel("Issue Date");
		lbreturn=new JLabel("Return Date");
		lbcurrent=new JLabel("Current Return Date");
		lbdelay=new JLabel("Delay In Days");
		lbfine=new JLabel("Fine");
		
		txid=new JTextField(SignInForm.Id+"");
		txid.setEnabled(false);
		
		txtitle=new JTextField(10);
		txissue=new JTextField(10);
		txreturn=new JTextField(10);
		java.util.Date dt=new java.util.Date();
		DateFormat sdf= SimpleDateFormat.getDateInstance();
		txcurrent=new JTextField(sdf.format(dt)+"");
		txdelay=new JTextField(10);
		txfine=new JTextField(10);
		
		btsub=new JButton("SUBMIT");
		//btsub.setSize(2,2);
		
		jcbid =new JComboBox(); 
		
		P1=new JPanel();
		P1.add(lbbookretutn);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(6,2));
		P2.add(lbid);
		P2.add(txid);
		P2.add(lbbid);
		P2.add(jcbid);
		P2.add(lbtitle);
		P2.add(txtitle);
		P2.add(lbissue);
		P2.add(txissue);
		P2.add(lbreturn);
		P2.add(txreturn);
		P2.add(lbcurrent);
		P2.add(txcurrent);
		
		P3=new JPanel();
		P3.setLayout(new GridLayout(1,4));
		P3.add(lbdelay);
		P3.add(txdelay);
		P3.add(lbfine);
		P3.add(txfine);
			
		PCenter=new JPanel();
		
		PCenter.setLayout(new GridLayout(4,1));
		PCenter.add(P1);
		PCenter.add(P2);
		PCenter.add(P3);
		setLayout(new FlowLayout());
		PCenter.add(btsub);
		setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		
		btsub.addActionListener(this);
		jcbid.addItemListener(this);
		lbfine.setEnabled(false);
		//txid.addFocusListener(this);
		//txid.requestFocus();
		txdelay.addFocusListener(this);
		fill_Book_id();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src=e.getSource();
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
					stmt.executeUpdate("Create table if not exists BookReturnTb(Id int ,book_ID int,Title varchar(255) ,issue_date date,return_date date,curr_return_date date,delay long,fine int)");
					
					PreparedStatement pstmt=con.prepareStatement("Insert into BookReturnTb(Id,book_ID,Title,issue_date,return_date,curr_return_date,delay,fine)values(?,?,?,?,?,?,?,?)");
					pstmt.setInt(1, Integer.parseInt(txid.getText()));
					pstmt.setInt(2, Integer.parseInt(jcbid.getSelectedItem().toString()));
					pstmt.setString(3, txtitle.getText());
					//System.out.println(txissue.getText());
					
					String dt=txissue.getText();
					String yy=dt.substring(0, 4);
					String mm=dt.substring(5, 7);
					String dd=dt.substring(8);
					java.sql.Date issuedt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					String dt1=txreturn.getText();
					mm=dt.substring(5, 7);
					yy=dt.substring(0, 4);
					 dd=dt.substring(8);
					java.sql.Date retdt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					String dt2=txcurrent.getText();
					yy=dt.substring(0, 4);
					mm=dt.substring(5, 7);
					dd=dt.substring(8);
					java.sql.Date curretdt=new java.sql.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
					
					pstmt.setDate(4, issuedt);
					pstmt.setDate(5, retdt);
					pstmt.setDate(6, curretdt);
					
					pstmt.setInt(7, Integer.parseInt(txdelay.getText()));
					pstmt.setInt(8, Integer.parseInt(txfine.getText()));
					pstmt.executeUpdate();
					
					
					pstmt=con.prepareStatement("delete from BookIssueTb where Id=? and book_ID=?");// to delete record from book issue tb with ref to id and bookid
					pstmt.setInt(1, Integer.parseInt(txid.getText()));
					pstmt.setInt(2, Integer.parseInt(jcbid.getSelectedItem().toString()));
					pstmt.executeUpdate();
		
						pstmt=con.prepareStatement("create table if not exists BookTransitionTb(id int, book_id int, issue_date date, curr_return_date date)");
						pstmt.executeUpdate();
						
						pstmt=con.prepareStatement("Insert into BookTransitionTb(Id,book_ID,issue_date,return_date)values(?,?,?,?)");
						pstmt.setInt(1, Integer.parseInt(txid.getText()));
						pstmt.setInt(2, Integer.parseInt(jcbid.getSelectedItem().toString()));
						pstmt.setDate(3, issuedt);
						pstmt.setDate(4, retdt);
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
	public void itemStateChanged(ItemEvent e)
	{
		if(jcbid.getSelectedIndex()!=0 && e.getStateChange()!=ItemEvent.DESELECTED)
		{
			int id=  Integer.parseInt(jcbid.getSelectedItem().toString());
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				stmt.execute("use LibraryDB");
			
				PreparedStatement pstmt=con.prepareStatement("select title from BookFormTb where Book_ID =? ");
				pstmt.setInt(1, id);
				ResultSet rs= pstmt.executeQuery();
				while (rs.next())
				{
					txtitle.setText(rs.getString(1));
				}
				pstmt=con.prepareStatement("select return_date ,issue_date from BookIssueTb where Book_ID=? ");
				pstmt.setInt(1,id);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					txissue.setText(rs.getObject(2)+"");
					txreturn.setText(rs.getDate(1)+"");
				}
				con.close();
				
				java.util.Date curDt=new java.util.Date();
				
				String dt=txreturn.getText();
				String yy=dt.substring(0, 4);
				String mm=dt.substring(5, 7); 
				String dd=dt.substring(8);
				
				java.util.Date retDt=new java.util.Date(Integer.parseInt(yy)-1900,Integer.parseInt(mm)-1,Integer.parseInt(dd));
				
				System.out.println("return date is "+retDt.toString()+"\n Current Date is "+curDt);
				
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
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}


	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	void fill_Book_id()
	{
		txissue.setText("");
		txreturn.setText("");
		txdelay.setText("");
		txfine.setText("");
		jcbid.removeAllItems();
		jcbid.addItem("select book id");
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.execute("use LibraryDB");
			
			PreparedStatement pstmt=con.prepareStatement("select Book_ID from BookissueTb where ID=?");
			pstmt.setInt(1, Integer.parseInt(txid.getText()));
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				jcbid.addItem(rs.getInt(1)+"");
			}
			
			con.close();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Override
	public void focusLost(FocusEvent fe)
	{
		//txcurrent.setText("");
		Object src=fe.getSource();
		if(src==txid)
		{
		txissue.setText("");
		txreturn.setText("");
		txdelay.setText("");
		txfine.setText("");
		jcbid.removeAllItems();
		jcbid.addItem("select book id");
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.execute("use LibraryDB");
			
			PreparedStatement pstmt=con.prepareStatement("select Book_ID from BookissueTb where ID=?");
			pstmt.setInt(1, Integer.parseInt(txid.getText()));
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				jcbid.addItem(rs.getInt(1)+"");
			}
			
			con.close();
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		}
		else
			if(src==txdelay)
			{
				txfine.setText(Integer.parseInt(txdelay.getText())*2+"");
			}
	}
}


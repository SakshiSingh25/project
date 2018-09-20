import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightDomestic extends JPanel implements ItemListener,ActionListener
{
	JComboBox CBFrom, CBTo, CBClass, CBAdult,CBday;
	JLabel LFrom, LTo, LBookingDate, LClass, LAdult, LChildren, LInfant, LBookingDetails,lflight, LPassengerDetails, LDate, LImg1, LImg2,LImg3,LImg4, LNotes,lp,lf,lt,lc,ltime,lair;
	JTextField tp,tf,tt,tflight,ttime,tuser,tclass,tair;
	Icon img1, img2,img3,img4;
	JButton bbk,btb,btreg;
	JPanel PPanel1, PPanel2,PPanel3,PPanel4,pl,cards,price,panelr,print;
	JRadioButton ck1,ck2,ck3,ck4,ck5,ck6,ck7;
	
	
	
	String[] item4={"No Adults","1","2","3","4","5","6"};
	
	

	public FlightDomestic()
	{	panelr=new JPanel();
		panelr.setPreferredSize(new Dimension(1300,1000));
		//Container main =getContentPane();
		panelr.setLayout(new BorderLayout());
	

		PPanel3 = new JPanel(null);
		PPanel3.setPreferredSize(new Dimension(1600,200));
		img1=new ImageIcon("image.jpg");
		LImg1 = new JLabel(img1);
		LImg1.setBounds(0,0,1600,200);
		
	
		ck1=new JRadioButton();	
		ck2=new JRadioButton();	
		Image1 PPanel4 = new Image1(new FlowLayout());
		
		PPanel4.setBackground(Color.blue);
		PPanel4.setPreferredSize(new Dimension(50,50));
		img3=new ImageIcon("image2.jpg");
		LImg3 = new JLabel(img3);
		
		
		
	
		
Image1 pl = new Image1(new BorderLayout());
		
		
		
		PPanel1 = new JPanel(null);
		PPanel1.setPreferredSize(new Dimension(200,100));

		LBookingDetails = new JLabel("<html><b><font color=\"#C71585\">Booking Details</font></b></html>");
		LFrom = new JLabel("From          :");
		LTo = new JLabel("To               :");
		LBookingDate = new JLabel("Booking Date:");
		LClass = new JLabel("Category         :");

		CBFrom = new JComboBox();
		CBFrom.addItem("Source");
		
		CBTo = new JComboBox();
		CBTo.addItem("Destination");

		tflight=new JTextField(10);
		CBday = new JComboBox();
		CBday.addItem("Weekday");
		
		bbk=new JButton("Get Details");
		btreg=new JButton("Book Flight");
		LDate = new JLabel("(DD/MM/YYYY)");
		LDate.setForeground(Color.red);
		
       JLabel luser=new JLabel("Username     :");
		tuser=new JTextField(10);
		tuser.setText(UsernamePass.user);
		tclass=new JTextField(10);
		
		lflight=new JLabel("Flightno         :");
		lair=new JLabel("Airline         :");
		tair=new JTextField(10);
		
		
		LBookingDetails.setBounds(20,10,100,20);
		
		
		luser.setBounds(20,50,100,20);
		tuser.setBounds(100,50,100,20);

		LFrom.setBounds(20,100,100,20);
		CBFrom.setBounds(100,100,100,20);

		LTo.setBounds(20,150,100,20);
		CBTo.setBounds(100,150,100,20);

		LBookingDate.setBounds(14,200,100,20);
		CBday.setBounds(100,200,100,20);
		LDate.setBounds(210,200,100,20);

		LClass.setBounds(20,250,100,20);
		tclass.setBounds(100,250,100,20);
	
		lflight.setBounds(20,300,100,25);
		tflight.setBounds(100,300,100,25);
		
		lair.setBounds(20,350,100,25);
		tair.setBounds(100,350,100,25);
		
		btreg.setBounds(50,450,100,25);
		bbk.setBounds(50,400,100,25);
		
		
	
		
		
		
		
		PPanel1.add(luser);
		PPanel1.add(tuser);
		PPanel1.add(LBookingDetails);
		PPanel1.add(LFrom);
		PPanel1.add(CBFrom);
		PPanel1.add(LTo);
		PPanel1.add(CBTo);
		PPanel1.add(LBookingDate);
		PPanel1.add(btreg);
		PPanel1.add(LDate);
		PPanel1.add(LClass);
		PPanel1.add(tclass);
		PPanel1.add(lair);
		PPanel1.add(tair);
	
		PPanel1.add(lflight);
		PPanel1.add(tflight);
		PPanel1.add(CBday);
		PPanel3.add(LImg1);
		PPanel4.add(LImg3);
		
		
		
		
		
		
		PPanel1.setBackground(Color.lightGray);
		panelr.add(PPanel3,BorderLayout.NORTH);
		panelr.add(PPanel1,BorderLayout.WEST);
	

		PPanel2 = new JPanel(null);
		PPanel2.setPreferredSize(new Dimension(200,100));

		LPassengerDetails=new JLabel("<html><b><font color=\"#C71585\">PassengerDetails</font></b></html>");

		LAdult = new JLabel("Adults(12+)");

		LChildren = new JLabel("Children(2-11)");
		LInfant = new JLabel("Infants(under 2)");

		
		CBAdult = new JComboBox(item4);
		
	

		img2 = new ImageIcon("image.jpg");
		LImg2 = new JLabel(img2);
		LNotes = new JLabel("<html><body><p>NOTE: Bookings with International Flights <p> should be done only through International credit cards .</body></html>");

		LPassengerDetails.setBounds(40,10,150,30);

		LAdult.setBounds(10,50,100,20);
		
		CBAdult.setBounds(100,50,100,20);

		


		LImg2.setBounds(16,220,320,200);
		LNotes.setBounds(55,240,380,180);

		PPanel2.add(LPassengerDetails);
		PPanel2.add(LAdult);

		PPanel2.add(CBAdult);


		PPanel2.add(LNotes);
		

		PPanel2.setBackground(Color.lightGray);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stmt=con.createStatement();
			stmt.execute("use flightDB");
			ResultSet rs=stmt.executeQuery("select distinct from1 from flight_master where cat1='Domestic'");
			
			
			
			while(rs.next())
			{ 
				CBFrom.addItem(rs.getString(1));

				}
			/*PreparedStatement pstmt=con.prepareStatement("select * from flight_master where from1=?");

			String roll=CBFrom.getSelectedItem().toString();
			pstmt.setString(1, roll);
			ResultSet rs1=pstmt.executeQuery();
			
			while(rs1.next())
			{	TFBookingDate.setText(rs1.getString("to1"));
				
			}
			*/
			
			
			con.close();
			
		} catch (ClassNotFoundException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}
		catch (SQLException ee)
		{
			ee.printStackTrace();
		}
		
		panelr.add(PPanel2,BorderLayout.EAST);
	
		
		
		

		Image5 price = new Image5();
		Font F1=new Font("comic sans", Font.BOLD, 10);
		
		lp=new JLabel("Price");
		tp=new JTextField(5);
		btb=new JButton("Go Back");
		tf=new JTextField();
		tt=new JTextField();
		ttime=new JTextField();
		lf = new JLabel("From:");
		lt = new JLabel("To:");
		ltime = new JLabel("Time:");
		
		
		lf.setBounds(130,100,100, 40);
		tf.setBounds(200,100,300, 40);
		lp.setBounds(130,150,100, 40);
		tp.setBounds(200,150,300, 40);
		lt.setBounds(130,200,100, 40);
		tt.setBounds(200,200,300, 40);
		ltime.setBounds(130,250,100, 40);
		ttime.setBounds(200,250,300, 40);
	
	
		

        price.add(lf);
      
        price.add(tf);
        price.add(lp);
        price.add(tp);
        price.add(lt);
        price.add(tt);
        price.add(ltime);
        price.add(ttime);
        
        price.setLayout(new BorderLayout());
		
		setSize(500,500);
		setVisible(true);
		
		
		btreg.addActionListener(this);
		bbk.addActionListener(this);
		btreg.addActionListener(this);
	    CBTo.addItemListener(this);
	    CBAdult.addItemListener(this);
	    CBFrom.addItemListener(this);
	    CBday.addItemListener(this);
	    cards = new JPanel(new CardLayout());
	            cards.add(PPanel4, "MyPanel1");
	            cards.add(pl, "MyPanel2");
	            cards.add(price, "MyPanel3");
	            panelr.add(cards,BorderLayout.CENTER);
	            pl.setLayout(new BorderLayout());
	           panelr.add(cards);

	           
	           print=new JPanel();
	           print.setPreferredSize(new Dimension(200,100));
	            add(panelr);
	            
	           
	}



	@Override
	public void itemStateChanged(ItemEvent e)
	{
		Object src=e.getSource();
		if(e.getStateChange()==ItemEvent.DESELECTED)
				return;
		
		
		
		String s="bkn";
		String delhi="Delhi";
		
		if(s.equals(CBFrom.getSelectedItem().toString()))
		{
			ck1.setSelected(true);
		}
		if(delhi.equals(CBFrom.getSelectedItem().toString()))
		{
			ck2.setSelected(true);
		}
		
		
		
		
		
			if(src==CBFrom)
			{
		
			String flightno=CBFrom.getSelectedItem().toString();
			if(CBTo.getItemCount()>0)
				CBTo.removeAllItems();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				stmt.execute("use flightDB");
			
				PreparedStatement pstmt=con.prepareStatement("select * from flight_master where from1=? and cat1='Domestic'");
				
				pstmt.setString(1, flightno);
				ResultSet rs1=pstmt.executeQuery();
				
				while(rs1.next())
				{
				
				CBTo.addItem(rs1.getString("to1"));
				
				}
			
				
				/*PreparedStatement pstmt1=con.prepareStatement("select flightno from flight_master where from1=? and to1=?");
				pstmt.setString(1, tofligno);
				ResultSet rs2=pstmt1.executeQuery();
				while(rs2.next())
				{
					tflight.setText(rs2.getString("flightno"));
				}*/
				con.close();
			}
				catch (ClassNotFoundException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			catch (SQLException ee)
			{
				ee.printStackTrace();
			}}
			
			
			
			
			else if(src==CBTo)
			{

				String flightno=CBFrom.getSelectedItem().toString();
				String flightto=CBTo.getSelectedItem().toString();
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					
					Statement stmt=con.createStatement();
					stmt.execute("use flightDB");
			
					PreparedStatement pstmt=con.prepareStatement("select * from flight_master where from1=? and to1=? and cat1='Domestic'");
					pstmt.setString(1,flightno );
					pstmt.setString(2,flightto );
				
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
						tflight.setText(rs.getString("flightno"));
						
						tf.setText(rs.getString("from1"));
						tt.setText(rs.getString("to1"));
						tclass.setText(rs.getString("cat1"));
						tair.setText(rs.getString("air1"));
						
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
				}}
			
			
			
			
			if(src==CBAdult)
			{	CardLayout c = (CardLayout)(cards.getLayout());
				c.show(cards, "MyPanel3");
				String flightno=CBFrom.getSelectedItem().toString();
				String flightto=CBTo.getSelectedItem().toString();
				String price=CBAdult.getSelectedItem().toString();
				int p=Integer.parseInt(price);
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					
					Statement stmt=con.createStatement();
					stmt.execute("use flightDB");
			
					PreparedStatement pstmt=con.prepareStatement("select * from flight_master where from1=? and to1=?");
					pstmt.setString(1,flightno );
					pstmt.setString(2,flightto );
				
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{

						tp.setText(rs.getInt("price")*p+"");
		
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
				}}
			
			
			
			
			
			
String day=tflight.getText();

if(src==CBTo){
if(CBday.getItemCount()>0)
	CBday.removeAllItems();	
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					
					Statement stmt=con.createStatement();
					stmt.execute("use flightDB");
			
					PreparedStatement pstmt=con.prepareStatement("select * from flightscheduleTB where flightno=?");
					pstmt.setString(1,day );
					
				
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
						CBday.addItem(rs.getString("day"));
						ttime.setText(rs.getString("time"));
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

	if(src==CBday)
	{String time=CBday.getSelectedItem().toString();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stmt=con.createStatement();
			stmt.execute("use flightDB");
	
			PreparedStatement pstmt=con.prepareStatement("select * from flightscheduleTB where day=?");
			pstmt.setString(1,time );
			
		
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				
				ttime.setText(rs.getString("time"));
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



		
				
	}
			
			
			
		
		
	public static void main(String[] args) {
		
new FlightDomestic();

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		
		
		if(src==btreg)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				Statement stmt=con.createStatement();
				stmt.executeUpdate("create database if not exists flightdb");
				stmt.execute("use flightDB");
			stmt.executeUpdate("create table if not exists printinfo(flightno varchar(255),from1 varchar(100),price int,to1 varchar(100),time varchar(100),day varchar(100),username varchar(100),cat1 varchar(100),air1 varchar(100))");
			
				PreparedStatement pstmt=con.prepareStatement("insert into printinfo values(?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1,tflight.getText());
				pstmt.setString(2, tf.getText());
				pstmt.setInt(3,Integer.parseInt(tp.getText()));
				pstmt.setString(4, tt.getText());
				pstmt.setString(5, ttime.getText());
				pstmt.setString(6,CBday.getSelectedItem().toString());
				pstmt.setString(7, tuser.getText());
				pstmt.setString(8, tclass.getText());
				pstmt.setString(9, tair.getText());
				
				
				pstmt.executeUpdate();
				
				con.close();
				
			} catch (ClassNotFoundException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			catch (SQLException ee)
			{
				ee.printStackTrace();
			
		}
			remove(panelr);	
			panelr=new Details();
			add(panelr);
				validate();
		
		
	}
}}
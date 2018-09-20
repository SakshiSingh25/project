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

public class Details extends JPanel implements ActionListener {
	JPanel m;
	JLabel lname,lage,laddress,lreg,lmail,lmob,lusername,lpass;
	JTextField tname,tage,taddress,tmail,tmob,tusername,tpass;
	JComboBox gender;
	JButton btr,btbook,btdetails;
	
	public Details()
	{	m=new JPanel();
		setLayout(new BorderLayout());
		m.setPreferredSize(new Dimension(1300,1000));
		Image1 Panel1 = new Image1(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		lname=new JLabel("Name");
		lreg=new JLabel("Details");
		lreg.setForeground(Color.white);
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
		tusername.setText(UsernamePass.user);
		tpass=new JTextField();
		btr=new JButton("Update");
		btdetails=new JButton("Get Details");
		btbook=new JButton("Print Flight");
		Font F1=new Font("comic sans", Font.BOLD, 70);
		
		lreg.setFont(F1);
		lreg.setBounds(300,0,1000,70);
		
		lusername.setBounds(130,100,100, 40);
		tusername.setBounds(200,100,300, 40);
		
		btdetails.setBounds(500,100,100, 40);
		
		
		lname.setBounds(130,150,100, 40);
		tname.setBounds(200,150,300, 40);
		lage.setBounds(130,200,100, 40);
		tage.setBounds(200,200,300, 40);
		laddress.setBounds(130,250,100, 40);
		taddress.setBounds(200,250,300, 40);
		lmail.setBounds(130,300,100, 40);
		tmail.setBounds(200,300,300, 40);
		lmob.setBounds(130,350,100, 40);
		tmob.setBounds(200,350,300, 40);
		
		
		
		btr.setBounds(200,400,300, 40);
		btbook.setBounds(200,450,300, 40);
		
		
		
		String[] sItem3={"Male","Female"};
		gender = new JComboBox(sItem3);
		gender.setBounds(500,150,100,20);
		
         
	

        Panel1.add(lreg);
        Panel1.add(lusername);
        Panel1.add(tusername);
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
       
        Panel1.add(lpass);
        Panel1.add(tpass);
        Panel1.add(btr);
        Panel1.add(btbook);
        Panel1.add(btdetails);
        
        
        

        panel.setPreferredSize(new Dimension(1300,1000));

	    Panel1.add(panel, BorderLayout.CENTER);

	    btr.addActionListener(this);
	    btdetails.addActionListener(this);
	    btbook.addActionListener(this);
		m.add(Panel1);
		add(m);

		setSize(800, 600);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent f) {
		Object src=f.getSource();
		if(src==btr){btr.setBackground(Color.red);
		{
			
			

			 String flightno=tusername.getText();
			 
			 try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					
					Statement stmt=con.createStatement();
					stmt.executeUpdate("use flightDB");
					PreparedStatement pstmt=con.prepareStatement("update register set name=?,age=?,address=?,mobile=?,mail=? where user=?");
					pstmt.setString(1, tname.getText());
					pstmt.setInt(2,Integer.parseInt(tage.getText()));
					pstmt.setString(3, taddress.getText());
					pstmt.setInt(4,Integer.parseInt(tmob.getText()));
					pstmt.setString(5, tmail.getText());
					pstmt.setString(6,flightno);
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
		
		}
		if(src==btdetails)
		{
			
			

			 String flightno=tusername.getText();
			
			
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt=con.createStatement();
				stmt.executeUpdate("use flightDB");
				
				PreparedStatement pstmt=con.prepareStatement("select * from register where user=?");
				pstmt.setString(1, flightno);
				
				ResultSet rs=pstmt.executeQuery();
				
				while(rs.next())
				{	tname.setText(rs.getString("name"));
					tage.setText(rs.getInt("age")+"");
					taddress.setText(rs.getString("address"));
					tmob.setText(rs.getInt("mobile")+"");
					tmail.setText(rs.getString("mail"));
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
		
		if(src==btbook)
		{
			remove(m);	
			m=new printdetails();
			add(m);
				validate();
		}
		
	}
	public static void main(String[] args) {
		
		new Details();

			}

	

}

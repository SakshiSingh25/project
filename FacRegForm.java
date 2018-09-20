import java.awt.BorderLayout;
import java.awt.FlowLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FacRegForm extends JPanel implements ActionListener{
	JLabel lbreg,lbid,lbpass,lbdept;
	JButton btsub;
	JPasswordField txpass;
	JTextField txid,txdept;
	JPanel P1,P2,P3,PCenter,Psouth;
	
	public FacRegForm() 
	{
		lbreg=new JLabel("FACULTY   REGISTRATION   FORM");
		lbid=new JLabel("Faculty_ID");
		lbpass=new JLabel("Password");
		lbdept=new JLabel("Department");
				
		txid=new JTextField(10);
		txpass=new JPasswordField(10);
		txdept=new JTextField(10);
		
		btsub= new JButton("SUBMIT");
		
		P1=new JPanel();
		P1.add(lbreg);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(4,2));
		P2.add(lbid);
		P2.add(txid);
		P2.add(lbpass);
		P2.add(txpass);
		P2.add(lbdept);
		P2.add(txdept);
		P3=new JPanel();
		P3.add(btsub);
		 
		Psouth=new JPanel();
		Psouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		PCenter=new JPanel();
		PCenter.setLayout(new GridLayout(3,1));
		PCenter.add(P1);
		PCenter.add(P2);		
		PCenter.add(P3);
		
		setLayout(new BorderLayout());
		//PCenter.setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		add(Psouth, BorderLayout.SOUTH);
		
			btsub.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src=e.getSource();
		if(src==btsub)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists LibraryDB");
					stmt.execute("use LibraryDB");//to open database
					stmt.executeUpdate("create table if not exists FacregTb(Fac_ID int,Password varchar(20),dept varchar(18),Primary Key(Fac_ID))");
					PreparedStatement pstmt=con.prepareStatement("Select Count(*) from FacregTb where Fac_id=?");
					int id=Integer.parseInt(txid.getText());
					pstmt.setInt(1, id);
					ResultSet rs=pstmt.executeQuery();
					int c=0;
					while(rs.next())
						c=rs.getInt(1);
				
					if(c==1)
					{
						JOptionPane.showMessageDialog(null, "already record with same id..");
						return;
					}
					pstmt=con.prepareStatement("Insert into FacregTb(Fac_ID,Password,dept)values(?,?,?)");
					pstmt.setInt(1,Integer.parseInt(txid.getText()));
					pstmt.setString(2,txpass.getText());
					pstmt.setString(3,txdept.getText());
					
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Please Sign IN");
					con.close();
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
	}

}

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


public class StuRegForm extends JPanel implements ActionListener{
	JLabel lbreg,lbid,lbpass,lbsem,lbbranch;
	JButton btsub;
	JPasswordField txpass;
	JTextField txid,txsem,txbranch;
	JPanel P1,P2,P3,PCenter,PNorth,PSouth;
	
	public StuRegForm()
	{
		lbreg=new JLabel("STUDENT REGISTRATION FORM");
		lbid=new JLabel("Student_ID");
		lbpass=new JLabel("Password");
		lbsem=new JLabel("Semester");
		lbbranch=new JLabel("Branch");
		
		txid=new JTextField(10);
		txpass=new JPasswordField(10);
		txsem=new JTextField(10);
		txbranch=new JTextField(10);
		
		btsub= new JButton("SUBMIT");
		
		P1=new JPanel();
		P1.add(lbreg);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(4,2));
		P2.add(lbid);
		P2.add(txid);
		P2.add(lbpass);
		P2.add(txpass);
		P2.add(lbsem);
		P2.add(txsem);
		P2.add(lbbranch);
		P2.add(txbranch);
		
		P3=new JPanel();
		P3.add(btsub);
		
		PSouth=new JPanel();
		PSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		PCenter=new JPanel();
		PCenter.setLayout(new GridLayout(3,1));
		PCenter.add(P1);
		PCenter.add(P2);		
		PCenter.add(P3);
		
		setLayout(new BorderLayout());
		//PCenter.setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		add(PSouth, BorderLayout.SOUTH);
		
		btsub.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
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
					stmt.executeUpdate("create table if not exists SturegTb(Stu_ID int,Password varchar(20),semester int,branch varchar(18),Primary Key(Stu_ID))");
					PreparedStatement pstmt=con.prepareStatement("Select Count(*) from SturegTb where Stu_id=?");
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
				
					pstmt=con.prepareStatement("Insert into SturegTb(Stu_ID,Password,semester,branch)values(?,?,?,?)");
					pstmt.setInt(1,Integer.parseInt(txid.getText()));
					pstmt.setString(2,txpass.getText());
					pstmt.setInt(3,Integer.parseInt(txsem.getText()));
					pstmt.setString(4,txbranch.getText());
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

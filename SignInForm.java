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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.server.Container;


public class SignInForm extends JPanel implements ActionListener
{
	JLabel lbsign,lbsid,lbspass,lbstype;
	JTextField txsid;
	JButton btsignin,btcancel;
	JComboBox cbcombo;
	JPanel P1,P2,P3,PCenter,Psouth;
	JPasswordField txpass;
	public static int Id;
	
	public SignInForm()
	{
		lbsign=new JLabel("SIGN IN FORM");
		lbsid=new JLabel("id");
		lbspass=new JLabel("Password");
		lbstype=new JLabel("type");
		
		txsid=new JTextField(10);
		txpass=new JPasswordField(10);
		
		btsignin=new JButton("SIGN IN");
		btcancel=new JButton("CANCEL");
		
		cbcombo=new JComboBox();
		cbcombo.addItem("select...");
		cbcombo.addItem("Admin");
		cbcombo.addItem("faculty");
		cbcombo.addItem("student");
		add(cbcombo);
		
		P1=new JPanel();
		P1.add(lbsign);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(3,2));
		P2.add(lbsid);
		P2.add(txsid);
		P2.add(lbspass);
		P2.add(txpass);
		P2.add(lbstype);
		P2.add(cbcombo);

		P3=new JPanel();
		P3.add(btsignin);
		P3.add(btcancel);
		
		
		PCenter=new JPanel();
		PCenter.setLayout(new GridLayout(3,1));
		PCenter.add(P1);
		PCenter.add(P2);		
		PCenter.add(P3);
		
		setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
			
		btsignin.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src=e.getSource();
		Id=Integer.parseInt(txsid.getText());
		if(src==btsignin)
		{
			String type=cbcombo.getSelectedItem().toString();
			int id=Integer.parseInt(txsid.getText());
			String pass=txpass.getText();
	
			if(type.equalsIgnoreCase("Admin") && id==123 && pass.equals("admin"))
			{
				WelcomePage.pane.remove(WelcomePage.Pcenter);
				WelcomePage.pane.validate();
				revalidate();
				repaint();
				
				WelcomePage.Pcenter=new AdminPane();
				
				WelcomePage.Psouth=new JPanel();
				WelcomePage.Psouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
				WelcomePage.Psouth.add(WelcomePage.btlog);
				WelcomePage.pane.add(WelcomePage.Psouth,BorderLayout.SOUTH);
						
				
				
				WelcomePage.pane.add(WelcomePage.Pcenter,BorderLayout.CENTER);
				WelcomePage.pane.validate();
				repaint();
				return;
			}
			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists LibraryDB");
					stmt.execute("use LibraryDB");
					String sql=null;
					
					if(type.equalsIgnoreCase("faculty"))
						sql="Select Count(*) from facregtb where fac_id=? and password=?";
					else
						sql="Select Count(*) from sturegtb where stu_id=? and password=?";
					
					PreparedStatement pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, id);
					pstmt.setString(2, pass);
					
					ResultSet rs=pstmt.executeQuery();
					int c=0;
					
					while(rs.next())
						c=rs.getInt(1);
					if(c==1)
					{
						WelcomePage.pane.remove(WelcomePage.Pcenter);
						
						WelcomePage.Psouth=new JPanel();
						WelcomePage.Psouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
						WelcomePage.Psouth.add(WelcomePage.btlog);
						WelcomePage.pane.add(WelcomePage.Psouth,BorderLayout.SOUTH);
						
						WelcomePage.pane.validate();
				
						revalidate();
						repaint();
						
						WelcomePage.Pcenter=new UserPane();
						
						WelcomePage.pane.add(WelcomePage.Pcenter,BorderLayout.CENTER);
						WelcomePage.pane.validate();
						repaint();
						return;
					}
					else
						JOptionPane.showMessageDialog(null,"user name or password may be wrong...");
						
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

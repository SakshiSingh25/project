import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class UsernamePass extends JPanel implements ActionListener {
	JPanel panelc;
	JLabel lname,lpass,llogin;
	JTextField tname,tpass;
	JButton bts,btr,bth;
public static String user=null;	
	public UsernamePass(){
		setLayout(new BorderLayout());
	panelc=new JPanel();	
		
	Image2 Panel1 = new Image2(new BorderLayout());

	JPanel panel = new JPanel();
	panel.setOpaque(false);
	
	lname=new JLabel("UserName");
	llogin=new JLabel("<html><b><font color=\"#C71585\">Login</font></b></html>");
	lpass=new JLabel("Password");
	
	
	tname=new JTextField();
	tpass=new JTextField();
	
	
	Font F1=new Font("comic sans", Font.BOLD, 45);
	
	bts=new JButton("Sign In");
	btr=new JButton("Register");
	bth=new JButton("Flight History");
	
	llogin.setFont(F1);
	llogin.setBounds(200,0,1000,60);
	lname.setBounds(130,100,100, 40);
	tname.setBounds(200,100,300, 40);
	lpass.setBounds(130,150,100, 40);
	tpass.setBounds(200,150,300, 40);
	bts.setBounds(200,200,300, 40);
	btr.setBounds(500,200,300, 40);
	bth.setBounds(200,250,300, 40);

    Panel1.add(llogin);
    Panel1.add(lname);
    Panel1.add(tname);
    Panel1.add(lpass);
    Panel1.add(tpass);
    Panel1.add(bts);
    Panel1.add(btr);
    Panel1.add(bth);
    
    bts.addActionListener(this);
    btr.addActionListener(this);
    bth.addActionListener(this);
    

    panel.setPreferredSize(new Dimension(1300,1000));
    panelc.setPreferredSize(new Dimension(1300,1000));

    Panel1.add(panel);
    panelc.add(Panel1);


	
	add(panelc);
	
	

	setSize(500, 500);

	setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
new UsernamePass();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		String Password="user";
		String Password1="admin";
			
				if(src==bts){
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt=con.createStatement();
					stmt.executeUpdate("use flightdb");
					
					String pas= tpass.getText();
					String usr= tname.getText();
					
					PreparedStatement pstmt=con.prepareStatement("select count(*) from register where user=? and pass=? ");
					pstmt.setString(1, usr);
					pstmt.setString(2, pas);
					ResultSet rs=pstmt.executeQuery();
					int c=0;
					while(rs.next())
						c=rs.getInt(1);
					
					if(c !=0)
					{
						user=usr;
						remove(panelc);	
						panelc= new TabbedPanelflight();add(panelc);
						validate();
						
					}
					else
						JOptionPane.showMessageDialog(null,"Please register first");
					
					
					
					con.close();
					
				} catch (ClassNotFoundException ee) {
					ee.printStackTrace();
				}
				catch (SQLException ee)
				{
					ee.printStackTrace();
				}
				
			

			
			}
			if(src==btr){
				
					JOptionPane.showMessageDialog(null,"Please register first");
					remove(panelc);	
					panelc= new Registration();add(panelc);
					add(panelc);
					validate();
		}
			if(src==bth){
				
				
				remove(panelc);	
				panelc= new printdetails();add(panelc);
				add(panelc);
				validate();
	}

	}}

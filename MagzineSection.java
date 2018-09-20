import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MagzineSection extends JPanel implements ActionListener 
{

	JLabel lbmagid,lbtitle,lbauthor,lbbooktype,lbcoverpic,lbprice,lbcover;
	JTextField txmagid,txtitle,txauthor,txprice;
	JComboBox jcategory;
	JButton btsub,btbrowse;
	JPanel P1,P2,P3,P4,P5,PCenter;
	File F;
	
	public MagzineSection()
	{
		lbmagid=new JLabel("Magazine ID");
		lbtitle=new JLabel("Title");
		lbauthor=new JLabel("Author");
		lbbooktype=new JLabel("Type");
		lbcoverpic=new JLabel("Cover Page Pic");
		lbcover=new JLabel("cover");
		lbprice=new JLabel("Price");
		

		txmagid=new JTextField(10);
		txtitle=new JTextField(10);
		txauthor=new JTextField(10);
		txprice=new JTextField(10);
		
		jcategory =new JComboBox();
		jcategory.addItem("select Category");
		jcategory.addItem("Food");
		jcategory.addItem("Cooking");
		jcategory.addItem("health");
		jcategory.addItem("Kids");
		jcategory.addItem("business");
		
		btsub=new JButton("Submit");
		btbrowse=new JButton("Browse");
	
		P1=new JPanel();
		P1.add(lbmagid);
		P1.add(txmagid);
		
		P2=new JPanel();
		P2.add(lbbooktype);
		P2.add(jcategory);
		
		
		P3=new JPanel();
		P3.setLayout(new GridLayout(2,2));
		P3.add(lbtitle);
		P3.add(txtitle);
		P3.add(lbauthor);
		P3.add(txauthor);
		
		P4=new JPanel();
		P4.setLayout(new GridLayout(1,3));
		P4.add(lbcoverpic);
		P4.add(btbrowse);
		P4.add(lbcover);
		
		P5=new JPanel();
		P5.setLayout(new GridLayout(2,2));
		P5.add(lbprice);
		P5.add(txprice);
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
		
		btsub.addActionListener(this);
		//jcategory.addItemListener(this);
		btbrowse.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent AE) {
		// TODO Auto-generated method stub
		Object src=AE.getSource();
		if(src==btsub)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("create database if not exists LibraryDB");
				stmt.execute("use LibraryDB");
				stmt.executeUpdate("create table if not exists MagzineSecTb(book_ID int auto_increment,category varchar(255),Title varchar(255),Author varchar(255),coverpage longblob,Price long,Primary Key(book_id))");
				
				PreparedStatement pstmt=con.prepareStatement("Insert into  MagzineSecTb(category,Title,Author,coverpage,Price)values(?,?,?,?,?)");
				
				InputStream fis=new FileInputStream(F);
				pstmt.setBinaryStream(4,fis,(int)F.length());
				
				pstmt.setString(1, jcategory.getSelectedItem().toString());
				pstmt.setString(2,txtitle.getText());
				pstmt.setString(3,txauthor.getText());
				pstmt.setLong(5, Long.parseLong(txprice.getText()));
				
				pstmt.executeUpdate();
				
				con.close();
			
				
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			} 
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//else
			
		if(src==btbrowse)
		{
			JFileChooser jtc=new JFileChooser();
			jtc.showOpenDialog(null);
			//to get the name of selected pic
			F=jtc.getSelectedFile();
			System.out.println(F.toString());
			ImageIcon ic=new ImageIcon(F.toString());
			lbcover.setIcon(ic);
		
		}
	}
}

	
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RTBForm extends JPanel implements ActionListener 
{
	JLabel lbbid,lbtitle,lbauthor,lbcoauthor,lbeditor,lbpublisher,lbcover,lbbookcolor,lbprice,jbrowse,jchoose;
	JTextField txid,txtitle,txauthor,txcoauthor,txeditor,txpublisher,txprice;
	JButton btsub,btbrowse,btchoose;
	JPanel P1,P2,PCenter;
	File F;
	public RTBForm()
	{
		lbbid=new JLabel("Book_ID");
		lbtitle=new JLabel("Title");
		lbauthor=new JLabel("Author");
		lbcoauthor=new JLabel("Co-Author");
		lbeditor=new JLabel("Editor");
		lbpublisher=new JLabel("Publisher");
		lbcover=new JLabel("Cover PAge Pic");
		lbbookcolor =new JLabel("Book Color");
		lbprice=new JLabel("Price");

		jbrowse=new JLabel("browse");
	//	jbrowse.setBackground(Color.CYAN);
		jchoose=new JLabel("CHOOSE COLOR");
	//	jchoose.setBackground(Color.CYAN);
		
		txid=new JTextField(10);
		txtitle=new JTextField(10);
		txauthor=new JTextField(10);
		txcoauthor=new JTextField(10);
		txeditor=new JTextField(10);
		txpublisher=new JTextField(10);
		txprice=new JTextField(10);

		btsub=new JButton("SUBMIT");
		btsub.setBackground(Color.pink);
		btbrowse=new JButton("BROWSE");
		btbrowse.setBackground(Color.pink);
		btchoose=new JButton("CHOOSE COLOR");
		btchoose.setBackground(Color.pink);
		
		P1=new JPanel();
		P1.setLayout(new GridLayout(6,2));
		P1.add(lbbid);
		P1.add(txid);
		P1.add(lbtitle);
		P1.add(txtitle);
		P1.add(lbauthor);
		P1.add(txauthor);
		P1.add(lbcoauthor);
		P1.add(txcoauthor);
		P1.add(lbeditor);
		P1.add(txeditor);
		P1.add(lbpublisher);
		P1.add(txpublisher);
		
		P2=new JPanel();
		P2.setLayout(new GridLayout(3,3));		
		P2.add(lbcover);
		P2.add(btbrowse);
		P2.add(jbrowse);
		P2.add(lbbookcolor);
		P2.add(btchoose);
		P2.add(jchoose);
		P2.add(lbprice);
		P2.add(txprice);
		//setLayout(new FlowLayout());
		//add(btsub);
		
		PCenter=new JPanel();
		PCenter.setLayout(new GridLayout(3,1));
		PCenter.add(P1);
		PCenter.add(P2);		
		PCenter.add(btsub);
		setLayout(new BorderLayout());
		add(PCenter,BorderLayout.CENTER);
		btsub.addActionListener(this);
		btbrowse.addActionListener(this);
		btchoose.addActionListener(this);
		//txauthor.setBackground(new Color(-16751002));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src=e.getSource();
		if(src==btsub)
		{
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				try
				{
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists LibraryDB");
					stmt.execute("use LibraryDB");
					stmt.executeUpdate("create table if not exists rtbFormTb(book_ID int auto_increment,Title varchar(255),Author varchar(255),Co_Author varchar(255),Editor varchar(255),Publisher varchar(255),coverpage longblob,Book_Color varchar(20),Price long,Primary Key(book_id))");
						
					PreparedStatement pstmt=con.prepareStatement("Insert into rtbFormTb(Title,Author,Co_Author,Editor,Publisher,coverpage,Book_Color,Price)values(?,?,?,?,?,?,?,?)");
					pstmt.setString(1,txtitle.getText());
					pstmt.setString(2,txauthor.getText());
					pstmt.setString(3,txcoauthor.getText());
					pstmt.setString(4,txeditor.getText());
					pstmt.setString(5,txpublisher.getText());
					InputStream fis=new FileInputStream(F);
					pstmt.setBinaryStream(6,fis,(int)F.length());
					
					pstmt.setString(7,jchoose.getBackground().getRGB()+"");
					pstmt.setLong(8, Integer.parseInt(txprice.getText()));
					pstmt.executeUpdate();
					
					con.close();
				
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
			} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
	}

}
		if(src==btbrowse)
		{
			JFileChooser jtc=new JFileChooser();
			jtc.showOpenDialog(null);
			//to get the name of selected pic
			F=jtc.getSelectedFile();
			System.out.println(F.toString());
			ImageIcon ic=new ImageIcon(F.toString());
			jbrowse.setIcon(ic);
		}
		if(src==btchoose)
		{
			Color C=JColorChooser.showDialog(null,"Choose Book Cover Page Colour",Color.BLUE);
			jchoose.setForeground(C);
		}
}
}
import java.awt.Dimension;

import javax.swing.*;  
public class TabbedPanelflight extends JPanel {  
JFrame f;  
public TabbedPanelflight(){  
    
      
    JPanel p2=new FlightDomestic(); 
    
    JPanel p3=new FlightSelectionInternational();  
    JTabbedPane tp=new JTabbedPane();  
    tp.setPreferredSize(new Dimension(1300,1000));
      
    tp.add("Domestic Flight",p2);  
    tp.add("International Flight",p3);    
    add(tp);  
 
    
    setVisible(true);  
}  
}  
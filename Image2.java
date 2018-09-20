import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Image2 extends JPanel {

	public Image2(LayoutManager l) {
		super.setLayout(l);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Image image = new ImageIcon("image.jpg").getImage();


		int bitisX = getSize().width;
		int bitisY = getSize().height;

		g.drawImage(image, 0, 0, bitisX, bitisY, null);
	}

}

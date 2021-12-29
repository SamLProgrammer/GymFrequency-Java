package views;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MyButton extends JButton{

	
	public MyButton(String iconURL) {
		setIcon(new ImageIcon(iconURL));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setFont(new Font("Oswald", Font.PLAIN, 15));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setBorder(new RoundedBorder(15,"#00C6FF"));
	}
}

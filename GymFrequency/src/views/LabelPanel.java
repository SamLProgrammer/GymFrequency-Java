package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LabelPanel extends JPanel{

	public LabelPanel(String title) {
		setBackground(Color.decode("#262B2C"));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(this.getHeight()/10, 0, 0, 0));
		initComponents(title);
	}
	
	private void initComponents(String title) {
		JLabel label = new JLabel(new ImageIcon("src/img/MainDumbbell.png"));
		label.setText(title);
//		Font font = new Font("Oswald Regular", Font.BOLD,20);
		label.setFont(new Font("Oswald Regular", Font.BOLD, 25));
		label.setForeground(Color.decode("#A42626"));
		label.setVerticalTextPosition(SwingConstants.NORTH);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		add(label);
	}
	
}

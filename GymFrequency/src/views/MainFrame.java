 package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	TablePanel tablePanel;
	LabelPanel labelPanel;
	ButtonsPanel buttonsPanel;
	
	public MainFrame(ActionListener controller) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		locate();
		setBackground(Color.decode("#404040"));
		initComponents(controller);
		setVisible(true);
	}
	
	private void initComponents(ActionListener controller) {
		labelPanel = new LabelPanel("<html><p style=\"text-align: center;\">PROGRAMMING<br>GYM</br></p></html");
		buttonsPanel = new ButtonsPanel(controller);
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0,
				this.getHeight()/30, 0));
		setIconImage(new ImageIcon("src/img/MainDumbbell.png").getImage());
		tablePanel = new TablePanel();
		add(labelPanel,BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private void locate() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenDimension.getWidth()/3, (int)(2*screenDimension.getHeight()/3));
		setLocation((int)(screenDimension.getWidth()/2 - this.getWidth()/2),
				(int)(screenDimension.getHeight()/2 - this.getHeight()/2 - screenDimension.getHeight()/20));
	}
	
	public TablePanel getTablePanel() {
		return tablePanel;
	}
	
	public void switchSouthPanel(Boolean flag) {
		buttonsPanel.setFlag(flag);
//		this.repaint();
	}

}

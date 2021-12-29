package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import control.Actions;

public class ReportsDialog extends JDialog{
	
	ReportGraphicPanel graphicReportPanel;
	
	JComboBox<Integer> xDivisionsBox, yDivisionsBox;
	
	public ReportsDialog(ActionListener controller, String[] xVector, double[] yVector) {
		setSize(700, 650);
		setIconImage(new ImageIcon("src/img/MainDumbbell.png").getImage());
		locate();
		initComponents(controller, xVector, yVector);
		setVisible(true);
	}

	private void initComponents(ActionListener controller, String[] xVector, double[] yVector) {
		setUIManager();
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		RoundJButton linealGraphicBtn = new RoundJButton("#262B2C","src/img/charts.png",15);
		linealGraphicBtn.setContentAreaFilled(false);
		linealGraphicBtn.setActionCommand(Actions.LINEAL_REPORT_BUTTON.name());
		linealGraphicBtn.addActionListener(controller);
		RoundJButton barsGraphicBtn = new RoundJButton("#262B2C","src/img/Bars.png",15);
		barsGraphicBtn.setContentAreaFilled(false);
		barsGraphicBtn.setActionCommand(Actions.BARS_REPORT_BUTTON.name());
		barsGraphicBtn.addActionListener(controller);
		
		xDivisionsBox = new JComboBox<>();
		xDivisionsBox.getComponent(0).setBackground(Color.decode("#A42626"));
		yDivisionsBox = new JComboBox<>();
		yDivisionsBox.getComponent(0).setBackground(Color.decode("#A42626"));
		for (int i = 5; i < 51; i++) {
			xDivisionsBox.addItem(i);
			yDivisionsBox.addItem(i);
		}
		yDivisionsBox.setActionCommand(Actions.Y_BOX_DIVISIONS.name());
		yDivisionsBox.addActionListener(controller);
		xDivisionsBox.setActionCommand(Actions.X_BOX_DIVISIONS.name());
		xDivisionsBox.addActionListener(controller);
		
		JButton expandUpButton = new JButton();
		expandUpButton.setFocusPainted(false);
		expandUpButton.setBackground(Color.decode("#262B2C"));
		expandUpButton.setFont(new Font("Oswald", Font.BOLD, 20));
		expandUpButton.setText("SAVE AS IMAGE...");
		expandUpButton.setForeground(Color.decode("#A42626"));
		expandUpButton.setHorizontalTextPosition(SwingConstants.CENTER);
		expandUpButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		expandUpButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		expandUpButton.setActionCommand(Actions.EXPAND_BUTTON_UP_REPORT.name());
		expandUpButton.addActionListener(controller);
		expandUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JPanel expandUpButtonPanel = new JPanel();
		expandUpButtonPanel.setLayout(new BorderLayout());
		expandUpButtonPanel.add(expandUpButton);
		expandUpButtonPanel.setBackground(Color.decode("#DED8D5"));
		expandUpButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 250, 0, 250));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		JLabel xLabel = new JLabel("X%");
		JLabel yLabel = new JLabel("Y%");
		LabelPanel labelPanel = new LabelPanel("<html><p style=\"text-align: center;\">REPORTS<br>( VISITS PER DAY )</br></p></html");
		graphicReportPanel = new ReportGraphicPanel("",
				xVector, yVector, 5, 5, false);
		JPanel xBoxPanel = new JPanel();
		xBoxPanel.setLayout(new FlowLayout());
		JPanel yBoxPanel = new JPanel();
		yBoxPanel.setLayout(new FlowLayout());
		
		xBoxPanel.add(xLabel);
		xBoxPanel.add(xDivisionsBox);
		yBoxPanel.add(yLabel);
		yBoxPanel.add(yDivisionsBox);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.insets = new Insets(0, 0, 3, 0);
		buttonsPanel.add(linealGraphicBtn,c);
		
		c.gridx = 8;
		c.gridy = 0;
		c.weightx = 0.5;
		c.insets = new Insets(0,0,3,0);
		buttonsPanel.add(barsGraphicBtn,c);
		
		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 0.5;
		c.insets = new Insets(0,0,3,0);
		buttonsPanel.add(xBoxPanel,c);
		
		c.gridx = 6;
		c.gridy = 0;
		c.gridheight = 1;
		buttonsPanel.add(yBoxPanel,c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		northPanel.add(labelPanel,c);
		
		c.gridx = 0;
		c.gridy = 2;
		northPanel.add(buttonsPanel,c);
		
		add(northPanel, BorderLayout.NORTH);
		add(graphicReportPanel);
		add(expandUpButtonPanel, BorderLayout.SOUTH);
	}
	
	private void locate() {
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenDimensions.getWidth()/2 - this.getWidth()/2), 
				(int)(screenDimensions.getHeight()/2 - this.getHeight()/2 - screenDimensions.getHeight()/20));
	}
	
	private void setUIManager() {
        UIManager.put("ComboBox.background", Color.decode("#DED8D5"));
        UIManager.put("JTextField.background", Color.decode("#A42626"));
        UIManager.put("ComboBox.selectionBackground", Color.decode("#A42626"));
//      UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.blue));
        UIManager.put("ComboBox.font", new Font("Oswald", Font.BOLD, (this.getWidth()+this.getHeight())/80));
        UIManager.put("ComboBox.foreground", Color.decode("#262B2C"));
	}
	
	public void setGraphicType(boolean bool) {
		graphicReportPanel.setGraphicType(bool);
	}
	
	public void setGraphicDivisions() {
		graphicReportPanel.setDivisions(getXBoxSelectedItem(), getYBoxSelectedItem());
	}
	
	public int getXBoxSelectedItem() {
		return (int)xDivisionsBox.getSelectedItem();
	}
	
	public int getYBoxSelectedItem() {
		return (int)yDivisionsBox.getSelectedItem();
	}
	
	public void captureReportImage() {
		graphicReportPanel.captureComponent();
	}
}

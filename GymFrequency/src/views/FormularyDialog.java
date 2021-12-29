package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import control.Actions;
import control.Controller;
import models.DayString;

public class FormularyDialog extends JDialog implements KeyListener{
	private JTextField xField;
	private JLabel daysLabel, xFieldLabel;
	private JComboBox<DayString> daysBox;
	private DCButton acceptB, cancelB;
	private LabelPanel labelPanel;
	
	public FormularyDialog(Controller controller) {
		setSize(550, 500);
		setIconImage(new ImageIcon("src/img/MainDumbbell.png").getImage());
		setUIManager();
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		initComponents(controller);
		setModal(true);
	}
	
	private void initComponents(Controller controller) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
		labelPanel = new LabelPanel("<html><p style=\"text-align: center;\">ADD<br>REGISTRY</br></p></html");
		
		daysBox = new JComboBox<DayString>(DayString.values());
		daysBox.setBackground(Color.white);
		daysBox.setFont(new Font("Oswald", Font.PLAIN, 18));
		daysBox.setLightWeightPopupEnabled(true);
		xField = new RoundJTextField(12);
		xField.setFont(new Font("Oswald", Font.PLAIN, 18));
		xField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		xField.setHorizontalAlignment(JTextField.CENTER);
		xField.addKeyListener(this);
		daysLabel = new JLabel("DAY ASSISTANCE");
		daysLabel.setHorizontalAlignment(JLabel.CENTER);
		xFieldLabel = new JLabel("GYMNAST NAME");
		xFieldLabel.setHorizontalAlignment(JLabel.CENTER);
		acceptB = new DCButton("#EF2D2D");
		acceptB.setText("DONE");
		acceptB.setActionCommand(Actions.ACCEPT_FORMULARY_BUTTON.name());
		acceptB.addActionListener(controller);
		acceptB.setForeground(Color.decode("#17CD47"));
		cancelB = new DCButton("#17CD47");
		cancelB.setText("CANCEL");
		cancelB.setActionCommand(Actions.CANCEL_FORMULARY_BUTTON.name());
		cancelB.addActionListener(controller);
		cancelB.setForeground(Color.decode("#EF2D2D"));
		
		JPanel xFieldLabelPanel = new JPanel();
		xFieldLabelPanel.setLayout(new BorderLayout());
		xFieldLabelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		JPanel xFieldPanel = new JPanel();
		xFieldPanel.setLayout(new GridLayout(2, 1));
		xFieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
		JPanel daysLabelPanel = new JPanel();
		daysLabelPanel.setLayout(new BorderLayout());
		daysLabelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		JPanel daysBoxPanel = new JPanel();
		daysBoxPanel.setLayout(new GridLayout(2,1));
		daysBoxPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 10, 100));
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100));
		
		xFieldLabelPanel.add(xFieldLabel);
		xFieldPanel.add(xFieldLabelPanel);
		xFieldPanel.add(xField);
		
		daysLabelPanel.add(daysLabel);
		daysBoxPanel.add(daysLabelPanel);
		daysBoxPanel.add(daysBox);
		
		buttonsPanel.add(acceptB);
		buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonsPanel.add(cancelB);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(labelPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(xFieldPanel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(daysBoxPanel, c);

		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 2;
		c.gridheight = 2;
		c.insets = new Insets(30, 0, 0, 0);
		c.fill = GridBagConstraints.VERTICAL;
		mainPanel.add(buttonsPanel, c);
		
		add(mainPanel);
	}
	
	private void setUIManager() {
		UIManager.put("TextField.border", new RoundedBorder(15,"#6C8BAB"));
		UIManager.put("Label.font", new Font("Oswald", Font.BOLD, 15));
		UIManager.put("Panel.background", Color.decode("#262B2C"));
		UIManager.put("Label.foreground", Color.decode("#8F969B"));
	}
	
	private void locate() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenDimension.getWidth()/2 - this.getWidth()/2),
				(int)(screenDimension.getHeight()/2 - this.getHeight()/2 - screenDimension.getHeight()/20));
	}
	
	public void cleanData() {
		locate();
		xField.setText("");
		daysBox.setSelectedIndex(0);
	}
	
	public String getTextFieldData() {
		return xField.getText();
	}
	
	public DayString getDaysBoxItem() {
		return (DayString)daysBox.getSelectedItem();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() > 47 && e.getKeyChar() < 58) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

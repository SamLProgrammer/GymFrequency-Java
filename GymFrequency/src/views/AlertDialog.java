package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Actions;

public class AlertDialog extends JDialog{
	
	String title, content;
	
	public AlertDialog(ActionListener controller, String title, String content) {
		locate();
		setLayout(new BorderLayout());
		setResizable(false);
		this.title = title;
		this.content = content;
//		initComponents(controller, title, content);
		initComponents(controller);
		setModal(true);
	}
	
	public void initComponents(ActionListener controller) {
		LabelPanel labelPanel = new LabelPanel("<html><p style=\"text-align: center;\">"
		+  divideElegantString(title) + "</p></html");
		
		JLabel alertLabel = new JLabel("<html><p style=\"text-align: center;\">" + 
		divideElegantString(content) + "</p></html", SwingConstants.CENTER);
		
		alertLabel.setFont(new Font("Oswald", Font.BOLD, 18));
		
		DCButton okButton = new DCButton("#EF2D2D");
		okButton.setText(" OK ");
		okButton.setForeground(Color.decode("#17CD47"));
		okButton.setActionCommand(Actions.OK_BUTTON_ALERT.name());
		okButton.addActionListener(controller);
		
		JPanel okButtonPanel = new JPanel();
		okButtonPanel.setLayout(new BorderLayout());
		okButtonPanel.setOpaque(false);
		okButtonPanel.add(okButton);
		okButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 150, 10, 150));
		
		add(labelPanel, BorderLayout.NORTH);
		add(alertLabel, BorderLayout.CENTER);
		add(okButtonPanel, BorderLayout.SOUTH);
	}
	
	public void locate() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(screenDimension.getWidth()/5), (int)(screenDimension.getHeight()/3));
		setLocation((int)(screenDimension.getWidth()/2 - this.getWidth()/2),
				(int)(screenDimension.getHeight()/2 - this.getHeight()/2 - screenDimension.getHeight()/20));
	}
	
	public static String divideElegantString(String string) {
		String auxString = string;
		int spacesCounter = 0;
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == 32) { 
				spacesCounter++;
			}
		}
		if(spacesCounter != 0) {
			auxString = "";
			int index = 0, length = (int)(string.length()*0.6);
			while(string.charAt(length) != 32) {
				length++;
			}
			for (int i = 0; i < length; i++) {
				auxString += string.charAt(i);
				index = i;
			}
			auxString+= "<br>";
			for (int i = index+2; i < string.length(); i++) {
				auxString+= string.charAt(i);
			}
			auxString += "</br>";
		}
		return auxString;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}

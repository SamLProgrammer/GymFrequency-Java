package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Actions;

public class ButtonsPanel extends JPanel{
	ActionListener controller;
	
	boolean flag;
	
	public ButtonsPanel(ActionListener controller) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.decode("#262B2C"));
		this.controller = controller;
		initComponents(controller);
	}
	
	private void initComponents(ActionListener controller) {	
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		JPanel globalPanel = new JPanel();
		globalPanel.setLayout(new GridLayout(2, 1,0,height/50));
		globalPanel.setOpaque(false);
		
		JPanel abovePanel = new JPanel(new GridLayout(1, 3, height/20, width/100));
		abovePanel.setBorder(BorderFactory.createEmptyBorder(height/50, width/150, 0, width/150));
		abovePanel.setOpaque(false);
		JPanel belowPanel = new JPanel();
		belowPanel.setLayout(new GridLayout(1, 2, width/20, 0));
		belowPanel.setBorder(BorderFactory.createEmptyBorder(0, width/18, 0, width/18));
		belowPanel.setOpaque(false);
		JPanel addPanelBtn = new JPanel();
		addPanelBtn.setLayout(new BorderLayout());
		addPanelBtn.setOpaque(false);
		JPanel reportPanelBtn = new JPanel();
		reportPanelBtn.setLayout(new BorderLayout());
		reportPanelBtn.setOpaque(false);
		JPanel rubbishPanelBtn = new JPanel();
		rubbishPanelBtn.setLayout(new BorderLayout());
		rubbishPanelBtn.setOpaque(false);
		JPanel savePanelBtn = new JPanel();
		savePanelBtn.setLayout(new BorderLayout());
		savePanelBtn.setOpaque(false);
		JPanel loadPanelBtn = new JPanel();
		loadPanelBtn.setLayout(new BorderLayout());
		loadPanelBtn.setOpaque(false);
		savePanelBtn.setAlignmentX(CENTER_ALIGNMENT);
		
		
		RoundJButton addRegistryBtn = new RoundJButton("#EEF3F7", "src/img/Registry1.png",12);
		addRegistryBtn.setContentAreaFilled(false);
		addRegistryBtn.setActionCommand(Actions.ADD_REGISTRY_BUTTON.name());
		addRegistryBtn.addActionListener(controller);
		addRegistryBtn.setText("<html><p style=\"text-align: center;\">ADD<br>REGISTRY</br></p></html");
		addRegistryBtn.setForeground(Color.decode("#EEF3F7"));
		
		Image img = new ImageIcon(getClass().getResource("/img/Registry1.png")).getImage();
		Image newimg = img.getScaledInstance(width / 20, height / 30, Image.SCALE_DEFAULT);
		addRegistryBtn.setIcon(new ImageIcon(newimg));
		
		RoundJButton reportBtn = new RoundJButton("#EEF3F7","src/img/Reports1.png",12);
		reportBtn.setContentAreaFilled(false);
		reportBtn.setActionCommand(Actions.REPORT_BUTTON.name());
		reportBtn.addActionListener(controller);
		reportBtn.setText("<html><p style=\"text-align: center;\">SEE<br>REPORT</br></p></html");
		reportBtn.setForeground(Color.decode("#EEF3F7"));
		
		Image img1 = new ImageIcon(getClass().getResource("/img/Reports1.png")).getImage();
		Image newimg1 = img1.getScaledInstance(width / 20, height / 30, Image.SCALE_DEFAULT);
		reportBtn.setIcon(new ImageIcon(newimg1));
		
		RoundJButton rubbishBtn = new RoundJButton("#EEF3F7","src/img/Rubbish3.png",12);
		rubbishBtn.setContentAreaFilled(false);
		rubbishBtn.setActionCommand(Actions.REMOVE_REGISTRY_BUTTON.name());
		rubbishBtn.addActionListener(controller);
		rubbishBtn.setText("REMOVE");
		rubbishBtn.setForeground(Color.decode("#EEF3F7"));
		
		Image img2 = new ImageIcon(getClass().getResource("/img/Rubbish3.png")).getImage();
		Image newimg2 = img2.getScaledInstance(width / 20, height / 30, Image.SCALE_DEFAULT);
		rubbishBtn.setIcon(new ImageIcon(newimg2));
		
		RoundJButton saveBtn = new RoundJButton("#EE3F3F7","src/img/save.png",12);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setActionCommand(Actions.SAVE_BUTTON.name());
		saveBtn.addActionListener(controller);
		saveBtn.setText("SAVE");
		saveBtn.setAlignmentX(SwingConstants.CENTER);
		saveBtn.setForeground(Color.decode("#EEF3F7"));
		
		Image img3 = new ImageIcon(getClass().getResource("/img/save.png")).getImage();
		Image newimg3 = img3.getScaledInstance(width / 20, height / 30, Image.SCALE_DEFAULT);
		saveBtn.setIcon(new ImageIcon(newimg3));
		
		RoundJButton loadBtn = new RoundJButton("#EE3F3F7","src/img/Load.png",12);
		loadBtn.setContentAreaFilled(false);
		loadBtn.setActionCommand(Actions.LOAD_BUTTON.name());
		loadBtn.addActionListener(controller);
		loadBtn.setText("LOAD");
		loadBtn.setForeground(Color.decode("#EEF3F7"));	
		
		Image img4 = new ImageIcon(getClass().getResource("/img/Load.png")).getImage();
		Image newimg4 = img4.getScaledInstance(width / 20, height / 30, Image.SCALE_DEFAULT);
		addRegistryBtn.setIcon(new ImageIcon(newimg4));
		
		JButton expandUpButton = new JButton();
		expandUpButton.setContentAreaFilled(false);
		expandUpButton.setFocusPainted(false);
		expandUpButton.setIcon(new ImageIcon("src/img/UpExpandArrow2.png"));
		expandUpButton.setHorizontalTextPosition(SwingConstants.CENTER);
		expandUpButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		expandUpButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		expandUpButton.setActionCommand(Actions.EXPAND_BUTTON_UP.name());
		expandUpButton.addActionListener(controller);
		
		JPanel expandUpButtonPanel = new JPanel();
		expandUpButtonPanel.setLayout(new BorderLayout());
		expandUpButtonPanel.setOpaque(false);
		expandUpButtonPanel.add(expandUpButton);
		
		JButton expandDownButton = new JButton();
		expandDownButton.setContentAreaFilled(false);
		expandDownButton.setFocusPainted(false);
		expandDownButton.setIcon(new ImageIcon("src/img/DownExpandArrow2.png"));
		expandDownButton.setHorizontalTextPosition(SwingConstants.CENTER);
		expandDownButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		expandDownButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		expandDownButton.setActionCommand(Actions.EXPAND_BUTTON_DOWN.name());
		expandDownButton.addActionListener(controller);
		
		JPanel expandDownButtonPanel = new JPanel();
		expandDownButtonPanel.setLayout(new BorderLayout());
		expandDownButtonPanel.setOpaque(false);
		expandDownButtonPanel.add(expandDownButton);
		expandDownButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		addPanelBtn.add(addRegistryBtn);
		reportPanelBtn.add(reportBtn);
		rubbishPanelBtn.add(rubbishBtn);
		savePanelBtn.add(saveBtn);
		loadPanelBtn.add(loadBtn);
		
		abovePanel.add(addPanelBtn);
		abovePanel.add(reportPanelBtn);
		abovePanel.add(rubbishPanelBtn);

		belowPanel.add(savePanelBtn);
		belowPanel.add(loadPanelBtn);;
		
		globalPanel.add(abovePanel);
		globalPanel.add(belowPanel);
		
		if(flag) {
		add(expandDownButtonPanel);
		add(globalPanel);
		}
		else {
			add(expandUpButtonPanel);
		}
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
		removeAll();
		initComponents(controller);
		this.updateUI();
	}
	
	public boolean isFlag() {
		return flag;
	}

}

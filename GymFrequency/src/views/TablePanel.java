package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TablePanel extends JPanel{

	JTable dataTable;
	DefaultTableModel model;
	
	public TablePanel() {
		setLayout(new BorderLayout());
		String[] headers = { "Gymnast", "Day - Assistance"};
		model = new DefaultTableModel(null, headers);
		
		dataTable = new JTable(model);
		JScrollPane scrollerTable = new JScrollPane();
		scrollerTable.setViewportView(dataTable);
		scrollerTable.getViewport().setBackground(Color.decode("#202020"));
		((DefaultTableCellRenderer)dataTable.getDefaultRenderer(Object.class)).setOpaque(false);
		
		dataTable.setRowHeight(40);
		dataTable.setForeground(Color.decode("#EEF3F7"));
		dataTable.setFont(new Font("Oswald", Font.BOLD, 20));
		dataTable.setBackground(Color.decode("#202020"));
		dataTable.setShowVerticalLines(false);
		Font f = new Font("Oswald", Font.BOLD, 21);
	      JTableHeader header = dataTable.getTableHeader();
	      TableColumnModel colMod = header.getColumnModel();
	      TableColumn tabCol;
	      for (int i = 0; i < colMod.getColumnCount(); i++) {
	    	  tabCol = colMod.getColumn(0);
	    	  tabCol.setResizable(true);
		}
	      
	      header.setFont(f);
	      header.setBackground(Color.decode("#262B2C"));
	      header.setForeground(Color.decode("#8F969B"));
		
		add(scrollerTable);
	}
	
	public void updateRowsTable(String[][] dataMatrix) {
		model.setRowCount(0);
		for (int i = 0; i < dataMatrix.length; i++) {
			String[] rowData = dataMatrix[i];
			model.addRow(rowData);
		}
	}
	
	public int getSelectedRow() {
		return dataTable.getSelectedRow();
	}
	
}

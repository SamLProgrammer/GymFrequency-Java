package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import models.DayString;
import models.Gym;
import persistence.ReadAndWrite;
import views.AlertDialog;
import views.FormularyDialog;
import views.MainFrame;
import views.ReportsDialog;

public class Controller implements ActionListener{

	private MainFrame mainFrame;
	private FormularyDialog formularyDialog;
	private ReportsDialog reportDialog;
	private Gym gym;
	private ReadAndWrite rw;
	private AlertDialog alertDialog;
	
	public Controller() {
		rw = new ReadAndWrite();
		gym = new Gym();
		mainFrame = new MainFrame(this);
		mainFrame.getTablePanel().updateRowsTable(gym.registerDataMatrix());
		formularyDialog = new FormularyDialog(this);
		initAlertDialog();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_REGISTRY_BUTTON:
			openRegistryDialog();
			break;
		case ACCEPT_FORMULARY_BUTTON:
			addRegistry();
			break;
		case CANCEL_FORMULARY_BUTTON:
			formularyDialog.setVisible(false);
			break;
		case REMOVE_REGISTRY_BUTTON:
			removeRegistry();
			break;
		case REPORT_BUTTON:
			openReportDialog();
			break;
		case BARS_REPORT_BUTTON:
			reportDialog.setGraphicType(true);
			break;
		case LINEAL_REPORT_BUTTON:
			reportDialog.setGraphicType(false);
			break;
		case X_BOX_DIVISIONS:
			reportDialog.setGraphicDivisions();
			break;
		case Y_BOX_DIVISIONS:
			reportDialog.setGraphicDivisions();
			break;
		case SAVE_BUTTON:
			saveData();
			break;
		case LOAD_BUTTON:
			loadDataFromFile();
			break;
		case EXPAND_BUTTON_UP:
			showButons();
			break;
		case EXPAND_BUTTON_DOWN:
			hideButons();
			break;
		case OK_BUTTON_ALERT:
			alertDialog.setVisible(false);
			break;
		case EXPAND_BUTTON_UP_REPORT:
			captureReportImage();
			break;
		}
	}
	
	private void addRegistry() {
		gym.addGymRegistry(gym.createGymRegistry(formularyDialog.getTextFieldData(), formularyDialog.getDaysBoxItem()));
		mainFrame.getTablePanel().updateRowsTable(gym.registerDataMatrix());
		formularyDialog.setVisible(false);
	}
	
	private void openRegistryDialog() {
		formularyDialog.cleanData();
		formularyDialog.setVisible(true);
	}
	
	private void removeRegistry() {
		if(mainFrame.getTablePanel().getSelectedRow() == -1) {
			showNonSelectedDataAlert();
		}
		else {
			gym.removeRegistry(mainFrame.getTablePanel().getSelectedRow());
			mainFrame.getTablePanel().updateRowsTable(gym.registerDataMatrix());
		}
	}
	
	private void showButons() {
		mainFrame.switchSouthPanel(true);
	}
	private void hideButons() {
		mainFrame.switchSouthPanel(false);
	}
	
	private void showNonSelectedDataAlert() {
		initAlertDialog();
		alertDialog.locate();
		alertDialog.setVisible(true);
	}
	
	private void captureReportImage() {
		reportDialog.captureReportImage();
		alertDialog = new AlertDialog(this,"¡GREAT!", "SUCCEFULLY SAVED IMAGE");
		alertDialog.setVisible(true);
	}
	
	private void openReportDialog() {
//		String[] values = {"1","2","3.5","4","5"};
//		double[] valuesd = {25,4,53,12,1};
		reportDialog = new ReportsDialog(this, DayString.stringValues(), gym.amountPerDayVector());
//		reportDialog = new ReportsDialog(this, values, valuesd);
	}
	
	private void initAlertDialog() {
		alertDialog = new AlertDialog(this,"¡UPSS!", "YOU MUST SELECT ONE REGISTRY TO DELETE");
	}
	
	private void loadedDataDialog() {
		alertDialog = new AlertDialog(this, "¡GREAT!", "LAST SAVED DATA RELOADED");
		alertDialog.setVisible(true);
	}
	
	private void saveData() {
		rw.write(gym);
		alertDialog = new AlertDialog(this, "¡GREAT!", "GYM DATA SUCCEFULLY SAVED");
		alertDialog.setVisible(true);
	}
	
	private void loadDataFromFile() {
		try {
			gym = rw.read();
			mainFrame.getTablePanel().updateRowsTable(gym.registerDataMatrix());
			loadedDataDialog();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

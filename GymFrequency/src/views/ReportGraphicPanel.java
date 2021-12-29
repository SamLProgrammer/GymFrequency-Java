package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ReportGraphicPanel extends JPanel{
	
	int hlx1,hlx2,hly1,vlx1,vly2,vly1;
	int[] xPoints, yPoints;
	String[] xVector, indicatorsVector;
	double[] yVector;
	String title = "";
	ArrayList<JButton> toolTextList;
	boolean stringIndicators, graphicType;
	int xLineDivision, yLineDivision, divisionsNumberY, divisionsNumberX;
	
	public ReportGraphicPanel (String title, String[] xVector, double[] yVector, int divisionsNumberY, int divisionsNumberX, boolean graphicType) {
		setName("Stats-Report");
		setSize(1024,600);
		setLayout(null);
		setUIManager();
		setBackground(Color.decode("#DED8D5"));
		setUpPoints();
		this.title = title;
		this.graphicType = graphicType;
		if(isStringVector(xVector)) {
			fillXpointsStringVector(xVector);
			this.divisionsNumberX = xVector.length;
			stringIndicators= true;
		}
		else {
		xPoints = xPixelPointsVector(xVector);
		this.divisionsNumberX = divisionsNumberX;
		}
		this.xVector = xVector;
		this.yVector = yVector;
		this.divisionsNumberY = divisionsNumberY;
		yPoints = yPixelPointsVector(yVector);
		initToolTextList();
	}
	
	//===================================================== PAINTERS ==================================================
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		setUpPoints();
		paintLines(g2);
		xLineDivision = calculateXLineDivision();
		yLineDivision = calculateYLineDivision();
		if(xPoints.length > 0 && yPoints.length > 0 ) {
			g2.setColor(Color.decode("#6F3E3E"));
			g2.setStroke(new BasicStroke(1));
			paintIndicatorLines(g2);
			paintBackgroundSquares(g2);
			paintIndicatorString(g2);
			paintTitle(g2);
			if(graphicType) {
				paintBars(g2);
				hideToolTips();
			}
			else {
				paintFunction(g2);
				paintPoints(g2);
				toolTipTextGenerator();
				showToolTips();
			}
		}
	}
	public void paintTitle(Graphics2D g2) {
		int widthText = g2.getFontMetrics().stringWidth(title);
		g2.drawString(title, this.getWidth()/2 - widthText/2, vly2 - this.getHeight()/16);
	}
	
	public void paintIndicatorLines(Graphics2D g2) {
		for (int i = 0; i < divisionsNumberY; i++) {
			g2.drawLine(vlx1, vly1 - yLineDivision*(i+1), vlx1 - this.getHeight()/100, vly1 - yLineDivision*(i+1));
		}
		for (int j = 0; j < xPoints.length; j++) {
			g2.drawLine(hlx1 + xLineDivision*(j+1), hly1, hlx1 + xLineDivision*(j+1), hly1 + this.getHeight()/100);
		}
		
	}
	public void paintLines(Graphics2D g) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.decode("#898890"));
		g.drawLine(hlx1, hly1, hlx2, hly1);
		g.drawLine(vlx1, vly1, vlx1, vly2);
		int[] xTrianglePointsX = {hlx2 + this.getHeight()/80,hlx2 + this.getHeight()/80,hlx2 + 2*(this.getHeight()/80)};
		int[] xTrianglePointsY = {hly1 - this.getHeight()/80, hly1 + this.getHeight()/80, hly1};
		int[] yTrianglePointsX = {vlx1 - this.getHeight()/80, vlx1 + this.getHeight()/80, vlx1};
		int[] yTrianglePointsY = {vly2 - this.getHeight()/80 , vly2 - this.getHeight()/80, vly2 - 2*this.getHeight()/80};
		g.drawLine(hlx2, hly1, hlx2 + this.getHeight()/80, hly1);
		g.drawLine(vlx1,vly2,vlx1,vly2 - this.getHeight()/80);
		g.fillPolygon(yTrianglePointsX, yTrianglePointsY, xTrianglePointsX.length);
		g.fillPolygon(xTrianglePointsX, xTrianglePointsY, yTrianglePointsX.length);
	}
	
	public void paintBackgroundSquares(Graphics2D g) {
		g.setStroke(new BasicStroke((float)0.001));
		g.setColor(Color.gray);
		for (int i = 0; i < divisionsNumberY; i++) {
			g.drawLine(vlx1, vly1 - yLineDivision*(i+1), hlx2, vly1 - yLineDivision*(i+1));
		}
		for (int j = 0; j < divisionsNumberX; j++) {			
			g.drawLine(hlx1 + xLineDivision*(j+1), hly1, hlx1 + xLineDivision*(j+1), vly2);
		}
	}
	
	public void paintIndicatorString(Graphics2D g2) {
		int textWidth =  0, textHeight = 0;
		generateIndicatorsVector(yVector);
		g2.setColor(Color.decode("#262B2C"));
		double numberY = (double)(biggestValueVector(yVector))/(double)(divisionsNumberY);
		double f = yLineDivision*(2*divisionsNumberY + 20)/100;
		g2.setFont(new Font("Oswald", Font.BOLD,
				(int)f));
		for (int i = 0; i < divisionsNumberY; i++) {
			textHeight = g2.getFontMetrics().getHeight();
			textWidth = g2.getFontMetrics().stringWidth(String.format("%.1f",numberY*(i+1)));
			g2.drawString(String.format("%.1f", numberY*(i+1)), vlx1 - textWidth - this.getHeight()/80, vly1 - yLineDivision*(i+1) + textHeight/4);
		}
		g2.setFont(new Font("Oswald", Font.BOLD, (this.getWidth()+this.getHeight())/60));
		if(stringIndicators) {
			for (int i = 0; i < divisionsNumberX; i++) {
				textHeight = g2.getFontMetrics().getHeight();
				textWidth = g2.getFontMetrics().stringWidth(xVector[i]);
				g2.drawString(xVector[i], hlx1 + xLineDivision*(i+1) - textWidth/2, hly1 + this.getHeight()/80 + textHeight);
			}
		}
		else {
			double numberX = (double)(biggestValueVector(xVector))/(double)(divisionsNumberX);
			for (int j = 0; j < divisionsNumberX; j++) {
				textHeight = g2.getFontMetrics().getHeight();
				textWidth = g2.getFontMetrics().stringWidth(String.format("%.1f",numberX*(j+1)));
				g2.drawString(String.format("%.1f",numberX*(j+1)), hlx1 + xLineDivision*(j+1) - textWidth/2, hly1 + (int)numberX + this.getHeight()/80 + textHeight);
			}
		}
	}
	
	public void paintFunction(Graphics2D g2) {
		g2.setColor(Color.decode("#A42626"));
		g2.setStroke(new BasicStroke((float)2));
		if(stringIndicators) {
			xPoints = xPixelPointsVectorString(xVector);
		}
		else {
			xPoints = xPixelPointsVector(xVector);
		}
		yPoints = yPixelPointsVector(yVector);
		for (int i = 0; i < xPoints.length; i++) {
			g2.drawPolyline(xPoints, yPoints, xPoints.length);
		}
	}
	
	public void paintBars(Graphics2D g2) {
		g2.setColor(Color.decode("#A42626"));
		if(stringIndicators) {
			xPoints = xPixelPointsVectorString(xVector);
		}
		else {
			xPoints = xPixelPointsVector(xVector);
		}
		yPoints = yPixelPointsVector(yVector);
		for (int i = 0; i < indicatorsVector.length; i++) {
			g2.fillRect(xPoints[i] - this.getWidth()/60, yPoints[i], this.getWidth()/30, vly1-yPoints[i]);
		}
	}
	
	public void paintPoints(Graphics2D g2) {
		g2.setColor(Color.decode("#A42626"));
		for (int i = 0; i < xPoints.length; i++) {
			g2.fillOval(xPoints[i] - this.getHeight()/100, yPoints[i] - this.getHeight()/100, this.getHeight()/50, this.getHeight()/50);
		}
	}
	
	public void toolTipTextGenerator() {
		for (int i = 0; i < xPoints.length; i++) {			
			toolTextList.get(i).setBounds(xPoints[i] - this.getHeight()/100, yPoints[i] - this.getHeight()/100, this.getHeight()/50, this.getHeight()/50);
			toolTextList.get(i).setToolTipText(
					"<html><p style =\"font-family:Oswald; color: #DED8D5; font-weight: 900;"
					+ " text-align: center; font-size: 14px;\">" + xVector[i] + " , " + yVector[i] + "</p></html");
		}
		for (JButton jButton : toolTextList) {
			add(jButton);
		}
	}
	
	
	//==================================================== LOGIC =======================================================
	
	
	public void fillXpointsStringVector(String[] xVector) {
		xPoints = new int[xVector.length];
		for (int i = 0; i < xVector.length; i++) {
			xPoints[i] = i;
		}
	}
	
	public boolean isStringVector(String[] vector) {
		boolean bool = false;
		for (int i = 0; i < vector.length; i++) {
			if(invalidStringNumber(vector[i]))
				bool = true;
		}
		return bool;
	}
	
	public int[] xPixelPointsVector(String[] valuesVector) {
		double[] damnVector = new double[valuesVector.length];
		for (int i = 0; i < damnVector.length; i++) {
			damnVector[i] = Double.parseDouble(valuesVector[i]);
		}
		double[] xPixelVector = new double[valuesVector.length];
		int[] resultVector = new int[valuesVector.length];
		for (int i = 0; i < valuesVector.length; i++) {
				xPixelVector[i] = hlx1 + (damnVector[i]*xLineDivision)*(divisionsNumberX/biggestValueVector(valuesVector));
		}
		for (int i = 0; i < resultVector.length; i++) {
			resultVector[i] = Integer.valueOf(beforeComaNumber(String.valueOf(xPixelVector[i])));
		}
		return resultVector;
	}
	
	public int[] xPixelPointsVectorString(String[] valuesVector) {
		int[] xPixelVector = new int[valuesVector.length];
		for (int i = 0; i < valuesVector.length; i++) {
			xPixelVector[i] = hlx1 + (i+1)*xLineDivision;
		}
		return xPixelVector;
	}
	
	public int[] yPixelPointsVector(double[] valuesVector) {
		for (int i = 0; i < valuesVector.length; i++) {
			valuesVector[i] = valuesVector[i];
		}
		double[] yPixelVector = new double[valuesVector.length];
		int[] resultVector = new int[valuesVector.length];
		for (int i = 0; i < valuesVector.length; i++) {
				yPixelVector[i] = vly1 - (valuesVector[i]*yLineDivision)*(divisionsNumberY/biggestValueVector(valuesVector));
		}
		for (int i = 0; i < resultVector.length; i++) {
			resultVector[i] = Integer.valueOf(beforeComaNumber(String.valueOf(yPixelVector[i])));
		}
		return resultVector;
	}
	
	public double biggestValueVector(String[] vector) { //probado
		float value = 0;
		for (int i = 0; i < vector.length; i++) {
			if(Float.valueOf(vector[i]) > value)
				value = Float.valueOf(vector[i]);
		}
		return value;
	}
	
	public double biggestValueVector(double[] vector) { //probado
		double value = 0;
		for (int i = 0; i < vector.length; i++) {
			if(vector[i] > value)
				value = vector[i];
		}
		return value;
	}
	
	public void setUpPoints() {
		hlx1 = this.getWidth() - 7*(this.getWidth()/8);
		hlx2 = this.getWidth() - this.getWidth()/8;
		hly1 = this.getHeight() - this.getHeight()/6;
		vlx1 = hlx1;
		vly1 = hly1;
		vly2 = this.getHeight() - 7*(this.getHeight()/8);
	}
	
	public int calculateXLineDivision() {
		return (hlx2-hlx1)/divisionsNumberX;
	}
	
	public int calculateYLineDivision() {
		return (vly1-vly2)/divisionsNumberY;
	}
	
	public void generateIndicatorsVector(double[] vector) {
		indicatorsVector = new String[vector.length];
		for (int i = 0; i < vector.length; i++) {
			indicatorsVector[i] = String.valueOf((double)((biggestValueVector(vector)/divisionsNumberY)*(i+1)));
		}
	}
	
	public double afterComaNumber(String string) { // probado
		int comaPosition = 0;
		String stringNumber = "0.";
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == 46)
				comaPosition = i+1;
		}
		for (int i = comaPosition; i < string.length(); i++) {
			stringNumber += string.charAt(i);
		}
		return Double.valueOf(stringNumber);
	}
	
	public int beforeComaNumber(String string) { //probado
		String stringNumber = "";
		int i = 0;
		while(string.charAt(i) != 46) {
			stringNumber += string.charAt(i);
			i++;
		}
		return Integer.valueOf(stringNumber);
	}
	
	public boolean dataIsFloat(String string) { //probado
		boolean bool = false;
		for (int i = 0; i < string.length() && !bool; i++) {
			if(string.charAt(i) == 46)
				bool = true;
		}
		return bool;
	}
	
	public boolean invalidStringNumber(String string) { //probado
		boolean bool = false;
		int comaCounter = 0, alienCharCounter = 0;
		for (int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == 46)
				comaCounter++;
			if((string.charAt(i) < 48 || string.charAt(i) > 57) && string.charAt(i) != 46)
				alienCharCounter++;
		}
		if((comaCounter > 1 && string.charAt(string.length()-1) != 46) || alienCharCounter > 0)
			bool = true;
		return bool;
	} 
	
	public void hideToolTips() {
		for (int i = 0; i < toolTextList.size(); i++) {
			toolTextList.get(i).setVisible(false);
		}
	}
	
	public void showToolTips() {
		for (int i = 0; i < toolTextList.size(); i++) {
			toolTextList.get(i).setVisible(true);
		}
	}
	
	public void initToolTextList() {
		toolTextList = new ArrayList<>();
		for (int i = 0; i < xVector.length; i++) {
			JButton btn = new JButton();
//			btn.setBorderPainted(false);
//			btn.setContentAreaFilled(false);
			toolTextList.add(btn);
		}
	}
	
	public void setGraphicType(boolean graphicType) {
		this.graphicType = graphicType;
		repaint();
	}
	
	public void setDivisions(int x, int y) {
		if(stringIndicators == false) {
		divisionsNumberX = x;
		}
		divisionsNumberY = y;
		repaint();
	}
	
	public void captureComponent() {
	    java.awt.Rectangle rect = this.getBounds();
	 
	    try {
	        String format = "png";
	        String fileName = this.getName() + "." + format;
	        BufferedImage captureImage =
	                new BufferedImage(rect.width, rect.height,
	                                    BufferedImage.TYPE_INT_ARGB);
	        this.paint(captureImage.getGraphics());
	 
	        ImageIO.write(captureImage, format, new java.io.File("src/files/" + fileName));
	 
	    } catch (java.io.IOException ex) {
	        System.err.println(ex);
	    }
	}
	
	private void setUIManager() {
		UIManager.put("ToolTip.background", Color.decode("#404040"));
		UIManager.put("ToolTip.foreground", Color.decode("#A42626"));
	}
}

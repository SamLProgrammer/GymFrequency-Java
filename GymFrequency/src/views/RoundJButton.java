package views;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RoundJButton extends JButton {
    private Shape shape;
    private String colorCode;
    private int radius;
    public RoundJButton(String colorCode, String iconURL, int radius) {
    	setIcon(new ImageIcon(iconURL));
    	setHorizontalTextPosition(SwingConstants.CENTER);
    	setVerticalTextPosition(SwingConstants.BOTTOM);
    	setFocusPainted(false);
    	setMargin(new Insets(0, 0, 0, 0));
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
    	this.colorCode = colorCode;
    	this.radius = radius;
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         super.paintComponent(g);
    }
    
    protected void paintBorder(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setStroke(new BasicStroke(3));
         g2.setColor(Color.decode(colorCode));
         g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         }
         return shape.contains(x, y);
    }
}
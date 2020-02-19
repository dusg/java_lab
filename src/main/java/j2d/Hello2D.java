package j2d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Hello2D {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Hello2DPanel());
        frame.setVisible(true);
        frame.setSize(800, 500);
    }

    static class Hello2DPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.blue);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            Ellipse2D ellipse2D = new Ellipse2D.Double(-100, -50, 200, 100);
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.PI / 6);
            Shape shape = transform.createTransformedShape(ellipse2D);
            g2.translate(300, 200);
            g2.scale(2, 2);
            g2.draw(shape);
            g2.drawString("Hello 2d", 0, 0);
        }
    }

}

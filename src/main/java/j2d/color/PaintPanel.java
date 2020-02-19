package j2d.color;

import j2d.Utils;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {
    public PaintPanel() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.white);
    }

    public static void main(String[] args) {
        Utils.runFrame(PaintPanel::new);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(100, 50, Color.white, 150, 50, Color.gray, true);
        g2.setPaint(gp);
        g2.fillRect(100, 40, 300, 20);

        GradientPaint paint = new GradientPaint(100, 300, Color.white, 400, 400, Color.black);
        g2.setPaint(paint);
        Font font = new Font("Serif", Font.BOLD, 144);
        g2.setFont(font);
        g2.drawString("Java", 100, 400);
    }

}

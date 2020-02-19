package j2d.color;

import j2d.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class ColorPanel extends JPanel {
    int red;
    int blue;
    int green;

    public ColorPanel() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape rc = new Ellipse2D.Double(100, 113, 200, 200);
        Shape gc = new Ellipse2D.Double(50, 200, 200, 200);
        Shape bc = new Ellipse2D.Double(150, 200, 200, 200);

        Area ra = new Area(rc);
        Area ga = new Area(gc);
        Area ba = new Area(bc);

        Area rga = new Area(rc);
        rga.intersect(ga);

        Area gba = new Area(gc);
        gba.intersect(ba);

        Area bra = new Area(bc);
        bra.intersect(ra);

        Area rgba = new Area(rga);
        rgba.intersect(ba);

        ra.subtract(rga);
        ra.subtract(bra);
        ga.subtract(rga);
        ga.subtract(gba);
        ba.subtract(bra);
        ba.subtract(gba);

        g2.setColor(new Color(red, 0,0));
        g2.fill(ra);

        g2.setColor(new Color(0, green, 0));
        g2.fill(ga);

        g2.setColor(new Color(0, 0, blue));
        g2.fill(ba);

        g2.setColor(new Color(red, green, 0));
        g2.fill(rga);

        g2.setColor(new Color(0, green, blue));
        g2.fill(gba);

        g2.setColor(new Color(red, 0, blue));
        g2.fill(bra);

        g2.setColor(new Color(red, green, blue));
        g2.fill(rgba);

        g2.setColor(Color.black);
        g2.draw(rc);
        g2.draw(gc);
        g2.draw(bc);

    }
}

package j2d;

import javax.swing.*;
import java.awt.*;

public class TreeIcon implements Icon {
    private static final int SIZE = 14;
    private boolean expanded;
    private Color color;

    public TreeIcon(boolean expanded, Color color) {
        this.expanded = expanded;
        this.color = color;
    }

    //@Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(color);
        if (expanded) {
            g2d.fillOval(x + SIZE / 4, y, SIZE / 2, SIZE);
        } else {
            g2d.fillOval(x, y + SIZE / 4, SIZE, SIZE / 2);
        }
    }

    //@Override
    public int getIconWidth() {
        return SIZE;
    }

    //@Override
    public int getIconHeight() {
        return SIZE;
    }
}

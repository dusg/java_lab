package j2d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class AreaApp extends SwingApplication {

    public static void main(String[] args) {
        new AreaApp().run();
    }
    @Override
    protected JPanel createContentPanel() {
        return new AreaPanel();
    }

    static class AreaPanel extends JPanel {
        public AreaPanel() {
            setPreferredSize(new Dimension(760, 400));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D gc = (Graphics2D) g;
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape s1 = new Ellipse2D.Double(0, 0, 100, 100);
            Shape s2 = new Ellipse2D.Double(60, 0, 100, 100);
            Area a1;
            Area a2 = new Area(s2);
            gc.translate(20, 50);
            gc.draw(s1);
            gc.draw(s2);
            gc.translate(0, 200);
            a1 = new Area(s1);
            a1.add(a2);
            gc.fill(a1);

            gc.translate(180, 0);
            a1 = new Area(s1);
            a1.intersect(a2);
            gc.fill(a1);

            gc.translate(180, 0);
            a1 = new Area(s1);
            a1.subtract(a2);
            gc.fill(a1);

            gc.translate(180, 0);
            a1 = new Area(s1);
            a1.exclusiveOr(a2);
            gc.fill(a1);
        }
    }
}

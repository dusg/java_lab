package j2d;

import com.alee.laf.WebLookAndFeel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;

public class DrawShapes extends JFrame {
    private JavaDraw2DPanel panel;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
//            try {
//                BeautyEyeLNFHelper.launchBeautyEyeLNF();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            WebLookAndFeel.install();
            DrawShapes frame = new DrawShapes();
            frame.setTitle("Spirograph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new Spirograph.SpiroPanel());
            frame.init();
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void init() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Object");
        menuBar.add(menu);
        JMenuItem mi = new JMenuItem("Rectangle");
        mi.addActionListener(e -> panel.shapeType = ShapeType.rectangle);
        menu.add(mi);

        mi = new JMenuItem("RoundRectangle");
        mi.addActionListener((e -> panel.shapeType = ShapeType.round_rectangle2d));
        menu.add(mi);

        mi = new JMenuItem("Ellipse");
        mi.addActionListener(e -> panel.shapeType = ShapeType.ellipse2d);
        menu.add(mi);

        mi = new JMenuItem("Arc");
        mi.addActionListener(e -> panel.shapeType = ShapeType.arc2d);
        menu.add(mi);

        mi = new JMenuItem("Line");
        mi.addActionListener(e -> panel.shapeType = ShapeType.line2d);
        menu.add(mi);

        mi = new JMenuItem("QuadCurve");
        mi.addActionListener(e -> panel.shapeType = ShapeType.quad_curve);
        menu.add(mi);

        mi = new JMenuItem("CubicCurve");
        mi.addActionListener(e -> panel.shapeType = ShapeType.cubic_curve);
        menu.add(mi);

        mi = new JMenuItem("Polygon");
        mi.addActionListener(e -> panel.shapeType = ShapeType.polygon);
        menu.add(mi);

        panel = new JavaDraw2DPanel();
        getContentPane().add(panel);
    }

    static enum ShapeType {
        rectangle,
        round_rectangle2d,
        ellipse2d,
        arc2d,
        line2d,
        quad_curve,
        cubic_curve,
        polygon,
        general,
        area
    }

    static class JavaDraw2DPanel extends JPanel implements MouseListener, MouseMotionListener {
        Vector<Shape> shapes = new Vector<>();
        Vector<Point> points = new Vector<>();
        public ShapeType shapeType = ShapeType.rectangle;
        private int pointIndex;
        private Point p;
        private Shape partialShape;

        public JavaDraw2DPanel() {
            super();
            setBackground(Color.white);
            setPreferredSize(new Dimension(640, 480));
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gc = (Graphics2D) g;
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            for (Shape shape : shapes) {
                gc.draw(shape);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch (shapeType) {

                case rectangle:
                case round_rectangle2d:
                case ellipse2d:
                case arc2d:
                case line2d:
                case quad_curve:
                case polygon:
                case general:
                case area:
                    points.add(e.getPoint());
                    break;
                case cubic_curve:
                    if (pointIndex == 0) {
                        points.add(e.getPoint());
                    }
                    break;
            }
            pointIndex = points.size();
            p = null;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Graphics gc = getGraphics();
            Point p1 = points.get(pointIndex - 1);
            p = e.getPoint();
            Shape shape = null;
            switch (shapeType) {

                case rectangle:
                    shape = new Rectangle(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case round_rectangle2d:
                    shape = new RoundRectangle2D.Float(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
                    break;
                case ellipse2d:
                    shape = new Ellipse2D.Float(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case arc2d:
                    shape = new Arc2D.Float(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120, Arc2D.OPEN);
                    break;
                case line2d:
                    shape = new Line2D.Float(p1.x, p1.y, p.x, p.y);
                    break;
                case quad_curve:
                    if (pointIndex > 1) {
                        Point p2 = points.get(0);
                        shape = new QuadCurve2D.Float(p2.x, p2.y, p.x, p.y, p1.x, p1.y);
                    }
                    break;
                case cubic_curve:
                    if (points.size() == 3) {
                        Point p2 = points.get(pointIndex - 2);
                        Point p3 = points.get(pointIndex - 3);
                        shape = new CubicCurve2D.Float(p2.x, p2.y, p.x, p.y, p1.x, p1.y, p3.x, p3.y);
                    } else {
                        points.add(p);
                        pointIndex++;
                    }
                    break;
                case polygon:
                    if (e.isShiftDown()) {
                        Polygon polygon = new Polygon();
                        for (Point point : points) {
                            polygon.addPoint(point.x, point.y);
                        }
                        polygon.addPoint(p.x, p.y);
                        shape = polygon;
                    }
                    break;
                case general:
                    break;
                case area:
                    break;
            }
            if (shape != null) {
                shapes.add(shape);
                points.clear();
                pointIndex = 0;
                p = null;
                repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Graphics2D gc = (Graphics2D) getGraphics();
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            gc.setXORMode(Color.white);
            Point p1 = points.lastElement();
            switch (shapeType) {

                case rectangle:
                    if (p != null) {
                        gc.drawRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    }
                    p = e.getPoint();
                    gc.drawRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case round_rectangle2d:
                    if (p != null) {
                        gc.drawRoundRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
                    }
                    p = e.getPoint();
                    gc.drawRoundRect(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 10, 10);
                    break;
                case ellipse2d:
                    if (p != null) {
                        gc.drawOval(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    }
                    p = e.getPoint();
                    gc.drawOval(p1.x, p1.y, p.x - p1.x, p.y - p1.y);
                    break;
                case arc2d:
                    if (p != null) {
                        gc.drawArc(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120);
                    }
                    p = e.getPoint();
                    gc.drawArc(p1.x, p1.y, p.x - p1.x, p.y - p1.y, 30, 120);
                    break;
                case line2d:
                case polygon:
                    if (p != null) {
                        gc.drawLine(p1.x, p1.y, p.x, p.y);
                    }
                    p = e.getPoint();
                    gc.drawLine(p1.x, p1.y, p.x, p.y);
                    break;
                case quad_curve:
                    if (pointIndex == 1) {
                        if (p != null) {
                            gc.drawLine(p1.x, p1.y, p.x, p.y);
                        }
                        p = e.getPoint();
                        gc.drawLine(p1.x, p1.y, p.x, p.y);
                    } else {
                        Point p2 = points.get(pointIndex - 2);
                        if (p != null) {
                            gc.draw(partialShape);
                        }
                        p = e.getPoint();
                        partialShape = new QuadCurve2D.Float(p2.x, p2.y, p.x, p.y, p1.x, p1.y);
                        gc.draw(partialShape);

                    }
                    break;
                case cubic_curve:
                    if (pointIndex == 1) {
                        if (p != null) {
                            gc.drawLine(p1.x, p1.y, p.x, p.y);
                        }
//                        repaint();
                        p = e.getPoint();
                        gc.drawLine(p1.x, p1.y, p.x, p.y);
                    } else if (pointIndex == 2){
                        Point p2 = points.get(pointIndex - 2);
                        if (p != null) {
                            gc.draw(partialShape);
                        }
                        p = e.getPoint();
                        partialShape = new QuadCurve2D.Float(p2.x, p2.y, p.x, p.y, p1.x, p1.y);
                        gc.draw(partialShape);
                    } else if (pointIndex == 3) {
                        Point p2 = points.get(pointIndex - 2);
                        Point p3 = points.get(pointIndex - 3);
                        if (p != null) {
                            gc.draw(partialShape);
                        }
                        p = e.getPoint();
                        partialShape = new CubicCurve2D.Float(p2.x, p2.y, p.x, p.y, p1.x, p1.y, p3.x, p3.y);
                        gc.draw(partialShape);

                    }
                    break;
                case general:
                    break;
                case area:
                    break;
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}

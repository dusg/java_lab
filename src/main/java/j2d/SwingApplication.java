package j2d;

import javax.swing.*;

abstract public class SwingApplication {
    public void run() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = createContentPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    protected abstract JPanel createContentPanel();
}

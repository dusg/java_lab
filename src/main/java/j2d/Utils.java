package j2d;

import com.alee.laf.WebLookAndFeel;

import javax.swing.*;

public class Utils {
    static public void runFrame(CreateComponent run) {
        SwingUtilities.invokeLater(() -> {
            WebLookAndFeel.install();
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(run.run());
            frame.pack();
            frame.setVisible(true);
        });
    }

    public interface CreateComponent {
        JComponent run();
    }
}

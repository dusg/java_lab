package j2d;

import com.alee.laf.WebLookAndFeel;
import com.alee.utils.CoreSwingUtils;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.*;

import org.eclipse.swt.awt.SWT_AWT;

import javax.swing.*;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
public class MainWindow extends ApplicationWindow {
	private JTextField textField;

	/**
	 * Create the application window.
	 */
	public MainWindow() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
//		container.setLayout(new GridLayout(1, false));
        container.setLayout(new FillLayout());

		Composite composite = new Composite(container, SWT.EMBEDDED);

		Frame frame = SWT_AWT.new_Frame(composite);
		try {
			UIManager.setLookAndFeel ( new WebLookAndFeel () );
			Icon expanded = new TreeIcon(true, Color.red);
			Icon collapsed = new TreeIcon(false, Color.blue);
			UIManager.put("Tree.collapsedIcon", collapsed);
			UIManager.put("Tree.expandedIcon", expanded);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Panel panel = new Panel();
		frame.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JRootPane rootPane = new JRootPane();
		panel.add(rootPane);
//		panel.add(new Form2().$$$getRootComponent$$$());
		
		JButton btnNewButton = new JButton("New button");
		rootPane.getContentPane().add(btnNewButton, BorderLayout.NORTH);
		textField = new JTextField();
		rootPane.getContentPane().add(textField, BorderLayout.SOUTH);
		rootPane.getContentPane().add(new Form2().$$$getRootComponent$$$(), BorderLayout.CENTER);
		textField.setColumns(10);
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			MainWindow window = new MainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}

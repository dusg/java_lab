/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package swt.snippets;

/*
 * Table example snippet: find a table cell from mouse down (SWT.FULL_SELECTION)
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Snippet3 {

public static void main(String[] args) {
	Display display = new Display();
	Shell shell = new Shell(display);
	shell.setText("Snippet 3");
	final Table table = new Table(shell, SWT.BORDER | SWT.V_SCROLL
			| SWT.FULL_SELECTION);
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
	final int rowCount = 64, columnCount = 4;
	for (int i = 0; i < columnCount; i++) {
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Column " + i);
	}
	for (int i = 0; i < rowCount; i++) {
		TableItem item = new TableItem(table, SWT.NONE);
		for (int j = 0; j < columnCount; j++) {
			item.setText(j, "Item " + i + "-" + j);
		}
	}
	for (int i = 0; i < columnCount; i++) {
		table.getColumn(i).pack();
	}
	Rectangle clientArea = shell.getClientArea();
	table.setLocation(clientArea.x, clientArea.y);
	Point size = table.computeSize(SWT.DEFAULT, 200);
	table.setSize(size);
	shell.pack();
	table.addListener(SWT.MouseDown, event -> {
		Point pt = new Point(event.x, event.y);
		TableItem item = table.getItem(pt);
		if (item == null)
			return;
		for (int i = 0; i < columnCount; i++) {
			Rectangle rect = item.getBounds(i);
			if (rect.contains(pt)) {
				int index = table.indexOf(item);
				System.out.println("Item " + index + "-" + i);
			}
		}
	});
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}

}

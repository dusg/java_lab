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
 * CoolBar example snippet: create a coolbar (relayout when resized)
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 *
 * @since 3.0
 */
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Snippet150 {

static int itemCount;
static CoolItem createItem(CoolBar coolBar, int count) {
	ToolBar toolBar = new ToolBar(coolBar, SWT.FLAT);
	for (int i = 0; i < count; i++) {
		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		item.setText(itemCount++ +"");
	}
	toolBar.pack();
	Point size = toolBar.getSize();
	CoolItem item = new CoolItem(coolBar, SWT.NONE);
	item.setControl(toolBar);
	Point preferred = item.computeSize(size.x, size.y);
	item.setPreferredSize(preferred);
	return item;
}

public static void main(String[] args) {
	Display display = new Display();
	final Shell shell = new Shell(display);
	shell.setText("Snippet 150");
	CoolBar coolBar = new CoolBar(shell, SWT.NONE);
	createItem(coolBar, 3);
	createItem(coolBar, 2);
	createItem(coolBar, 3);
	createItem(coolBar, 4);
	int style = SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL;
	Text text = new Text(shell, style);
	FormLayout layout = new FormLayout();
	shell.setLayout(layout);
	FormData coolData = new FormData();
	coolData.left = new FormAttachment(0);
	coolData.right = new FormAttachment(100);
	coolData.top = new FormAttachment(0);
	coolBar.setLayoutData(coolData);
	coolBar.addListener(SWT.Resize, event -> shell.layout());
	FormData textData = new FormData();
	textData.left = new FormAttachment(0);
	textData.right = new FormAttachment(100);
	textData.top = new FormAttachment(coolBar);
	textData.bottom = new FormAttachment(100);
	text.setLayoutData(textData);
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
	}
	display.dispose();
}
}

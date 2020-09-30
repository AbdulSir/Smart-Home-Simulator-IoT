import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;

import ui.ui;

public class SHS {

	public static void main(String[] args) {

		/* Responsible for managing the connection between SWT and OS */
		Display display = Display.getDefault();

		/** Create the new window */
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		/* Create a UI Object */
		ui ui = new ui(shell, SWT.NONE);

		/* Window Title */
		shell.setText("Smart Home Simulation");		

		/* Pack the ui together */
		shell.pack();
		
		/* Center Ui */
		setUiCenter(display, shell);

		/** This will open the window and will make it visible */
		shell.open();

		/**
		 * this loop is to maintain the window open until a dispose event is received
		 * usually a close event
		 */
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	public static void setUiCenter(Display display, Shell shell) {
		/** Take the primary monitor */
		Monitor primary = display.getPrimaryMonitor();

		/** Get the size of the screen */
		Rectangle bounds = primary.getBounds();

		/** Get the size of the window */
		Rectangle rect = shell.getBounds();

		/** Calculate the centre */
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		/** Set the new location */
		shell.setLocation(x, y);
	}

}

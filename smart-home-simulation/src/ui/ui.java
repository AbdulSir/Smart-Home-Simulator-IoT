package ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JRootPane;

public class ui extends Composite {

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ui(Composite parent, int style) {
		super(parent, style);
		setLayout(null);

		Label lblHelloWorld = new Label(this, SWT.NONE);
		lblHelloWorld.setBounds(42, 46, 63, 15);
		lblHelloWorld.setText("Hello World");
		
		Composite composite = new Composite(this, SWT.EMBEDDED);
		composite.setBounds(234, 174, 64, 64);
		
		Frame frame = SWT_AWT.new_Frame(composite);
		
		Panel panel = new Panel();
		frame.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JRootPane rootPane = new JRootPane();
		panel.add(rootPane);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

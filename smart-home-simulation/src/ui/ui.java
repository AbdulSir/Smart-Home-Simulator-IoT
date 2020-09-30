package ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import java.awt.Frame;
import org.eclipse.swt.awt.SWT_AWT;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JRootPane;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

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
		
		TabFolder tabFolder = new TabFolder(this, SWT.BORDER);
		tabFolder.setBounds(264, 0, 297, 369);
		
		TabItem tbtmShs = new TabItem(tabFolder, SWT.NONE);
		tbtmShs.setText("SHS");
		
		TabItem tbtmShc = new TabItem(tabFolder, SWT.NONE);
		tbtmShc.setText("SHC");
		
		TabItem tbtmShp = new TabItem(tabFolder, SWT.NONE);
		tbtmShp.setText("SHP");
		
		TabItem tbtmShh = new TabItem(tabFolder, SWT.NONE);
		tbtmShh.setText("SHH");
		
		Group group = new Group(this, SWT.BORDER);
		group.setBounds(0, 0, 258, 369);
		
		DateTime dateTime = new DateTime(group, SWT.BORDER);
		dateTime.setBounds(78, 287, 80, 24);
		
		Label lblNewLabel = new Label(this, SWT.BORDER);
		lblNewLabel.setBounds(567, 0, 215, 369);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

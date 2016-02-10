package plugin.splmetrics.windowbuilderviews;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class InterfaceResults {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InterfaceResults window = new InterfaceResults();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(682, 421);
		shell.setText("SPL Metrics: Results");
		shell.setLayout(null);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, 646, 362);
		
		/* ***************************************
		 * Tab NoF
		 * ***************************************/
		TabItem tbtmNof = new TabItem(tabFolder, SWT.NONE);
		tbtmNof.setText("NoF");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNof.setControl(composite);
		composite.setLayout(null);
		
		Label lblTotalOfFeatures = new Label(composite, SWT.NONE);
		lblTotalOfFeatures.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblTotalOfFeatures.setBounds(10, 10, 145, 20);
		lblTotalOfFeatures.setText("Total of Features:");
		
		Label lblAbstractsFeatures = new Label(composite, SWT.NONE);
		lblAbstractsFeatures.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblAbstractsFeatures.setBounds(10, 48, 145, 20);
		lblAbstractsFeatures.setText("Concretes Features: ");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel.setBounds(10, 87, 145, 20);
		lblNewLabel.setText("Abstracts Features:");
		
		Label lblFtotalResult = new Label(composite, SWT.NONE);
		lblFtotalResult.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblFtotalResult.setBounds(161, 10, 110, 20);
		lblFtotalResult.setText("fTotal");
		
		Label lblFconcretes = new Label(composite, SWT.NONE);
		lblFconcretes.setText("fConcretes");
		lblFconcretes.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblFconcretes.setBounds(161, 48, 110, 20);
		
		Label lblFabstracts = new Label(composite, SWT.NONE);
		lblFabstracts.setText("fAbstracts");
		lblFabstracts.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblFabstracts.setBounds(161, 87, 110, 20);
		
		

		/* ***************************************
		 * Tab DIT
		 * ***************************************/
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("DIT");
		
		table = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmNewItem.setControl(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnFeatureName = new TableColumn(table, SWT.NONE);
		tblclmnFeatureName.setWidth(200);
		tblclmnFeatureName.setText("Feature Name");
		
		TableColumn tblclmnDit = new TableColumn(table, SWT.NONE);
		tblclmnDit.setWidth(100);
		tblclmnDit.setText("DIT");
		
		for (int loopIndex = 0; loopIndex < 5; loopIndex++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, "TESTE");
			item.setText(1, "VALUE");
		}
		
		

		/* ***************************************
		 * Tab NoR
		 * ***************************************/
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("NoR");
		
		Tree tree = new Tree(tabFolder, SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tbtmNewItem_1.setControl(tree);
		
		TreeColumn trclmnFeatureName = new TreeColumn(tree, SWT.NONE);
		trclmnFeatureName.setWidth(200);
		trclmnFeatureName.setText("Feature Name");
		
		TreeColumn trclmnNor = new TreeColumn(tree, SWT.NONE);
		trclmnNor.setWidth(100);
		trclmnNor.setText("NoR");
		
		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_2.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
		trtmNewTreeitem_3.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_5 = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
		trtmNewTreeitem_5.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_6 = new TreeItem(trtmNewTreeitem_5, SWT.NONE);
		trtmNewTreeitem_6.setText("New TreeItem");
		trtmNewTreeitem_5.setExpanded(true);
		
		TreeItem trtmNewTreeitem_7 = new TreeItem(trtmNewTreeitem_2, SWT.NONE);
		trtmNewTreeitem_7.setText("New TreeItem");
		trtmNewTreeitem_2.setExpanded(true);
		
		TreeItem trtmNewTreeitem_8 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_8.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_4.setText("New TreeItem");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setText("New TreeItem");

	}
}

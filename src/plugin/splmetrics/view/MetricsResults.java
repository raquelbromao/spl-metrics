package plugin.splmetrics.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import plugin.splmetrics.algmetrics.DIT;
import plugin.splmetrics.algmetrics.NoF;
import plugin.splmetrics.algmetrics.nor.NoR;

public class MetricsResults {

	private Table table;
	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args, Integer[] nof, String[][] dit) {
		try {
			MetricsResults window = new MetricsResults();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * 
	 * @param dit
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
		lblFtotalResult.setText(String.valueOf(NoF.getTotalFeatures()));
		
		Label lblFconcretes = new Label(composite, SWT.NONE);
		lblFconcretes.setText(String.valueOf(NoF.getConcretesFeatures()));
		lblFconcretes.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblFconcretes.setBounds(161, 48, 110, 20);
		
		Label lblFabstracts = new Label(composite, SWT.NONE);
		lblFabstracts.setText(String.valueOf(NoF.getAbstractsFeatures()));
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
		tblclmnFeatureName.setWidth(165);
		tblclmnFeatureName.setText("Feature Name");
		
		TableColumn tblclmnDit = new TableColumn(table, SWT.NONE);
		tblclmnDit.setWidth(35);
		tblclmnDit.setText("DIT");
		
		for (int i = 0; i < NoF.getTotalFeatures(); i++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, DIT.dit[i][0]);
			item.setText(1, DIT.dit[i][1]);
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

		TreeColumn trclmnClassName = new TreeColumn(tree, SWT.NONE);
		trclmnClassName.setWidth(100);
		trclmnClassName.setText("Class Name");
		
		TreeColumn trclmnFeatureName = new TreeColumn(tree, SWT.NONE);
		trclmnFeatureName.setWidth(200);
		trclmnFeatureName.setText("Feature Name");
		
		TreeColumn trclmnNor = new TreeColumn(tree, SWT.NONE);
		trclmnNor.setWidth(100);
		trclmnNor.setText("NoR");
	      
        for (int i = 0; i < NoR.getRefinesObjs().size(); i++) {
        	if (NoR.getRefinesObjs().get(i).getNumRefines() > 0) {
        		TreeItem trtmNewTreeItem = new TreeItem(tree, SWT.NONE);
        	
	        	trtmNewTreeItem.setText(0, NoR.getRefinesObjs().get(i).getNameClass());
	        	trtmNewTreeItem.setText(1, NoR.getRefinesObjs().get(i).getFeatureName());
	        	trtmNewTreeItem.setText(2, String.valueOf(NoR.getRefinesObjs().get(i).getNumRefines()));
	        	
	        	for(int j = 0; j < NoR.getRefinesObjs().get(i).getRefinesClasses().size(); j++) {
	        		TreeItem trtmNewTreeSubItem = new TreeItem(trtmNewTreeItem, SWT.NONE);
	        		trtmNewTreeSubItem.setText(0, NoR.getRefinesObjs().get(i).getRefinesClasses().get(j).getNameClass());
	        		trtmNewTreeSubItem.setText(1, NoR.getRefinesObjs().get(i).getRefinesClasses().get(j).getFeatureName());
	        	}
        	}
        }
	}

	public void recebeDados() {
		open();
	}
}

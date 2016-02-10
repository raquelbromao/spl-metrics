package plugin.splmetrics.actions;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.wb.swt.SWTResourceManager;

import plugin.splmetrics.main.Main;
import plugin.splmetrics.main.ReadXML;

/**
 * Our sample action implements workbench action delegate. The action proxy will
 * be created by the workbench and shown in the UI. When the user tries to use
 * the action, this delegate will be created and execution will be delegated to
 * it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class splMetricsAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	public Shell shlSplMetricsSelect;

	private static final String nol_ACTION = "plugin.splmetrics.actions.numberOfLines";

	/**
	 * The constructor.
	 * @wbp.parser.entryPoint
	 */
	public splMetricsAction() {
	}

	/**
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */

	public IProject[] getProjects() {
		/**
		 * Lista os projetos da Workspace em utilização
		 */
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

		return projects;

	}
	
	public void showSelectProjectDialog() {
		shlSplMetricsSelect = new Shell();
		shlSplMetricsSelect.setSize(391, 197);
		shlSplMetricsSelect.setText("SPL Metrics: Select the Project");
		shlSplMetricsSelect.setLayout(null);
		
		Label lblPleaseSelectThe = new Label(shlSplMetricsSelect, SWT.NONE);
		lblPleaseSelectThe.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPleaseSelectThe.setBounds(25, 20, 287, 26);
		lblPleaseSelectThe.setText("Please, select the project to compute the metrics!");
		
		Composite composite = new Composite(shlSplMetricsSelect, SWT.NONE);
		composite.setBounds(10, 52, 313, 45);
		
		Label lblProject = new Label(composite, SWT.NONE);
		lblProject.setBounds(16, 13, 55, 15);
		lblProject.setText("Project: ");
		
		Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(77, 10, 226, 23);
		
		// Gets all projects from workspace
		IProject[] projects = getProjects();

		for (int i = 0; i < projects.length; i++) {
			combo.add(projects[i].getName());
		}

		combo.select(0);
		
		Composite composite_1 = new Composite(shlSplMetricsSelect, SWT.NONE);
		composite_1.setBounds(148, 103, 174, 45);
		
		
		Button btnApply = new Button(composite_1, SWT.NONE);
		btnApply.setSelection(true);
		btnApply.setBounds(10, 10, 75, 25);
		btnApply.setText("Apply");
		
		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.setBounds(91, 10, 75, 25);
		btnCancel.setText("Cancel");
		shlSplMetricsSelect.pack();
		shlSplMetricsSelect.open();
		

		btnApply.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String pathWorkspace = "C:/Users/fsdia/Dropbox/Programming/runtime-EclipseApplication/";
				
				ReadXML.setPathModelXml(pathWorkspace + combo.getItem(combo.getSelectionIndex()) + "/model.xml");
				ReadXML.setPathFeatureDir(pathWorkspace + combo.getItem(combo.getSelectionIndex()) + "/features");
				
				File fModelXml = new File(ReadXML.getPathModelXml()); 
				if (fModelXml.exists()) {
					File fFeatureDir = new File(ReadXML.getPathFeatureDir());
					
					if (fFeatureDir.exists() && fFeatureDir.isDirectory()) {
						Main.execute(ReadXML.getPathModelXml());
					} else {
						errorSpl("NOT_EXISTS_DIR_FEATURES");
					}
				} else {
					errorSpl("NOT_EXISTS_XML");
				}
				
			}
		});

		btnCancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shlSplMetricsSelect.close();
				dispose();
			}
		});
		
	}
	
	public void errorSpl(String code) {
		int style = SWT.ICON_ERROR;
	    
	    MessageBox messageBox = new MessageBox(shlSplMetricsSelect, style);
	    
	    if(code == "NOT_EXISTS_XML") {
		    messageBox.setMessage("File 'model.xml' not exists in this project!");	    	
	    } else if (code == "NOT_EXISTS_DIR_FEATURES") {
	    	messageBox.setMessage("Directory '/features' not exists in this project!");	    	
	    }

	    int rc = messageBox.open();

	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void run(IAction action) {
		
		splMetricsAction spl = new splMetricsAction();
		spl.showSelectProjectDialog();
	
	}

	/**
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 * @wbp.parser.entryPoint
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 * @wbp.parser.entryPoint
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
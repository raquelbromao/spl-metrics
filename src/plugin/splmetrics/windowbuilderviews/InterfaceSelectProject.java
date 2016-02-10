package plugin.splmetrics.windowbuilderviews;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class InterfaceSelectProject {

	protected Shell shlSplMetricsSelect;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InterfaceSelectProject window = new InterfaceSelectProject();
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
		shlSplMetricsSelect.open();
		shlSplMetricsSelect.layout();
		while (!shlSplMetricsSelect.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSplMetricsSelect = new Shell();
		shlSplMetricsSelect.setSize(348, 197);
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
		
		Composite composite_1 = new Composite(shlSplMetricsSelect, SWT.NONE);
		composite_1.setBounds(148, 103, 174, 45);
		
		Button btnApply = new Button(composite_1, SWT.NONE);
		btnApply.setSelection(true);
		btnApply.setBounds(10, 10, 75, 25);
		btnApply.setText("Apply");
		
		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.setBounds(91, 10, 75, 25);
		btnCancel.setText("Cancel");

	}
}

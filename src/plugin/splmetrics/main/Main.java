package plugin.splmetrics.main;

import plugin.splmetrics.algmetrics.DIT;
import plugin.splmetrics.algmetrics.NoF;
import plugin.splmetrics.algmetrics.nor.NoR;
import plugin.splmetrics.view.MetricsResults;

public class Main {
	public static void execute(String pathXml) {
		ReadXML read = new ReadXML(pathXml);

		NoF.execute();
		DIT.execute();
		NoR.execute();
		
		MetricsResults mr = new MetricsResults();
		mr.recebeDados();
		
		NoF.reset();
		DIT.reset();
		NoR.reset();
	}
}

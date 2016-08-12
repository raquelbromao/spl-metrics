package plugin.splmetrics.algmetrics;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import plugin.splmetrics.main.ReadXML;

public class DIT {

	static int depthOfXml = 1;
	public static String[][] dit;
	public static int line = 0;

	public static int getChildElementCount(Element element) {
		int count = 0;
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				count++;
			}
		}
		return count;
	}
	
	public static void reset() {
		depthOfXml = 1;
		line = 0;
		dit = null;
	}
	
	private static void printNode(NodeList nodeList, int level) {
		level++;
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element no = (Element) node;

					if (no.hasAttribute("name")) {

						int nor = getChildElementCount(no);
						dit[line][0] = no.getAttribute("name");
						dit[line][1] = String.valueOf((level - 3));

						// System.out.println(dit[line][0] + " / " +
						// dit[line][1]);
						line++;
					}
					printNode(node.getChildNodes(), level);

					// o quão profundo é
					if (level > depthOfXml) {
						depthOfXml = level;
					}
				}
			}
		}
	}


	public static void inicializaDit() {
		dit = new String[NoF.getTotalFeatures()][3];
	}

	public static String[][] setDit(Element raiz) {
		inicializaDit();
		// Profundidade da arvore
		int lvl = 1;

		NodeList nodeList = raiz.getChildNodes();

		printNode(nodeList, lvl);

		System.out.println("Lvl mais fundo: " + depthOfXml);

		return dit;
	}
	
	public static void execute() {
		DIT.setDit(ReadXML.getRaiz());

	}
}

package plugin.splmetrics.algmetrics;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import plugin.splmetrics.main.ReadXML;

public class NoF {
	private static int fTotal = 0;
	private static int fAbstracts = 0;
	private static int fConcretes = 0;
	
	public static void reset() {
		fTotal = 0;
		fAbstracts = 0;
		fConcretes = 0;
	}
	
	public static void setTotalFeatures(Element raiz) {
		NodeList and = raiz.getElementsByTagName("and"); //pega todos os elementos com nome "and"
		NodeList alt = raiz.getElementsByTagName("alt"); //pega todos os elementos com nome "alt"
		NodeList feature = raiz.getElementsByTagName("feature");  //pega todos os elementos com nome "feature"
		NodeList or = raiz.getElementsByTagName("or");  //pega todos os elementos com nome "or"

		int totalAbstract = 0;

		// Conta abstracts Nodes Feature
		for(int i = 0; i < feature.getLength(); i++) {
			// Como cada elemento do NodeList é um nó, é necessário fazer um cast
			if (feature.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element x = (Element) feature.item(i);
				Attr name = x.getAttributeNode("name");
				
				if (x.hasAttribute("abstract")) {
					fAbstracts++;
				}
			}
		}
		
		// Conta abstracts Nodes AND
		for (int i = 0; i < and.getLength(); i++) {
			if (and.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element x = (Element) and.item(i);
				Attr name = x.getAttributeNode("name");

				if (x.hasAttribute("abstract")) {
					fAbstracts++;
				}
			}
		}

		// Conta abstracts Nodes ALT
		for (int i = 0; i < alt.getLength(); i++) {
			if (alt.item(i).getNodeType() == Node.ELEMENT_NODE) { 
				Element x = (Element) alt.item(i);
				Attr name = x.getAttributeNode("name");

				if (x.hasAttribute("abstract")) {
					fAbstracts++;
				} 
			}
		} 

		// Conta abstracts Nodes OR
		for (int i = 0; i < or.getLength(); i++) {
			if (or.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element x = (Element) or.item(i);
				Attr name = x.getAttributeNode("name");

				if (x.hasAttribute("abstract")) {
					fAbstracts++;
				}
			}
		}
		
		fTotal = and.getLength() + alt.getLength() + feature.getLength() + or.getLength();
		
		fConcretes = fTotal - fAbstracts;
		
	}
	
	public static int getTotalFeatures() {
		return fTotal;
	}
	
	public static int getAbstractsFeatures() {
		return fAbstracts;
	}
	
	public static int getConcretesFeatures() {
		return fConcretes;
	}
	
	public static void execute() {
		NoF.setTotalFeatures(ReadXML.getRaiz());
		
	}
}

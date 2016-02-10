package plugin.splmetrics.main;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ReadXML {
	private static Element raiz;
	
	public ReadXML(String file) {
		try {
			//fazer o parse do arquivo e criar o documento XML
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file); //raiz do documento XML
			
			//Obter elemento raiz
			this.raiz = doc.getDocumentElement();
			System.out.println("Elemento Raiz: " + this.raiz.getNodeName()
								+ "\n Tag name: " + this.raiz.getTagName()
								+ "\n Node Type: " + this.raiz.getNodeType());
			
			
		} catch (ParserConfigurationException e) { 
			// TODO Auto-generated catch block
			// acionado quando o DocumentBuilderFactory não consegue criar o parser.
			System.out.println("O parser não foi configurado corretamente.");
			e.printStackTrace();
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			// ocorre quando o parser encontra um problema na formatação do arquivo XML. 
			// o objeto Exception carrega informações sobre a localização do erro no arquivo.
			System.out.println("Problema ao fazer o parse do arquivo.");
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// Acionado quando um erro de arquivo ocorre.
			System.out.println("O arquivo não pode ser lido.");
			e.printStackTrace();
			
		}
	}
	
	public static Element getRaiz() {
		return raiz;
	}
}

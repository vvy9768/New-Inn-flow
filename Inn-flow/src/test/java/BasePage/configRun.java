package BasePage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class configRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static Document createDocument() {
		 try {
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		  Document doc = docBuilder.newDocument();
		  
		  return doc;
		 } catch (Exception e) {
		  throw new RuntimeException(e);
		 }
		}	
	
}

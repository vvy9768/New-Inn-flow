package runTimexml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DummyClassXml {
	
	final String filePath = "src/automation/excelReadCreateXMLRunTestCase/smoke.xml";
	Document document;

	@Test
	public void writeXML() {
		try {

			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dBF.newDocumentBuilder();
			document = docBuilder.newDocument();

			Element suite = document.createElement("suite");
			document.appendChild(suite);

			Attr at = document.createAttribute("name");
			at.setValue("Suite");
			suite.setAttributeNode(at);

			Element test = document.createElement("test");
			suite.appendChild(test);

			Attr at_test1 = document.createAttribute("name");
			at_test1.setValue("Test");
			test.setAttributeNode(at_test1);

			Attr at_test = document.createAttribute("thread-count");
			at_test.setValue("5");
			test.setAttributeNode(at_test);

			Element classes = document.createElement("classes");
	
		//	ExcelUtil run = new ExcelUtil();
			//Set<String> tescaseid = run.tain("class NMW");
//			for (String string : tescaseid) {
//				suiteClass(classes, "automation.excelReadCreateXMLRunTestCase." + string);
//			}
////			suiteClass(classes, "a");
//			suiteClass(classes, "b");
//			suiteClass(classes, "c");
//			suiteClass(classes, "d");

//		<suite name="Suite">
//			<test thread-count="5" name="Test">
//				<classes>
//					<class name="java.B" />
//					<class name="java.A" />
//				</classes>
//			</test> <!-- Test -->
//		</suite> <!-- Suite -->

			createXML();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		TestNG tNGRunner = new TestNG();
		List<String> suiteFile = new ArrayList<String>();
		suiteFile.add(filePath);
		tNGRunner.setTestSuites(suiteFile);
		tNGRunner.run();
	}

	public void suiteClass(Element element, String value) {
		Element classtag = document.createElement("class");
		element.appendChild(classtag);

		Attr cls = document.createAttribute("name");
		cls.setValue(value);
		classtag.setAttributeNode(cls);

	}

	public void createXML() {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			DOMSource ds = new DOMSource(document);
			StreamResult sr = new StreamResult(new File(filePath));
			tf.transform(ds, sr);

		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	

	
	
}


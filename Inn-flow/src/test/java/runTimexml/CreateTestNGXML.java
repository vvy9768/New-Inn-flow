package runTimexml;

import java.io.File;
import java.io.IOException;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class CreateTestNGXML {
	final String filePath = "src/test/resources/RunTime.xml";
	Document document;

	
	@Parameters ("testCaseName")
	@Test
	public void writeXML(String testCaseName) throws SAXException, IOException {
		
		try {
//==============create new instance of xml ==================================//
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dBF.newDocumentBuilder();
			document = docBuilder.newDocument();

//=========================create suite instance================================//
			Element suite = document.createElement("suite");
			document.appendChild(suite);

			Attr at = document.createAttribute("name");
			at.setValue("Suite");
			suite.setAttributeNode(at);
			//===================Listeners ==========================//		
			
  			Element listens =document.createElement("listeners");
	     	suite.appendChild(listens);
		
	     	Element listen =document.createElement("listener");
	     	listens.appendChild(listen);
	
	    	Attr att = document.createAttribute("class-name");
	     	att.setValue("Listeners.Listener");
	    	listen.setAttributeNode(att);
//==================create testName===============================//
			Element test = document.createElement("test");
			suite.appendChild(test);

			Attr at_test1 = document.createAttribute("name");
			at_test1.setValue("Test");
			test.setAttributeNode(at_test1);

			Attr at_test = document.createAttribute("thread-count");
			at_test.setValue("1");
			test.setAttributeNode(at_test);

			Element classes = document.createElement("classes");
			test.appendChild(classes);
			
			
			ExcelUtillXml	run=new ExcelUtillXml();
			Set<String> tescaseid = run.tain(testCaseName);
			
		Element	clsElmt=suiteClass(classes, "testCases."+testCaseName );
		Element mathodElmt=suiteMathods(clsElmt);
			for (String string : tescaseid) {
				mathodInclude(mathodElmt, string);
				
			}
			
			
			
//			suiteClass(classes, "a");
//			suiteClass(classes, "b");
//			suiteClass(classes, "c");
//			suiteClass(classes, "d");

//		<suite name="Suite">
//			<test thread-count="5" name="Test">
//				<classes>
//					<class name="java.B" />
//					<methods>
//			<include class="methodName">
//			
//           			<methods/>
//				</classes>
//			</test> <!-- Test -->
//		</suite> <!-- Suite -->

			createXML();

		 } catch (ParserConfigurationException e) {
			 
		 }
//=================================testng Runner===================================================//
		TestNG tNGRunner = new TestNG();
		List<String> suiteFile = new ArrayList<String>();
		suiteFile.add(filePath);
		tNGRunner.setTestSuites(suiteFile);
		tNGRunner.run();
		
	}

	
	public Element suiteClass(Element element,String clsValue) {
		Element classtag = document.createElement("class");
		element.appendChild(classtag);

		Attr cls = document.createAttribute("name");
		cls.setValue(clsValue);
		
		classtag.setAttributeNode(cls);
        return classtag;
        
	}
	
public Element suiteMathods(Element element) {
	   Element   methodsTag=document.createElement("methods");
	    element.appendChild(methodsTag);
	    return methodsTag;
	   
}
public Element mathodInclude(Element element,String value) {
	 Element   includeTag=document.createElement("include");
	    element.appendChild(includeTag);
	
	Attr cls = document.createAttribute("name");
	cls.setValue(value);
	includeTag.setAttributeNode(cls);
	    return includeTag;
	   
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
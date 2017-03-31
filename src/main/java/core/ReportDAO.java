package core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;   
import org.dom4j.io.*;

import model.TRClass;
import model.TRMethod;
import model.TRSuite;
import model.TRSummary;
import model.TRTest;
import model.TestReport;  

public class ReportDAO {	
	
	public static Document doc;
	
	public static Document readDoc(){
		try {           
        	File f = new File("C:/Lily/TestPro/workspace/MyMavenWebTest/target/surefire-reports/testng-results.xml");   
        	SAXReader reader = new SAXReader();   
        	doc = reader.read(f);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		} 
    	return doc;
	}
	
	public static TRSummary getReportSummary(Element root) {
		TRSummary summary = new TRSummary();
		summary.setSkipped(root.attributeValue("skipped" ));
		summary.setIgnored(root.attributeValue("ignored" ));
		summary.setFailed(root.attributeValue("failed" ));
		summary.setTotal(root.attributeValue("total" ));
		summary.setPassed(root.attributeValue("passed" ));
		return summary;
	}
	
	public static TRSuite getReportSuite(Element foo) {
		TRSuite suite = new TRSuite();
		suite.setSuiteName(foo.attributeValue("name" ));
		suite.setSuiteDuration(foo.attributeValue("duration-ms" ));
		suite.setSuiteStarted(foo.attributeValue("started-at" ));
		suite.setSuiteFinished(foo.attributeValue("finished-at" ));
		suite.setTests(getReportTests(foo));
		return suite;
	}
	
	public static TRTest getReportTest(Element foo) {
		TRTest test = new TRTest();
		test.setTName(foo.attributeValue("name" ));
		test.setTDuration(foo.attributeValue("duration-ms" ));
		test.setTStarted(foo.attributeValue("started-at" ));
		test.setTFinished(foo.attributeValue("finished-at" ));
		test.setTClasses(getReportClasses(foo));
		return test;
	}
	
	public static TRClass getReportClass(Element foo) {
		TRClass tClass = new TRClass();
		tClass.setClassName(foo.attributeValue("name" ));
		tClass.setTMethods(getReportMethods(foo));
		return tClass;
	}
	
	public static TRMethod getReportMethod(Element foo) {
		TRMethod method = new TRMethod();
		String name = foo.attributeValue("name" );
		String status = foo.attributeValue("status" );
		String isConfig = foo.attributeValue("is-config");
		String message = "";
		if(isConfig == null){
			if(!"beforeMethod".equals(name)&& !"afterMethod".equals(name)){
				method.setMethodName(foo.attributeValue("name" ));
				method.setMethodDuration(foo.attributeValue("duration-ms" ));
				method.setMethodStarted(foo.attributeValue("started-at" ));
				method.setMethodFinished(foo.attributeValue("finished-at" ));
				method.setMethodStatus(status);
				if(!"PASS".equals(status)){
					message = foo.selectSingleNode("//exception/message").getText();
				}
				else {
					message = foo.selectSingleNode("//reporter-output/line").getText();
				}
				method.setMethodMessage(message);
			}
		}
		
		return method;
	}
	
	
	public static List<TRSuite> getReportSuites(String path) {
        List<TRSuite> list = new ArrayList<TRSuite>();
        try {           
			List<Node> nodes = doc.selectNodes(path);
			for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
				Element foo = (Element) i.next();				
				list.add(getReportSuite(foo));
	        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		}
        return list;
    }
	
	public static List<TRTest> getReportTests(Element ele) {
        List<TRTest> list = new ArrayList<TRTest>();
        try {           
			List<Node> nodes = doc.selectNodes( "/" + ele.getPath() + "[@name='" + ele.attributeValue("name") + "']/test" );
			for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
				Element foo = (Element) i.next();
				list.add(getReportTest(foo));
	        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		}
        return list;
    }
	public static List<TRClass> getReportClasses(Element ele) {
        List<TRClass> list = new ArrayList<TRClass>();
        try {           
			List<Node> nodes = doc.selectNodes( "/" + ele.getPath() + "[@name='" + ele.attributeValue("name") + "']/class" );
			for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
				Element foo = (Element) i.next();
				list.add(getReportClass(foo));
	        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		}
        return list;
    }
	
	public static List<TRMethod> getReportMethods(Element ele) {
        List<TRMethod> list = new ArrayList<TRMethod>();
        try {           
			List<Node> nodes = doc.selectNodes( "/" + ele.getPath() + "[@name='" + ele.attributeValue("name") + "']/test-method" );
			for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
				Element foo = (Element) i.next();
				list.add(getReportMethod(foo));
	        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		}
        return list;
    }
	
	public static TestReport getReportResult() {
		TestReport report = new TestReport();
        try {             
        	readDoc();
			Element root = doc.getRootElement(); 
			report.setSummary(getReportSummary(root));
			report.setSuites(getReportSuites("/" + root.getPath() + "/suite"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		}
        return report;
    }

}

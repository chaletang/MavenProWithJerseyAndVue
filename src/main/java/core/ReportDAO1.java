package core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;   
import org.dom4j.io.*;

import model.TRMethod;
import model.TRSummary;
import model.TestReport;  

public class ReportDAO1 {	
	
	public static List<TRMethod> getReportResult() {
        List<TRMethod> list = new ArrayList<TRMethod>();
        try {           
        	File f = new File("C:/Lily/TestPro/workspace/MyMavenWebTest/target/surefire-reports/testng-results.xml");   
        	SAXReader reader = new SAXReader();   
        	Document doc = reader.read(f);   
			Element root = doc.getRootElement(); 
			List<Node> nodes = doc.selectNodes( "//testng-results/suite/test/class/test-method" );
			for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
				Element foo = (Element) i.next();
				TRMethod report = new TRMethod();
				String name = foo.attributeValue("name" );
				String status = foo.attributeValue("status" );
				String message = "";
				if(!"beforeMethod".equals(name)&& !"afterMethod".equals(name)){
					report.setMethodName(foo.attributeValue("name" ));
					report.setMethodDuration(foo.attributeValue("duration-ms" ));
					report.setMethodStarted(foo.attributeValue("started-at" ));
					report.setMethodFinished(foo.attributeValue("finished-at" ));
					report.setMethodStatus(status);
					if(!"PASS".equals(status)){
						message = foo.selectSingleNode("//exception/message").getText();
					}
					else {
						message = foo.selectSingleNode("//reporter-output/line").getText();
					}
					report.setMethodMessage(message);
					list.add(report);
				}
	        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
		}
        return list;
    }

}

package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testReport")
public class TestReport {
	
	private TRSummary Summary;  
	private List<TRSuite> Suites; 
      
    public TestReport() {};  
      
    public TestReport(TRSummary Summary, List<TRSuite> Suites) {  
        this.Summary = Summary;  
        this.Suites = Suites;
    }  
    @XmlElement
    public TRSummary getSummary() {  
        return Summary;  
    }  
    public void setSummary(TRSummary Summary) {  
        this.Summary = Summary;  
    }  
    public List<TRSuite> getSuites() {  
        return Suites;  
    }  
    public void setSuites(List<TRSuite> Suites) {  
        this.Suites = Suites;  
    }       
}

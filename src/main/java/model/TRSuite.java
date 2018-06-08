package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trSuite")
public class TRSuite {
	
	private String SuiteName;  
	private String SuiteDuration;  
	private String SuiteStarted; 
    private String SuiteFinished; 
    private List<TRTest> Tests;
      
    public TRSuite() {};  
      
    public TRSuite(String SuiteName, String SuiteDuration, String SuiteStarted, String SuiteFinished, List<TRTest> Tests) {  
        this.SuiteName = SuiteName;  
        this.SuiteDuration = SuiteDuration; 
        this.SuiteStarted = SuiteStarted;  
        this.SuiteFinished = SuiteFinished;
        this.Tests = Tests;
    }  
    @XmlAttribute
    public String getSuiteName() {  
        return SuiteName;  
    }  
    public void setSuiteName(String SuiteName) {  
        this.SuiteName = SuiteName;  
    }  
    public String getSuiteDuration() {  
        return SuiteDuration;  
    }  
    public void setSuiteDuration(String SuiteDuration) {  
        this.SuiteDuration = SuiteDuration;  
    } 
    public String getSuiteStarted() {  
        return SuiteStarted;  
    }  
    public void setSuiteStarted(String SuiteStarted) {  
        this.SuiteStarted = SuiteStarted;  
    } 
    public String getSuiteFinished() {  
        return SuiteFinished;  
    }  
    public void setSuiteFinished(String SuiteFinished) {  
        this.SuiteFinished = SuiteFinished;  
    } 
    @XmlElement
    public List<TRTest> getTests() {  
        return Tests;  
    }  
    public void setTests(List<TRTest> Tests) {  
        this.Tests = Tests;  
    }
    
    
}

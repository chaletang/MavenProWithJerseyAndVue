package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "trSummary")
public class TRSummary {
	
	private String Skipped;  
	private String Failed;  
	private String Ignored;  
    private String Total;  
    private String Passed; 
      
    public TRSummary() {};  
      
    public TRSummary(String Skipped, String Failed, String Ignored, String Total, String Passed) {  
        this.Skipped = Skipped;  
        this.Failed = Failed; 
        this.Ignored = Ignored;  
        this.Total = Total;
        this.Passed = Passed;
    }  
    @XmlAttribute
    public String getSkipped() {  
        return Skipped;  
    }  
    public void setSkipped(String Skipped) {  
        this.Skipped = Skipped;  
    }  
    public String getFailed() {  
        return Failed;  
    }  
    public void setFailed(String Failed) {  
        this.Failed = Failed;  
    } 
    public String getIgnored() {  
        return Ignored;  
    }  
    public void setIgnored(String Ignored) {  
        this.Ignored = Ignored;  
    } 
    public String getTotal() {  
        return Total;  
    }  
    public void setTotal(String Total) {  
        this.Total = Total;  
    } 
    public String getPassed() {  
        return Passed;  
    }  
    public void setPassed(String Passed) {  
        this.Passed = Passed;  
    }   
}

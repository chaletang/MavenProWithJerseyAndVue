package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rTest")
public class TRTest {
	
	private String TName;  
	private String TDuration;  
	private String TStarted; 
    private String TFinished; 
    private List<TRClass> TClasses; 
      
    public TRTest() {};  
      
    public TRTest(String TName, String TDuration, String TStarted, String TFinished,List<TRClass> TClasses) {  
        this.TName = TName;  
        this.TDuration = TDuration; 
        this.TStarted = TStarted;  
        this.TFinished = TFinished;
        this.TClasses = TClasses;
    }  
    @XmlAttribute
    public String getTName() {  
        return TName;  
    }  
    public void setTName(String TName) {  
        this.TName = TName;  
    }  
    public String getTDuration() {  
        return TDuration;  
    }  
    public void setTDuration(String TDuration) {  
        this.TDuration = TDuration;  
    } 
    public String getTStarted() {  
        return TStarted;  
    }  
    public void setTStarted(String TStarted) {  
        this.TStarted = TStarted;  
    } 
    public String getTFinished() {  
        return TFinished;  
    }  
    public void setTFinished(String TFinished) {  
        this.TFinished = TFinished;  
    } 
    @XmlElement
    public List<TRClass> getTClasses() {  
        return TClasses;  
    }  
    public void setTClasses(List<TRClass> TClasses) {  
        this.TClasses = TClasses;  
    }
    
}

package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trMethod")
public class TRMethod {
	
	private String MethodName;  
	private String MethodDuration;  
	private String MethodStarted;  
    private String MethodStatus;  
    private String MethodFinished;  
    private String MethodMessage;  
      
    public TRMethod() {};  
      
    public TRMethod(String MethodName, String MethodDuration, String MethodStarted, String MethodFinished, String MethodStatus, String MethodMessage) {  
        this.MethodName = MethodName;  
        this.MethodDuration = MethodDuration; 
        this.MethodStarted = MethodStarted;  
        this.MethodFinished = MethodFinished;
        this.MethodStatus = MethodStatus;
        this.MethodMessage = MethodMessage;
    }  
    @XmlElement
    public String getMethodName() {  
        return MethodName;  
    }  
    public void setMethodName(String MethodName) {  
        this.MethodName = MethodName;  
    }  
    public String getMethodDuration() {  
        return MethodDuration;  
    }  
    public void setMethodDuration(String MethodDuration) {  
        this.MethodDuration = MethodDuration;  
    } 
    public String getMethodStarted() {  
        return MethodStarted;  
    }  
    public void setMethodStarted(String MethodStarted) {  
        this.MethodStarted = MethodStarted;  
    } 
    public String getMethodFinished() {  
        return MethodFinished;  
    }  
    public void setMethodFinished(String MethodFinished) {  
        this.MethodFinished = MethodFinished;  
    } 
    public String getMethodStatus() {  
        return MethodStatus;  
    }  
    public void setMethodStatus(String MethodStatus) {  
        this.MethodStatus = MethodStatus;  
    } 
    public String getMethodMessage() {  
        return MethodMessage;  
    }  
    public void setMethodMessage(String MethodMessage) {  
        this.MethodMessage = MethodMessage;  
    } 
    
    
}

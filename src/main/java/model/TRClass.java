package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trClass")
public class TRClass {
	
	private String ClassName;
	private List<TRMethod> TMethods;
      
    public TRClass() {};  
      
    public TRClass(String ClassName,List<TRMethod> TMethods) {  
        this.ClassName = ClassName; 
        this.TMethods = TMethods; 
    }  
    @XmlAttribute
    public String getClassName() {  
        return ClassName;  
    }  
    public void setClassName(String ClassName) {  
        this.ClassName = ClassName;  
    }  
    @XmlElement
    public List<TRMethod> getTMethods() {  
        return TMethods;  
    }  
    public void setTMethods(List<TRMethod> TMethods) {  
        this.TMethods = TMethods;  
    }
}

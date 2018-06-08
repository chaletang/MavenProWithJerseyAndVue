package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testCase")
public class TestCmd {
	private String TestId;  
    private String TestName;  
    private String TestDes;  
      
    public TestCmd() {};  
      
    public TestCmd(String TestId, String TestName, String TestDes) {  
        this.TestId = TestId;  
        this.TestName = TestName;  
        this.TestDes = TestDes;  
    }  
    @XmlElement
    public String getTestId() {  
        return TestId;  
    }  
    public void setTestId(String TestId) {  
        this.TestId = TestId;  
    }  
    public String getTestName() {  
        return TestName;  
    }  
    public void setTestName(String TestName) {  
        this.TestName = TestName;  
    }  
    public String getTestDes() {  
        return TestDes;  
    }  
    public void setTestDes(String TestDes) {  
        this.TestDes = TestDes;  
    }  
    @Override

    public String toString() {
        return new StringBuffer("ID:").append(this.TestId)
                .append("Name: ").append(this.TestName)
                .append("Des: ").append(this.TestDes).toString();
    }
}

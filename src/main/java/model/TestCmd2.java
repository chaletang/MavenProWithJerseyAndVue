package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testCase")
public class TestCmd2 {
	private String TestId;  
    private String TestName;  
    private String TestDes;  
    private String CategoryName; 
      
    public TestCmd2() {};  
      
    public TestCmd2(String TestId, String TestName, String TestDes, String CategoryName) {  
        this.TestId = TestId;  
        this.TestName = TestName;  
        this.TestDes = TestDes;  
        this.CategoryName = CategoryName;  
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
    public String getCategoryName() {  
        return CategoryName;  
    }  
    public void setCategoryName(String CategoryName) {  
        this.CategoryName = CategoryName;  
    } 
    @Override

    public String toString() {
        return new StringBuffer("ID:").append(this.TestId)
                .append("Name: ").append(this.TestName)
                .append("Des: ").append(this.TestDes)
                .append("Catrgory: ").append(this.CategoryName).toString();
    }
}

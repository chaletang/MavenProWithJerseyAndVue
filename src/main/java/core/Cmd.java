package core;

import java.util.ArrayList;
import java.util.List;

public class Cmd {
	static String batPath = "C:\\Lily\\TestPro\\workspace\\MyMavenWebTest\\bat\\test.bat ";
	static String batPath2 = "C:\\Lily\\TestPro\\workspace\\MyMavenWebTest\\bat\\test2.bat ";
	static String prefix = "src/test/resources/";
	
	public static void execProcess(String[] arstringCommand) {  
        for (int i = 0; i < arstringCommand.length; i++) {  
            System.out.print(arstringCommand[i] + " ");  
        }  
        try {  
        	/*ProcessBuilder exec many cmds one by one*/
    		ProcessBuilder pb = new ProcessBuilder(arstringCommand);
    		pb.start();
  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	public static void execCommand(String[] arstringCommand) {  
		
        for (int i = 0; i < arstringCommand.length; i++) {  
            System.out.print(arstringCommand[i] + " ");  
        }  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
    public static void execCommand(String arstringCommand) { 
    	//String cmd = “cmd /c D: && cd D:\\img && dir /b”;
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    } 
    
    public static void execTest(List<String> suites) {  
        try {  
        	
        	String suitsString = String.join(".xml," + prefix, suites);
        	suitsString = prefix + suitsString + ".xml";
        	String arstringCommand = "cmd /c start " + batPath + suitsString;
            Runtime.getRuntime().exec(arstringCommand);  
            //ProcessBuilder pb = new ProcessBuilder(arstringCommand);
            //pb.start();
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    } 
    
    public static void execTest2(List<String> tests) {  
        try {  
        	//Like:TestFirefox#testng1+testng2,TestChrome#testng1+testng2
        	String testsString = String.join("," , tests);
        	String arstringCommand = "cmd /c start " + batPath2 + testsString;
            Runtime.getRuntime().exec(arstringCommand);  
            //ProcessBuilder pb = new ProcessBuilder(arstringCommand);
            //pb.start();
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    } 
    
    public void cmdTest(){  
          
        String[] arstringCommand = new String[] {  
                "cmd ",   
                "/k",  
                "start", 
                "calc"  
        };  
        //execCommand(arstringCommand);  
        
        String cmd = "cmd /k start notepad";  
        //execCommand(cmd);  
        
        List<String> testArray = new ArrayList<String>();
        testArray.add("TestTable");
        testArray.add("TestBrowser");
        execTest(testArray);	
        
        String start = "cmd /c start cmd.exe /K";
        String path = "C:\\Lily\\TestPro\\workspace\\MyMavenWebTest";
        String cd = "cd " + path + " &&";
        String mvn = "mvn clean test -DsuiteXmlFile=src/test/resources/TestBrowser.xml";
    	
        //execCommand(start + cd + mvn);
        
    }  
	public static void main(String[] args) {
		new Cmd().cmdTest();  

	}

}

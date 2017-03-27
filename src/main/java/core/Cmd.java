package core;

public class Cmd {
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
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    } 
    public static void execBat(String batname) {  
        try {  
        	String path = "C:\\Lily\\TestPro\\workspace\\MyMavenWebTest";
        	String arstringCommand = "cmd /c start " + path + "\\bat\\" + batname + ".bat";
            Runtime.getRuntime().exec(arstringCommand);  
              
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
        execBat("testNG");
    }  
	public static void main(String[] args) {
		new Cmd().cmdTest();  

	}

}

package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;  
import java.io.InputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
 
  
public class Format {  
    public static void ConvertXMLtoJSON()  {  
        
    }  
    
    public static String getParam(String className, String pKey) {
		JSONParser parser = new JSONParser();
		String pValue = "";
        try {

            Object obj = parser.parse(new FileReader("C:/Lily/TestPro/workspace/MyMavenWebTest/src/main/webapp/static/data/params.json"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            JSONObject classObj = (JSONObject)jsonObject.get(className);
            JSONObject paramObj = (JSONObject)classObj.get("Params");
            pValue = (String)paramObj.get(pKey);

            // loop array
            /*JSONArray msg = (JSONArray) jsonObject.get(pKey);
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

		return pValue;
	}
      
    public static void main(String[] args) {  
        ConvertXMLtoJSON();  
        String value = getParam("TestSearch","pRfqId");
        System.out.println(value);
    }  
}

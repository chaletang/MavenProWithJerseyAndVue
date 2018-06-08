package rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import model.TestCmd;

public class TestClient {

	public static void main(String[] args) {
		try {
			//ClientConfig cc = new DefaultClientConfig();  
			//Just for POJO support, if for JAXB, remove it  
	        //cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  
	        Client client = Client.create();          
	        WebResource resource = client.resource("http://localhost:8080/MyMavenWebTest/rest/test/post");  
	          
	        TestCmd test = new TestCmd();  
	        test.setTestDes("cmd /k start notepad");  
			test.setTestId("003");  
			test.setTestName("OpenTest");  
	          
	        ClientResponse response = resource  
	                .accept(MediaType.APPLICATION_JSON) 
	                .post(ClientResponse.class, test.toString());  
	          
	        if (response.getStatus() != 200) {
	        	throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	        }
	        String output = response.getEntity(String.class);
	        System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

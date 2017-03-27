package rest;

import java.net.URI; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;  

import org.glassfish.grizzly.http.server.HttpServer;  
  
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;  
import com.sun.jersey.api.core.PackagesResourceConfig;  
import com.sun.jersey.api.core.ResourceConfig;  
import com.sun.jersey.api.json.JSONConfiguration;

import core.Cmd;
import core.TestCmd;
import core.TestDAO; 

@Path("/test")
public class TestService {
	private static Map<String,TestCmd> testMap = new HashMap<String,TestCmd>();
	
	@GET  
    @Produces(MediaType.APPLICATION_JSON)  
    public List<TestCmd> getJSON() {  
		CacheControl cache = new CacheControl();
		cache.setNoCache(true);
		System.out.println("Get Test");
		return TestDAO.findAll();
    } 

	@GET  
    @Path("/getXml")  
    @Produces(MediaType.APPLICATION_XML)  
	public List<TestCmd> getXML() {  
		System.out.println("Get Test XML");
		return TestDAO.findAll();
    } 
	
	@GET  
    @Path("/getXml/{id}")  
    @Produces(MediaType.APPLICATION_XML)  
	public TestCmd getXML(@PathParam("name") String id) {  
		System.out.println("Get Json by id");
		return TestDAO.findById(id);
    } 
	
	@GET  
    @Path("{id}")  
    @Produces(MediaType.APPLICATION_JSON)  
	public TestCmd getJSONByID(@PathParam("id") String id) {  
		System.out.println("Get Json by id");
		return TestDAO.findById(id);
    }
	
	@GET  
    @Path("/search/{name}")  
    @Produces(MediaType.APPLICATION_JSON)  
	public List<TestCmd> getJSONByName(@PathParam("name") String name) {  
		System.out.println("Get Json by name");
		return TestDAO.findByName(name);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public TestCmd addTest(TestCmd test) {
		System.out.println("addTest");
		return TestDAO.save(test);
	} 
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public TestCmd updateTest(TestCmd test) { 
		System.out.println("updateTest");
		return TestDAO.save(test);
	} 
	
	@GET
	@Path("/run/{id}") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public void runTest(@PathParam("id") String id) {
		TestCmd test = TestDAO.findById(id);
		System.out.println("Run Test:" + test.getTestId());
		//Cmd.execCommand(test.getTestCmd());
		Cmd.execBat(test.getTestName());
	} 
	
	@GET
	@Path("/run") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)  
	public void runTests(@QueryParam("id") List<String> id) {
		for(String i:id) {
			TestCmd test = TestDAO.findById(i);
			System.out.println("Run Test:" + test.getTestId());
			//Cmd.execCommand(test.getTestCmd());
			Cmd.execBat(test.getTestName());
		}
	} 
	
	@DELETE
	@Path("/remove/{id}") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public void removeTest(@PathParam("id") String id) {
		System.out.println("Remove Test:" + id);
		TestCmd test = TestDAO.findById(id);
		TestDAO.remove(test);
	}
	
	@DELETE
	@Path("/remove") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	public void removeTests(@QueryParam("id") List<String> id) {
		for(String i:id) {
			TestCmd test = TestDAO.findById(i);
			System.out.println("Remove Test:" + test.getTestId());
			TestDAO.remove(test);
		}
		
	}

}
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.grizzly.http.server.HttpServer;  
  
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;  
import com.sun.jersey.api.core.PackagesResourceConfig;  
import com.sun.jersey.api.core.ResourceConfig;  
import com.sun.jersey.api.json.JSONConfiguration;

import core.Cmd;
import core.ReportDAO;
import core.TestDAO2;
import model.TRMethod;
import model.TestCmd2;
import model.TestReport; 

@Path("/test2")
public class TestService2 {	
	@Context
    UriInfo uriInfo;
	
	@GET  
    @Produces(MediaType.APPLICATION_JSON)  
    public List<TestCmd2> getJSON() {  
		CacheControl cache = new CacheControl();
		cache.setNoCache(true);
		System.out.println("Get Test");
		return TestDAO2.findAll();
    } 

	@GET  
    @Path("/getXml")  
    @Produces(MediaType.APPLICATION_XML)  
	public List<TestCmd2> getXML() {  
		System.out.println("Get Test XML");
		return TestDAO2.findAll();
    } 
	
	@GET  
    @Path("/getXml/{id}")  
    @Produces(MediaType.APPLICATION_XML)  
	public TestCmd2 getXML(@PathParam("name") String id) {  
		System.out.println("Get Json by id");
		return TestDAO2.findById(id);
    } 
	
	@GET  
    @Path("{id}")  
    @Produces(MediaType.APPLICATION_JSON)  
	public TestCmd2 getJSONByID(@PathParam("id") String id) {  
		System.out.println("Get Json by id");
		return TestDAO2.findById(id);
    }
	
	@GET  
    @Path("/search/{name}")  
    @Produces(MediaType.APPLICATION_JSON)  
	public List<TestCmd2> getJSONByName(@PathParam("name") String name) {  
		System.out.println("Get Json by name");
		return TestDAO2.findByName(name);
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public TestCmd2 addTest(TestCmd2 test) {
		System.out.println("addTest");
		return TestDAO2.save(test);
	} 
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public TestCmd2 updateTest(TestCmd2 test) { 
		System.out.println("updateTest");
		return TestDAO2.save(test);
	} 
		
	@GET
	@Path("/run") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)  
	public void runTests(@QueryParam("id") List<String> id) {
		Map<String,List<String>> testMap = new HashMap<String,List<String>>();
		List<String> testArray = new ArrayList<String>();
		for(String i:id) {
			TestCmd2 test = TestDAO2.findById(i);
			System.out.println("Run Test:" + test.getTestId());
			
			String catName = test.getCategoryName();
			String testName = test.getTestName();	
			if(!testMap.containsKey(catName)){
				List<String> array = new ArrayList<String>();
				array.add(testName);
				testMap.put(catName, array);
			}
			else {
				List<String> array = testMap.get(catName);
				array.add(testName);
				testMap.put(catName, array);
			}
		}
		for (Map.Entry<String,List<String>> entry : testMap.entrySet()) {
			String catString = "";
			String cat = entry.getKey();
			catString += cat;
			catString += "#";
			for(String value:entry.getValue()) {
				catString += value; 
				catString += "+"; 
			}
			testArray.add(catString.substring(0, catString.lastIndexOf("+")));
	   	}
		Cmd.execTest2(testArray);
	} 
	
	@DELETE
	@Path("/remove/{id}") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)  
	public void removeTest(@PathParam("id") String id) {
		System.out.println("Remove Test:" + id);
		TestCmd2 test = TestDAO2.findById(id);
		TestDAO2.remove(test);
	}
	
	@DELETE
	@Path("/remove") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	public void removeTests(@QueryParam("id") List<String> id) {
		for(String i:id) {
			TestCmd2 test = TestDAO2.findById(i);
			System.out.println("Remove Test:" + test.getTestId());
			TestDAO2.remove(test);
		}
		
	}
	
	/*@GET
	@Path("/report") 
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.TEXT_HTML)  
	public Response report() {
		//Redirect to other url
		URI targetURIForRedirection = uriInfo.getBaseUriBuilder().path("../report.html").build();
	    return Response.temporaryRedirect(targetURIForRedirection).build();
	} */
	
	@GET  
    @Path("/reportData")  
    @Produces(MediaType.APPLICATION_JSON)  
	public TestReport getReportXML() {  
		System.out.println("Get Test Report");
		return ReportDAO.getReportResult();
    } 

}
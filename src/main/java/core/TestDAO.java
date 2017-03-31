package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;

import model.TestCmd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TestDAO {
	private static Map<String,TestCmd> testMap = new HashMap<String,TestCmd>();
	
	public TestCmd TestFormat(String data){
		TestCmd test= new TestCmd();
		return test;
	}
	public static List<TestCmd> findAll() {
        List<TestCmd> list = new ArrayList<TestCmd>();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection();
            MongoCursor<Document> cursor = c.find().iterator();
            while (cursor.hasNext()) {
            	Document next = cursor.next();
            	TestCmd test = new TestCmd();
            	System.out.println(next.toJson());
            	test.setTestId(next.get("testId").toString());
            	test.setTestName(next.get("testName").toString());
            	test.setTestDes(next.get("testDes").toString());
            	list.add(test);
            }   
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return list;
    }

    
    public static List<TestCmd> findByName(String name) {
        List<TestCmd> list = new ArrayList<TestCmd>();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection();
            MongoCursor<Document> cursor = c.find(exists(name)).iterator();
            while (cursor.hasNext()) {
            	Document next = cursor.next();
            	TestCmd test = new TestCmd();
            	System.out.println(next.toJson());
            	test.setTestId(next.get("testId").toString());
            	test.setTestName(next.get("testName").toString());
            	test.setTestDes(next.get("testDes").toString());
            	list.add(test);
            }  
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			
			MongoDB.close();
		}
        return list;
    }
    
    public static TestCmd findById(String id) {
    	TestCmd test = new TestCmd();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection(); 
            Document doc = c.find(eq("testId", id)).first();	
        	System.out.println(doc.toJson());
        	test.setTestId(doc.get("testId").toString());
        	test.setTestName(doc.get("testName").toString());
        	test.setTestDes(doc.get("testDes").toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return test;
    }
    
    public static Boolean isExisted(String id) {
    	TestCmd test = new TestCmd();
    	Document doc = null;
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection(); 
            doc = c.find(eq("testId", id)).first();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return doc != null;
    }

    public static TestCmd save(TestCmd test)
	{
		return isExisted(test.getTestId())? update(test) : create(test);
	}    
    
    public static TestCmd create(TestCmd test) {
    	MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection();
            Document doc = new Document();
            doc.put("testId", test.getTestId());
            doc.put("testName", test.getTestName());
            doc.put("testDes", test.getTestDes());
            c.insertOne(doc);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return test;
    }

    public static TestCmd update(TestCmd test) {
    	MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection();
            Document filter = new Document();  
            filter.append("testId", test.getTestId());
            Document update = new Document();  
            update.append("$set", new Document("testName", test.getTestName()).append("testDes", test.getTestDes())); 
            c.updateOne(filter, update);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return test;
    }

    public static void remove(TestCmd test) {
    	MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection();
            Document filter = new Document();  
            filter.append("testId", test.getTestId());  
            c.deleteOne(filter); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
    	
    }
}

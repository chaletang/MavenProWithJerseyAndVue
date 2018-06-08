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

import model.TestCmd2;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class TestDAO2 {
	private static Map<String,TestCmd2> testMap = new HashMap<String,TestCmd2>();
	
	public TestCmd2 TestFormat(String data){
		TestCmd2 test= new TestCmd2();
		return test;
	}
	public static List<TestCmd2> findAll() {
        List<TestCmd2> list = new ArrayList<TestCmd2>();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2();
            MongoCursor<Document> cursor = c.find().iterator();
            while (cursor.hasNext()) {
            	Document next = cursor.next();
            	TestCmd2 test = new TestCmd2();
            	System.out.println(next.toJson());
            	test.setTestId(next.get("testId").toString());
            	test.setTestName(next.get("testName").toString());
            	test.setTestDes(next.get("testDes").toString());
            	test.setCategoryName(next.get("categoryName").toString());
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

    
    public static List<TestCmd2> findByName(String name) {
        List<TestCmd2> list = new ArrayList<TestCmd2>();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2();
            MongoCursor<Document> cursor = c.find(exists(name)).iterator();
            while (cursor.hasNext()) {
            	Document next = cursor.next();
            	TestCmd2 test = new TestCmd2();
            	System.out.println(next.toJson());
            	test.setTestId(next.get("testId").toString());
            	test.setTestName(next.get("testName").toString());
            	test.setTestDes(next.get("testDes").toString());
            	test.setCategoryName(next.get("categoryName").toString());
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
    
    public static List<TestCmd2> findByCategory(String cat) {
        List<TestCmd2> list = new ArrayList<TestCmd2>();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2();
            MongoCursor<Document> cursor = c.find(exists(cat)).iterator();
            while (cursor.hasNext()) {
            	Document next = cursor.next();
            	TestCmd2 test = new TestCmd2();
            	System.out.println(next.toJson());
            	test.setTestId(next.get("testId").toString());
            	test.setTestName(next.get("testName").toString());
            	test.setTestDes(next.get("testDes").toString());
            	test.setCategoryName(next.get("categoryName").toString());
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
    
    public static TestCmd2 findById(String id) {
    	TestCmd2 test = new TestCmd2();
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2(); 
            Document doc = c.find(eq("testId", id)).first();	
        	System.out.println(doc.toJson());
        	test.setTestId(doc.get("testId").toString());
        	test.setTestName(doc.get("testName").toString());
        	test.setTestDes(doc.get("testDes").toString());
        	test.setCategoryName(doc.get("categoryName").toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return test;
    }
    
    public static Boolean isExisted(String id) {
    	TestCmd2 test = new TestCmd2();
    	Document doc = null;
        MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2(); 
            doc = c.find(eq("testId", id)).first();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return doc != null;
    }

    public static TestCmd2 save(TestCmd2 test)
	{
		return isExisted(test.getTestId())? update(test) : create(test);
	}    
    
    public static TestCmd2 create(TestCmd2 test) {
    	MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2();
            Document doc = new Document();
            doc.put("testId", test.getTestId());
            doc.put("testName", test.getTestName());
            doc.put("testDes", test.getTestDes());
            doc.put("categoryName", test.getCategoryName());
            c.insertOne(doc);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return test;
    }

    public static TestCmd2 update(TestCmd2 test) {
    	MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2();
            Document filter = new Document();  
            filter.append("testId", test.getTestId());
            Document update = new Document();  
            update.append("$set", new Document("testName", test.getTestName())
            		.append("testDes", test.getTestDes())
            		.append("categoryName", test.getCategoryName())); 
            c.updateOne(filter, update);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			MongoDB.close();
		}
        return test;
    }

    public static void remove(TestCmd2 test) {
    	MongoCollection<Document> c = null;
        try {
            c = MongoDB.getConnection2();
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

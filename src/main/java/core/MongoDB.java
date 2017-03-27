package core;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;

import org.bson.Document;

public class MongoDB {
	public static MongoClient mongoClient;
	public static MongoDatabase db;
	public static MongoCollection<Document> collection;
	
	public static MongoCollection<Document> getConnection() {	
		try{		
		     // Connect to mongodb server
			 mongoClient = new MongoClient( "localhost" , 27017 );			
			 // Connect to your databases
			 db = mongoClient.getDatabase("mydb");
			 System.out.println("Connect to database successfully");
			 collection = db.getCollection("testcmd");
		     return collection;
				
	  	}catch(Exception e){
	  		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  			return null;
	      	}
	   	}
   public static void close() {	
	   mongoClient.close();
   }
}

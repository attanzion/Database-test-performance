package mongo_db;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
//import com.mongodb.client.model.CreateCollectionOptions;
//import com.mongodb.client.model.ValidationOptions;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.QueryBuilder.*;

public class Mongo_Import {
    
    /**
     * Costruttore della classe 'Mongo_Import'.
     */
    
    public Mongo_Import() {
    	}
    
    /**
     * Funzione che controlla se esiste un calciatore con un certo link nel DB.
     * @param link
     * @return flag
     */
    
    public boolean Check_Link(String link) {

    	boolean flag = false;
    	
    	Mongo mongo = new Mongo();
    	
    	mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");
    	
    	MongoCollection<Document> collection = mongo.getMongoCollection();
    	
    	ArrayList<Document> doc_calciatore = collection.find(eq("Link", link)).into(new ArrayList<Document>());
    	
    	if(doc_calciatore.size() != 0) {
    		
    		flag = true;
    		
    	}
    	
    	mongo.Disconnection();
    	
    	return flag;
    	
    }

}

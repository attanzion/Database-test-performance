package mongo_db;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	
	MongoClient mongoClient = null;                 /** Variabile che rappresenta il client di MongoDB. */
    MongoDatabase database = null;                  /** Variabile che rappresenta il database. */
    MongoCollection<Document> collection = null;    /** Variabile che rappresenta la collezione. */
    
    /**
     * Costruttore della classe 'Mongo'.
     */
    
    public Mongo() {}
    
    /**
     * Funzione che gestisce la connessione a MongoDB.
     * 
     * @param localhost
     * @param port
     * @param database
     * @param collection
     */

    public void Connection(String localhost, int port, String database, String collection) {

        this.setMongoClient(localhost, port);
        this.setMongoDatabase(database);
        this.setMongoCollection(collection);

       // System.out.println("Connessione a MongoDB. \n Database: " + database + "\n");

    }
    
    /**
     * Funzione che gestisce la disconnessione a MongoDB.
     */

    public void Disconnection() {

        this.mongoClient.close();

        //System.out.println("Disconnessione da MongoDB.");

    }
    
    /**
     * Funzione che setta 'mongoClient'.
     * @param localhost
     * @param port
     */

    public void setMongoClient(String localhost, int port) {

        this.mongoClient = new MongoClient( localhost, port);


        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

    }

    /**
     * Funzione che ritorna 'mongoClient'.
     * @return mongoClient
     */

    public MongoClient getMongoClient() {

        return this.mongoClient;

    }

    /**
     * Funzione che setta 'database'.
     * @param database_name
     */

    public void setMongoDatabase(String database_name) {

        this.database = this.mongoClient.getDatabase(database_name);

    }

    /**
     * Funzione che ritorna 'database'.
     * @return database
     */

    public MongoDatabase getMongoDatabase() {

        return this.database;

    }

    /**
     * Funzione che setta 'collection'.
     * @param collection_name
     */

    public void setMongoCollection(String collection_name) {

        this.collection = this.database.getCollection(collection_name);

    }

    /**
     * Funzione che ritorna 'collection'.
     * @return collection
     */

    public MongoCollection<Document> getMongoCollection() {

        return this.collection;

    }
    
    /**
     * Funzione che cancella il database.
     * @param nome_database
     */
    
    public void Drop_database(String nome_database) {
    	
    	this.Connection("localhost", 27017, nome_database, "Calciatori");
    	
    	this.database.drop();
    	
    	this.Disconnection();
    	
    	System.out.println("");
    	
    }
    
}

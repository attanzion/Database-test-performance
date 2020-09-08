package performance;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import classi.Giocatore;
import classi.Portiere;
import mongo_db.Mongo;
import mongo_db.Mongo_Export;
import mongo_db.DB1;
import mongo_db.Mongo_Export_2;
import mongo_db.Mongo_Export_3;
import mongo_db.DB2;
import mongo_db.DB3;

public class Performance_evaluation {
	
	/**
	 * Costruttore della classe 'Performance_evaluation'.
	 */
	
	public Performance_evaluation() {
		
	}
	
	/**
	 * Funzione che inserisce i calciatori nei rispettivi database in base al valore di 'configuration' e ne restituisce i millisecondi di esecuzione dell'operazione.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public long Insert(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		long ms = 0;
		
		try {
			
			final Mongo mongo = new Mongo();
			
			int count_gioc = 1;
	        
	    	long nano = 0;

			
			switch (configuration) {
			
			case 1:
			            
			            mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
			            
			            MongoCollection<Document> collection = mongo.getMongoCollection();
				    
				    	/** Inserimento giocatori. */
				    	for (Giocatore giocatore : all_gioc) {
							
				    		Mongo_Export insert = new Mongo_Export(giocatore, null, collection, 1);
				    		
				    		Thread thread = new Thread(insert);
				    		thread.start();
				    		thread.join();
				    		
				    		System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - Inserito nel db 'FootballStats'. \nCalciatore numero:  " + count_gioc);
				    		
				    		nano = nano + insert.getNano();
				    		
				    		count_gioc++;
				    		
						}
				    	/**--------------------------*/
				    	
				    	/** Inserimento portieri. */
				    	for (Portiere portiere : all_por) {
							
				    		Mongo_Export insert = new Mongo_Export( null, portiere, collection, 1);
				    		
				    		Thread thread = new Thread(insert);
				    		thread.start();
				    		thread.join();
				    		
				    		System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - Inserito nel db 'FootballStats'. \nCalciatore numero:  " + count_gioc);
				    		
				    		nano = nano + insert.getNano();
				    		
				    		count_gioc++;
				    		
							}
				    	/**--------------------------*/
				
				    ms = TimeUnit.NANOSECONDS.toMillis(nano);		
				    
				    mongo.Disconnection();
				
			break;
				
			case 2:
				
				mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
		    
		    	/** Inserimento giocatori. */
		    	for (Giocatore giocatore : all_gioc) {
					
		    		Mongo_Export_2 insert = new Mongo_Export_2(giocatore, null, collection_2, 1);
		    		
		    		Thread thread = new Thread(insert);
		    		thread.start();
		    		thread.join();
		    		
		    		System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - Inserito nel db 'FootballStats_2'. \nCalciatore numero:  " + count_gioc);
		    		
		    		nano = nano + insert.getNano();
		    		
		    		count_gioc++;
		    		
				}
		    	/**--------------------------*/
		    	
		    	/** Inserimento portieri. */
		    	for (Portiere portiere : all_por) {
					
		    		Mongo_Export_2 insert = new Mongo_Export_2( null, portiere, collection_2, 1);
		    		
		    		Thread thread = new Thread(insert);
		    		thread.start();
		    		thread.join();
		    		
		    		System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - Inserito nel db 'FootballStats'. \nCalciatore numero:  " + count_gioc);
		    		
		    		nano = nano + insert.getNano();
		    		
		    		count_gioc++;
		    		
					}
		    	/**--------------------------*/
		
		    	ms = TimeUnit.NANOSECONDS.toMillis(nano);
		    	
		    	mongo.Disconnection();
				
			break;
			
			case 3:
				
				mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
		    
		    	/** Inserimento giocatori. */
		    	for (Giocatore giocatore : all_gioc) {
					
		    		Mongo_Export_3 insert = new Mongo_Export_3(giocatore, null, collection_3, 1);
		    		
		    		Thread thread = new Thread(insert);
		    		thread.start();
		    		thread.join();
		    		
		    		System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - Inserito nel db 'FootballStats_2'. \nCalciatore numero:  " + count_gioc);
		    		
		    		nano = nano + insert.getNano();
		    		
		    		count_gioc++;
		    		
				}
		    	/**--------------------------*/
		    	
		    	/** Inserimento portieri. */
		    	for (Portiere portiere : all_por) {
					
		    		Mongo_Export_3 insert = new Mongo_Export_3( null, portiere, collection_3, 1);
		    		
		    		Thread thread = new Thread(insert);
		    		thread.start();
		    		thread.join();
		    		
		    		System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - Inserito nel db 'FootballStats'. \nCalciatore numero:  " + count_gioc);
		    		
		    		nano = nano + insert.getNano();
		    		
		    		count_gioc++;
		    		
					}
		    	/**--------------------------*/
		
		    	ms = TimeUnit.NANOSECONDS.toMillis(nano);
		    	
		    	mongo.Disconnection();
				
			break;
				
			}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation - Insert()");
		}
		
		return ms;
		
	}

}

package mongo_db;

import org.bson.Document;
import org.bson.conversions.Bson;


import classi.Giocatore;
import classi.Portiere;
import classi.Stagione_Calciatore;
import classi.Stats;

import java.util.ArrayList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Updates;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.*;

/**
 * Classe che gestisce le operazioni per la memorizzazione dei dati all'interno di esso, considerando la CONFIGURAZIONE 1 dell'aggregato. 
 */

public class DB1 {

    /**
     * Costruttore della classe "Mongo_Export".
     */

    public DB1() {
    }
    
    /**
     * Funzione che memorizza o un giocatore o un portiere nel database 'FootballStats'.
     * @param giocatore
     * @param portiere
     * @param collection
     */
    
    public void Insert_Calciatori(ArrayList<Giocatore> all_gioc, ArrayList<Portiere> all_por) {
    	
    	Mongo mongo = new Mongo();
    	
    	mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
        
        MongoCollection<Document> collection = mongo.getMongoCollection();       
		
		try {
    	
    		for(Giocatore giocatore : all_gioc) {
    		
    		 ArrayList<Document> stagioni = new ArrayList<Document>();

             Document doc_giocatore = new Document();     //Si crea un documento con le info principali
             doc_giocatore.put("Nome", giocatore.getNome_calciatore());
             doc_giocatore.put("Data nascita", giocatore.getData_nascita());
             doc_giocatore.put("Nazionalità", giocatore.getNazionalita());
             doc_giocatore.put("Ruolo", giocatore.getRuolo());
             doc_giocatore.put("Link calciatore", giocatore.getLink_calciatore());
             doc_giocatore.put("Stagioni", stagioni);    //'Stagioni' è un array inizialmente vuoto.

             

             for(Stagione_Calciatore stag_c : giocatore.getStag_g()) {   //Per ogni stagione del giocatore

                 Document doc_stats = new Document();    //Si crea un documento contenente le statistiche di ogni stagione

                 for (Stats stats : stag_c.getStats()) {
                     
                     if(stats.getType().equals("string")) {

                     	doc_stats.put(stats.getNome_stat(), stats.getString_stat());
                     	
                     } else if(stats.getType().equals("int")) {
                     	
                     	doc_stats.put(stats.getNome_stat(), stats.getInt_stat());
                     	
                     } else if(stats.getType().equals("double")) {
                     	
                     	doc_stats.put(stats.getNome_stat(), stats.getDouble_stat());
                     	
                     }

                 }
                 
                 stagioni.add(doc_stats);  

             }
             
             collection.insertOne(doc_giocatore);   //Si memorizza il documento nel database
             
             
    		
    }
    		for(Portiere portiere : all_por) {
    			
    		ArrayList<Document> stagioni = new ArrayList<Document>();

            Document doc_portiere = new Document();     //Si crea un documento con le info principali
            doc_portiere.put("Nome", portiere.getNome_calciatore());
            doc_portiere.put("Data nascita", portiere.getData_nascita());
            doc_portiere.put("Nazionalità", portiere.getNazionalita());
            doc_portiere.put("Ruolo", portiere.getRuolo());
            doc_portiere.put("Link calciatore", portiere.getLink_calciatore());
            doc_portiere.put("Stagioni", stagioni);     //'Stagioni' è un array inizialmente vuoto.

            

            for(Stagione_Calciatore stag_c : portiere.getStag_p()) {       //Per ogni stagione del giocatore

                Document doc_stats_por = new Document();        //Si crea un documento contenente le statistiche di ogni stagione

                for(Stats stats : stag_c.getStats()) {          //Per ogni stagione del giocatore

                	if(stats.getType().equals("string")) {
                		 
                    	doc_stats_por.put(stats.getNome_stat(), stats.getString_stat());
                    	
                    } else if(stats.getType().equals("int")) {
                    	
                    	doc_stats_por.put(stats.getNome_stat(), stats.getInt_stat());
                    	
                    } else if(stats.getType().equals("double")) {
                    	
                    	doc_stats_por.put(stats.getNome_stat(), stats.getDouble_stat());
                    	
                    }

                }

                stagioni.add(doc_stats_por);

           }
            
            collection.insertOne(doc_portiere);    //Si memorizza il documento nel database
            
            
            
}

				
			} catch (Exception e) {
				System.out.println("Errore in DB1 - Insert_Calciatori().");
			}
    	
    		mongo.Disconnection();
    	
    }
    
    /**
     * Funzione che cancella i calciatori, presenti negli arraylist passati come argomento, dal database.
     * @param all_por
     * @param all_gioc
     */
    
    public void Delete_Calciatori(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
            
            mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
            
            for (Giocatore giocatore : all_gioc) {
            	
            	collection.deleteOne(eq("Link calciatore", giocatore.getLink_calciatore()));
				
			}
            
            for (Portiere portiere : all_por) {
            	
            	collection.deleteOne(eq("Link calciatore", portiere.getLink_calciatore()));
				
			}
            
            mongo.Disconnection();
			
		} catch (Exception e) {
			
			System.out.println("Errore in Mongo_Export - Delete_Calciatori(). \n \n" + e);
			
		}
    	
    }
    
    /**
     * Funzione che inserisce una nuova stagione di un calciatore, nell'array di documenti 'Stagioni'.
     * @param nome
     * @param link
     * @param document
     */
    
    public void Insert_new_season(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc, Document document, Document document_por) {
    	
			try {	

	    		Mongo mongo = new Mongo();
	            
	            mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
	            
	            int count_gioc = 1;
	            
	            for (Giocatore giocatore : all_gioc) {
					
	            	 collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()),  push("Stagioni", document));
	            	 
	            	 System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - AGGIORNATO. \nCalciatore num: " + count_gioc);
	 	            
	 	             count_gioc++;
	            	
				}
	            
	            for (Portiere portiere : all_por) {
					
	            	 collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()),  push("Stagioni", document_por));
	            	 
	            	 System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - AGGIORNATO. \nCalciatore num: " + count_gioc);
			            
			         count_gioc++;
	            	
				}
	           
	            
	            mongo.Disconnection();
				
			} catch (Exception e) {
				
				System.out.println("Errore in Mongo_Export - Insert_new_season(). \n \n" + e);
				
			}
		    	
	 }
    
    /**
     * Funzione che aggiorna alcuni valori dell'ultima stagione di un calciatore, per il portiere 'goals_against_gk' e 'saves', per il giocatore 'goals' e 'assists'.
     * @param all_por
     * @param all_gioc
     */
    
    public void Update_last_season(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
            
            mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
            
            int count_gioc = 1;
            
            for(Giocatore giocatore : all_gioc) {
            	
            	collection.updateOne(and(eq("Link calciatore", giocatore.getLink_calciatore()), eq("Stagioni.season", "2050-2051")), Updates.combine(Updates.set("Stagioni.$.games", 222),Updates.set("Stagioni.$.games_starts", 222)));
            	
            	System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - CAMPI AGGIORNATI. \nCalciatore num: " + count_gioc);
            	
            	count_gioc++;
            	
            }
            
            for(Portiere portiere : all_por) {
            	
            	collection.updateOne(and(eq("Link calciatore", portiere.getLink_calciatore()), eq("Stagioni.season", "2050-2051")), Updates.combine(Updates.set("Stagioni.$.goals_against_gk", 222),Updates.set("Stagioni.$.saves", 222)));
            	
            	System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - CAMPI AGGIORNATI. \nCalciatore num: " + count_gioc);
            	
            	count_gioc++;
            	
            }
			
		} catch (Exception e) {
			
			System.out.println("Errore in Mongo_Export - Update_last_season(). \n \n" + e);
			
		}
    	
    }
    
    /**
     * Funzione che cerca un documento tramite 'Nome'. In questo caso tutti volta per volta tutti i documenti in base ai nomi degli oggetti in 'all_gioc' e 'all_por'.
     * @param all_por
     * @param all_gioc
     */
    
    public void Find_by_name(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
            
            mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
            
            int count_gioc = 1;
            
            for(Giocatore giocatore : all_gioc) {
            	
            	ArrayList<Document> doc_giocatore = collection.find(eq("Nome", giocatore.getNome_calciatore())).into(new ArrayList<Document>());
            	
            	if(doc_giocatore.size()!=0) {
            		
            		System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + "- Trovato! \nCalciatore num: " + count_gioc);
            		
            	} else {
            		
            		System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + "- Non Trovato! \nCalciatore num: " + count_gioc);
            		
            	}
            	
            	count_gioc++;
            	
            }
            
            for(Portiere portiere : all_por) {
            	
            	ArrayList<Document> doc_portiere = collection.find(eq("Nome", portiere.getNome_calciatore())).into(new ArrayList<Document>());
            	
            	if(doc_portiere.size()!=0) {
            		
            		System.out.println("PORTIERE: " + portiere.getNome_calciatore() + "- Trovato! \nCalciatore num: " + count_gioc);
            		
            	} else {
            		
            		System.out.println("PORTIERE: " + portiere.getNome_calciatore() + "- Non Trovato! \nCalciatore num: " + count_gioc);
            		
            	}
            	
            	count_gioc++;
            	
            }
			
		} catch (Exception e) {
			
			System.out.println("Errore in Mongo_Export - Find_by_name");
			
		}
    	
    }
    
    public void Create_index(String field) {
    	
    	Mongo mongo = new Mongo();
        
        mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
        
        MongoCollection<Document> collection = mongo.getMongoCollection();
        
        collection.createIndex(Indexes.ascending(field));
        
        mongo.Disconnection();
    	
    }

}
    
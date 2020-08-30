package mongo_db;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import classi.Giocatore;
import classi.Portiere;
import classi.Stagione_Calciatore;
import classi.Stats;

public class Mongo_Export_2 {
	
	/**
     * Costruttore della classe "Mongo_Export_2".
     */

    public Mongo_Export_2() {
    }
    
    /**
     * Funzione che memorizza i calciatori presenti negli array 'all_gioc' e 'all_por' nel database.
     * @param all_por
     * @param all_gioc
     */

    public void Insert_Calciatori(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
    		
    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
    		
    		MongoCollection<Document> collection = mongo.getMongoCollection();
    		
    		System.out.println("EXPORT GIOCATORI -  Configurazione 2. \n \n");
    		
    		for (Giocatore giocatore : all_gioc) {       //Per ogni giocatore in 'all_gioc'
			
    			 ArrayList<Document> stagioni = new ArrayList<Document>();
    			 ArrayList<Stagione_Calciatore> stag_c = giocatore.getStag_g();
    			 
    			 Document doc_giocatore = new Document();     //Si crea un documento con le info principali
                 doc_giocatore.put("Nome", giocatore.getNome_calciatore());
                 doc_giocatore.put("Data nascita", giocatore.getData_nascita());
                 doc_giocatore.put("Nazionalità", giocatore.getNazionalita());
                 doc_giocatore.put("Ruolo", giocatore.getRuolo());
                 doc_giocatore.put("Link calciatore", giocatore.getLink_calciatore());
                 
                 Document doc_ultima_stagione = new Document();
                 Document doc_penultima_stagione = new Document();
                 
                 int size = stag_c.size();
                 
                 for (int i = 0; i < size; i++) {
                	 
                	 Document doc_stats = new Document();
                	 
                	 Stagione_Calciatore stag_calc = stag_c.get(i);
                		 
                	 ArrayList<Stats> stats = stag_calc.getStats();
                	 
            		 for (Stats s : stats) {
            			 
            			 if(s.getType().equals("string")) {
            				 
                         	doc_stats.put(s.getNome_stat(), s.getString_stat());
                        
                         } else if(s.getType().equals("int")) {
                         	
                         	doc_stats.put(s.getNome_stat(), s.getInt_stat());
                         	
                         } else if(s.getType().equals("double")) {
                         	
                         	doc_stats.put(s.getNome_stat(), s.getDouble_stat());
                         	
                         }
							
					}
                	 
                	 if(i == size - 1) {
                		 
                		 doc_ultima_stagione = doc_stats;
                		 
                	 } else if(i == size - 2) {
                		 
                		 doc_penultima_stagione = doc_stats;
                		 
                	 } else {
                		 
                		 stagioni.add(doc_stats);
                		 
                	 }
					
				}
                 
                 doc_giocatore.put("Ultima stagione", doc_ultima_stagione);
                 doc_giocatore.put("Penultima stagione", doc_penultima_stagione);
                 doc_giocatore.put("Stagioni", stagioni);
    			
                 collection.insertOne(doc_giocatore);
                 
                 System.out.println(giocatore.getNome_calciatore() + " EXPORTED! \n");
                 
    		}
    		
    		System.out.println("EXPORT PORTIERI. \n \n");
    		
    		for (Portiere portiere : all_por) {       //Per ogni giocatore in 'all_gioc'
    			
   			 ArrayList<Document> stagioni = new ArrayList<Document>();
   			 ArrayList<Stagione_Calciatore> stag_c = portiere.getStag_p();
   			 
   			 Document doc_giocatore = new Document();     //Si crea un documento con le info principali
                doc_giocatore.put("Nome", portiere.getNome_calciatore());
                doc_giocatore.put("Data nascita", portiere.getData_nascita());
                doc_giocatore.put("Nazionalità", portiere.getNazionalita());
                doc_giocatore.put("Ruolo", portiere.getRuolo());
                doc_giocatore.put("Link calciatore", portiere.getLink_calciatore());
                
                Document doc_ultima_stagione = new Document();
                Document doc_penultima_stagione = new Document();
                
                int size = stag_c.size();
                
                for (int i = 0; i < size; i++) {
               	 
               	 Document doc_stats = new Document();
               	 
               	 Stagione_Calciatore stag_calc = stag_c.get(i);
               		 
               	 ArrayList<Stats> stats = stag_calc.getStats();
               	 
           		 for (Stats s : stats) {
           			 
           			 if(s.getType().equals("string")) {
           				 
                        	doc_stats.put(s.getNome_stat(), s.getString_stat());
                       
                        } else if(s.getType().equals("int")) {
                        	
                        	doc_stats.put(s.getNome_stat(), s.getInt_stat());
                        	
                        } else if(s.getType().equals("double")) {
                        	
                        	doc_stats.put(s.getNome_stat(), s.getDouble_stat());
                        	
                        }
							
					}
               	 
               	 if(i == size - 1) {
               		 
               		 doc_ultima_stagione = doc_stats;
               		 
               	 } else if(i == size - 2) {
               		 
               		 doc_penultima_stagione = doc_stats;
               		 
               	 } else {
               		 
               		 stagioni.add(doc_stats);
               		 
               	 }
					
				}
                
                doc_giocatore.put("Ultima stagione", doc_ultima_stagione);
                doc_giocatore.put("Penultima stagione", doc_penultima_stagione);
                doc_giocatore.put("Stagioni", stagioni);
   			
                collection.insertOne(doc_giocatore);
                
                System.out.println(portiere.getNome_calciatore() + " EXPORTED! \n");
                
   		}
    		
    		mongo.Disconnection();
    			
		} catch (Exception e) {
			
			System.out.println("Errore in Mongo_Export_2 - Insert_Calciatori(). \n \n" + e);
			
		}
    	
    }

}

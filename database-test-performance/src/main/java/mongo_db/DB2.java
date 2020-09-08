package mongo_db;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import classi.Giocatore;
import classi.Portiere;
import classi.Stagione_Calciatore;
import classi.Stats;

public class DB2 {
	
	/**
     * Costruttore della classe "Mongo_Export_2".
     */

    public DB2() {
    }
    
    /**
     * Funzione che memorizza o un giocatore o un portiere nel database 'FootballStats'.
     * @param giocatore
     * @param portiere
     * @param collection
     */
    
    public void Insert_Calciatori(Giocatore giocatore, Portiere portiere, MongoCollection<Document> collection) {
    	
    	if(portiere == null) {
    		
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
    		
    	} else if(giocatore == null) {
    		
    		 ArrayList<Document> stagioni = new ArrayList<Document>();
  			 ArrayList<Stagione_Calciatore> stag_c = portiere.getStag_p();
  			 
  			 Document doc_portiere = new Document();     //Si crea un documento con le info principali
  			 	doc_portiere.put("Nome", portiere.getNome_calciatore());
  			 	doc_portiere.put("Data nascita", portiere.getData_nascita());
  			 	doc_portiere.put("Nazionalità", portiere.getNazionalita());
  			 	doc_portiere.put("Ruolo", portiere.getRuolo());
  			 	doc_portiere.put("Link calciatore", portiere.getLink_calciatore());
               
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
               
               doc_portiere.put("Ultima stagione", doc_ultima_stagione);
               doc_portiere.put("Penultima stagione", doc_penultima_stagione);
               doc_portiere.put("Stagioni", stagioni);
  			
               collection.insertOne(doc_portiere);
    		
    	}
    	
    	
    }
    
    /**
     * Funzione che cancella i calciatori, presenti negli arraylist passati come argomento, dal database.
     * @param all_por
     * @param all_gioc
     */
    
    public void Delete_Calciatori(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
            
            mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
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
     * Funzione che inserisce una nuova stagione di un calciatore come 'Ultima stagione', spostando la vecchia ultima stagione in 'Penultima stagione' e la vecchia penutima stagione aggiunta nell'array delle stagioni ('Stagioni').
     * @param nome
     * @param link
     * @param document
     */
    
    public void Insert_new_season(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc, Document document, Document document_por) {
    	
			try {
				

	    		Mongo mongo = new Mongo();
	            
	            mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
	            
	            int count_gioc = 1;
	            
	            for(Giocatore giocatore : all_gioc) {
	            
	            ArrayList<Document> doc_list = collection.find(eq("Link calciatore", giocatore.getLink_calciatore())).into(new ArrayList<Document>());
	            
	            Document ultima_stagione = new Document();
	            Document penultima_stagione = new Document();
	            
	            for (Document d : doc_list) {
					
	            	ultima_stagione = (Document) d.get("Ultima stagione");
	            	penultima_stagione = (Document) d.get("Penultima stagione");
	            	
				}
	            
	            BasicDBObject updateFields = new BasicDBObject();
	            updateFields.append("Ultima stagione", document);
	            updateFields.append("Penultima stagione", ultima_stagione);
	            BasicDBObject setQuery = new BasicDBObject();
	            setQuery.append("$set", updateFields);
	            
	            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()),  push("Stagioni", penultima_stagione));
	            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), setQuery);
	            
	            System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - AGGIORNATO. \nCalciatore num: " + count_gioc);
	            
	            count_gioc++;
	            
	            }
	            
	            for(Portiere portiere : all_por) {
	            	
	            	ArrayList<Document> doc_list = collection.find(eq("Link calciatore", portiere.getLink_calciatore())).into(new ArrayList<Document>());
		            
		            Document ultima_stagione = new Document();
		            Document penultima_stagione = new Document();
		            
		            for (Document d : doc_list) {
						
		            	ultima_stagione = (Document) d.get("Ultima stagione");
		            	penultima_stagione = (Document) d.get("Penultima stagione");
		            	
					}
		            
		            BasicDBObject updateFields = new BasicDBObject();
		            updateFields.append("Ultima stagione", document_por);
		            updateFields.append("Penultima stagione", ultima_stagione);
		            BasicDBObject setQuery = new BasicDBObject();
		            setQuery.append("$set", updateFields);
		            
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), setQuery);
	            	
		            System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - AGGIORNATO. \nCalciatore num: " + count_gioc);
		            
		            count_gioc++;
	            	
	            }
	            
	            mongo.Disconnection();
				
			} catch (Exception e) {
				
				System.out.println("Errore in Mongo_Export_2 - Insert_new_season(). \n \n" + e);
				
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
            
            mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
            
            int count_gioc = 1;
            
            for(Giocatore giocatore : all_gioc) {
            	
            	collection.updateOne(and(eq("Link calciatore", giocatore.getLink_calciatore())), Updates.combine(Updates.set("Ultima stagione.games", 222),Updates.set("Ultima stagione.games_starts", 222)));
            	
            	System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - CAMPI AGGIORNATI. \nCalciatore num: " + count_gioc);
            	
            	count_gioc++;
            	
            }
            
            for(Portiere portiere : all_por) {
            	
            	collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), Updates.combine(Updates.set("Ultima stagione.goals_against_gk", 222),Updates.set("Ultima stagione.saves", 222)));
            	
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
            
            mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
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
    
}

package performance;

import java.util.ArrayList;
import java.util.Random;
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
	
	public double Insert(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		try {
			
			final Mongo mongo = new Mongo();
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	/** Cancellazione dei vecchi database. */
	    	if (configuration == 1) {
	    		
	    		this.Delete_DB();
	    		
	    	}
	    	/**-------------------------------------*/
		
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
				    		
				    		nano = nano + insert.getNano();
				    		
				    		count_gioc++;
				    		
							}
				    	/**--------------------------*/
				
				    ms = nano/1000000;		
				    
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
		    		
		    		nano = nano + insert.getNano();
		    		
		    		count_gioc++;
		    		
					}
		    	/**--------------------------*/
		
		    	ms = nano/1000000;
		    	
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
		    		
		    		nano = nano + insert.getNano();
		    		
		    		count_gioc++;
		    		
					}
		    	/**--------------------------*/
		
		    	ms = nano/1000000;
		    	
		    	mongo.Disconnection();
				
			break;
				
			}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation - Insert()");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi dell'esecuzione dell'aggiunta di una nuova stagione in tutti i calciatori in 'all_gioc' e 'all_por', per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Update_new_season(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		try {
			
			final Mongo mongo = new Mongo();
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */    
	    	
	    	}
			
	    	switch (configuration) {
	    	
				case 1:
					
					mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
		            
		            MongoCollection<Document> collection = mongo.getMongoCollection();
					
		            /** Aggiunta nuova stagione per i giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export update_new_season = new Mongo_Export(giocatore, null, collection, 2, this.Standard_document_stagione_giocatore());
						
						Thread thread = new Thread(update_new_season);
						thread.start();
						thread.join();
						
						nano = nano + update_new_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Aggiunta nuova stagione per i portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export update_new_season = new Mongo_Export(null, portiere, collection, 2, this.Standard_document_stagione_portiere());
						
						Thread thread = new Thread(update_new_season);
						thread.start();
						thread.join();
						
						nano = nano + update_new_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					ms = nano/1000000;
			    	
			    	mongo.Disconnection();
					
					break;
					
				case 2:
					
					mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
		            
		            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
					
		            /** Aggiunta nuova stagione per i giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 update_new_season = new Mongo_Export_2(giocatore, null, collection_2, 2, this.Standard_document_stagione_giocatore());
						
						Thread thread = new Thread(update_new_season);
						thread.start();
						thread.join();
						
						nano = nano + update_new_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Aggiunta nuova stagione per i portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 update_new_season = new Mongo_Export_2(null, portiere, collection_2, 2, this.Standard_document_stagione_portiere());
						
						Thread thread = new Thread(update_new_season);
						thread.start();
						thread.join();
						
						nano = nano + update_new_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					ms = nano/1000000;
			    	
			    	mongo.Disconnection();
					
					break;
					
				case 3:
					
					mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
		            
		            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
					
		            /** Aggiunta nuova stagione per i giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 update_new_season = new Mongo_Export_3(giocatore, null, collection_3, 2, this.Standard_document_stagione_giocatore());
						
						Thread thread = new Thread(update_new_season);
						thread.start();
						thread.join();
						
						nano = nano + update_new_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Aggiunta nuova stagione per i portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 update_new_season = new Mongo_Export_3(null, portiere, collection_3, 2, this.Standard_document_stagione_portiere());
						
						Thread thread = new Thread(update_new_season);
						thread.start();
						thread.join();
						
						nano = nano + update_new_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					ms = nano/1000000;
			    	
			    	mongo.Disconnection();
					
					break;
	
				default:
					break;
					
			}
	    	
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Update_new_season().");
		}
		
		return ms;
		
	}
	
	  /**
     * Funzione che calcola i millisecondi che ci son voluti per aggiornare alcuni valori dell'ultima stagione di un calciatore, per il portiere 'goals_against_gk' e 'saves', per il giocatore 'goals' e 'assists', per tutte le configurazioni.
     * I valori vengono settati a 222.
     * @param all_por
     * @param all_gioc
     * @param configuration
     */
	
	public double Update_last_season(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
		
		try {
		
		int count_gioc = 1;
        
    	double nano = 0;
    	
    	if(configuration == 1) {
    	
    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
    	
    	} 
    	
    	switch (configuration) {
    	
			case 1:
				
				mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
				
	            /** Update campi ultima stagione per i giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export update_last_season = new Mongo_Export(giocatore, null, collection, 3);
						
						Thread thread = new Thread(update_last_season);
						thread.start();
						thread.join();
						
						nano = nano + update_last_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Update campi ultima stagione per i portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export update_last_season = new Mongo_Export(null, portiere, collection, 3);
						
						Thread thread = new Thread(update_last_season);
						thread.start();
						thread.join();
						
						nano = nano + update_last_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
				
				break;
				
			case 2:
				
				mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            /** Update campi ultima stagione per i giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 update_last_season = new Mongo_Export_2(giocatore, null, collection_2, 3);
						
						Thread thread = new Thread(update_last_season);
						thread.start();
						thread.join();
						
						nano = nano + update_last_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Update campi ultima stagione per i portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 update_last_season = new Mongo_Export_2(null, portiere, collection_2, 3);
						
						Thread thread = new Thread(update_last_season);
						thread.start();
						thread.join();
						
						nano = nano + update_last_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
				
				break;
				
			case 3:
	
				mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
			    
			    MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
			    /** Update campi ultima stagione per i giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 update_last_season = new Mongo_Export_3(giocatore, null, collection_3, 3);
						
						Thread thread = new Thread(update_last_season);
						thread.start();
						thread.join();
						
						nano = nano + update_last_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Update campi ultima stagione per i portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 update_last_season = new Mongo_Export_3(null, portiere, collection_3, 3);
						
						Thread thread = new Thread(update_last_season);
						thread.start();
						thread.join();
						
						nano = nano + update_last_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
				
				mongo.Disconnection();
	
				break;
	
			default:
				break;
		}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Update_last_season().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per la ricerca, tramite nome, dei calciatori in 'all_gioc' e 'all_por'.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Find_players(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
		
		try {
		
		int count_gioc = 1;
        
    	double nano = 0;
    	
    	if(configuration == 1) {
    	
    	this.Delete_and_Insert(all_gioc, all_por, "Nome");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
    	
    	}
    	
    	switch(configuration) {
    	
    	case 1:
    		
    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
			
            /** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export find_players = new Mongo_Export(giocatore, null, collection, 4);
					
					Thread thread = new Thread(find_players);
					thread.start();
					thread.join();
					
					nano = nano + find_players.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export find_players = new Mongo_Export(null, portiere, collection, 4);
					
					Thread thread = new Thread(find_players);
					thread.start();
					thread.join();
					
					nano = nano + find_players.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 2:
    		
    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_2 find_players = new Mongo_Export_2(giocatore, null, collection_2, 4);
					
					Thread thread = new Thread(find_players);
					thread.start();
					thread.join();
					
					nano = nano + find_players.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_2 find_players = new Mongo_Export_2(null, portiere, collection_2, 4);
					
					Thread thread = new Thread(find_players);
					thread.start();
					thread.join();
					
					nano = nano + find_players.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 3:		
    		
    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_3 find_players = new Mongo_Export_3(giocatore, null, collection_3, 4);
					
					Thread thread = new Thread(find_players);
					thread.start();
					thread.join();
					
					nano = nano + find_players.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_3 find_players = new Mongo_Export_3(null, portiere, collection_3, 4);
					
					Thread thread = new Thread(find_players);
					thread.start();
					thread.join();
					
					nano = nano + find_players.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    	
    		break;
    		
    	default:   		
    		break;
    		
    	}
    	
		}catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Find_players().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per ottenere un dato di una certa stagione ( in questo caso 'minutes' per i giocatori e 'minutes_gk' per i portieri, dell'ultima stagione) per tutte le configurazioni.
	 * Si sceglie l'ultima stagione in quanto è possibile fare un confronto nelle tre configurazioni perchè altrimenti l'algoritmo sarebbe lo stesso per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Find_field(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
		
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
				
	            /** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export find_field = new Mongo_Export(giocatore, null, collection, 5);
						
						Thread thread = new Thread(find_field);
						thread.start();
						thread.join();
						
						nano = nano + find_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export find_field = new Mongo_Export(null, portiere, collection, 5);
						
						Thread thread = new Thread(find_field);
						thread.start();
						thread.join();
						
						nano = nano + find_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 find_field = new Mongo_Export_2(giocatore, null, collection_2, 5);
						
						Thread thread = new Thread(find_field);
						thread.start();
						thread.join();
						
						nano = nano + find_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 delete_players = new Mongo_Export_2(null, portiere, collection_2, 5);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 find_field = new Mongo_Export_3(giocatore, null, collection_3, 5);
						
						Thread thread = new Thread(find_field);
						thread.start();
						thread.join();
						
						nano = nano + find_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 find_field = new Mongo_Export_3(null, portiere, collection_3, 5);
						
						Thread thread = new Thread(find_field);
						thread.start();
						thread.join();
						
						nano = nano + find_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    	
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Find_field().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per calcolare la media di un campo per ogni stagione del calciatore, per i giocatori si calcolerà la media dei 'goals', per i portieri si calcolerà la media dei 'saves', per le tre configurazioni dei database.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Average(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
		
		try {
		
		int count_gioc = 1;
        
    	double nano = 0;
    	
    	if(configuration == 1) {
    	
    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
    	
    	}
    	
    	switch(configuration) {
    	
    	case 1:
    		
    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
			
            /** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export average_goals = new Mongo_Export(giocatore, null, collection, 6);
					
					Thread thread = new Thread(average_goals);
					thread.start();
					thread.join();
					
					nano = nano + average_goals.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export average_saves = new Mongo_Export(null, portiere, collection, 6);
					
					Thread thread = new Thread(average_saves);
					thread.start();
					thread.join();
					
					nano = nano + average_saves.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 2:
    		
    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_2 average_goals = new Mongo_Export_2(giocatore, null, collection_2, 6);
					
					Thread thread = new Thread(average_goals);
					thread.start();
					thread.join();
					
					nano = nano + average_goals.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_2 average_saves = new Mongo_Export_2(null, portiere, collection_2, 6);
					
					Thread thread = new Thread(average_saves);
					thread.start();
					thread.join();
					
					nano = nano + average_saves.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 3:		
    		
    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_3 average_goals = new Mongo_Export_3(giocatore, null, collection_3, 6);
					
					Thread thread = new Thread(average_goals);
					thread.start();
					thread.join();
					
					nano = nano + average_goals.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_3 average_saves = new Mongo_Export_3(null, portiere, collection_3, 6);
					
					Thread thread = new Thread(average_saves);
					thread.start();
					thread.join();
					
					nano = nano + average_saves.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    	
    		break;
    		
    	
    		
    	default:   		
    		break;
    		
    	}
    	
		}catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Average().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per eliminare un campo (in questo caso 'npxg_xa_per90' per i giocatore e 'goals_against_gk_per90' per i portieri) da una stagione per tutte le configurazioni.
	 * Si sceglie l'ultima stagione in quanto è possibile fare un confronto nelle tre configurazioni perchè altrimenti l'algoritmo sarebbe lo stesso per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Delete_field(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		Mongo mongo = new Mongo();
		
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
				
	            /** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export delete_field = new Mongo_Export(giocatore, null, collection, 7);
						
						Thread thread = new Thread(delete_field);
						thread.start();
						thread.join();
						
						nano = nano + delete_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export average_saves = new Mongo_Export(null, portiere, collection, 7);
						
						Thread thread = new Thread(average_saves);
						thread.start();
						thread.join();
						
						nano = nano + average_saves.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 delete_field = new Mongo_Export_2(giocatore, null, collection_2, 7);
						
						Thread thread = new Thread(delete_field);
						thread.start();
						thread.join();
						
						nano = nano + delete_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 delete_field = new Mongo_Export_2(null, portiere, collection_2, 7);
						
						Thread thread = new Thread(delete_field);
						thread.start();
						thread.join();
						
						nano = nano + delete_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 delete_field = new Mongo_Export_3(giocatore, null, collection_3, 7);
						
						Thread thread = new Thread(delete_field);
						thread.start();
						thread.join();
						
						nano = nano + delete_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 delete_field = new Mongo_Export_3(null, portiere, collection_3, 7);
						
						Thread thread = new Thread(delete_field);
						thread.start();
						thread.join();
						
						nano = nano + delete_field.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    		
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Delete_field().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per cancellare i calciatori presenti in 'all_gioc' e 'all_por', per le tre configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Delete_players(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
				
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
				
	            /** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export delete_players = new Mongo_Export(giocatore, null, collection, 8);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export delete_players = new Mongo_Export(null, portiere, collection, 8);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 delete_players = new Mongo_Export_2(giocatore, null, collection_2, 8);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 delete_players = new Mongo_Export_2(null, portiere, collection_2, 8);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 delete_players = new Mongo_Export_3(giocatore, null, collection_3, 8);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 delete_players = new Mongo_Export_3(null, portiere, collection_3, 8);
						
						Thread thread = new Thread(delete_players);
						thread.start();
						thread.join();
						
						nano = nano + delete_players.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    	
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Delete_players().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per eliminare una stagione di un calciatore (in questo caso la stagione '2019-2020', ovvero l'ultima stagione) per tutte le configurazioni.
	 * Si sceglie l'ultima stagione in quanto è possibile fare un confronto nelle tre configurazioni perchè altrimenti l'algoritmo sarebbe lo stesso per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Delete_season(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
				
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
				
	            /** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export delete_season = new Mongo_Export(giocatore, null, collection, 9);
						
						Thread thread = new Thread(delete_season);
						thread.start();
						thread.join();
						
						nano = nano + delete_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export delete_season = new Mongo_Export(null, portiere, collection, 9);
						
						Thread thread = new Thread(delete_season);
						thread.start();
						thread.join();
						
						nano = nano + delete_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 delete_season = new Mongo_Export_2(giocatore, null, collection_2, 9);
						
						Thread thread = new Thread(delete_season);
						thread.start();
						thread.join();
						
						nano = nano + delete_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 delete_season = new Mongo_Export_2(null, portiere, collection_2, 9);
						
						Thread thread = new Thread(delete_season);
						thread.start();
						thread.join();
						
						nano = nano + delete_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 delete_season = new Mongo_Export_3(giocatore, null, collection_3, 9);
						
						Thread thread = new Thread(delete_season);
						thread.start();
						thread.join();
						
						nano = nano + delete_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 delete_season = new Mongo_Export_3(null, portiere, collection_3, 9);
						
						Thread thread = new Thread(delete_season);
						thread.start();
						thread.join();
						
						nano = nano + delete_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    	
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Delete_season().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per ricercare i giocatori che hanno una statistica (in questo caso 'goals' per i giocatori e 'saves' per i portieri) maggiore di un certo valore per una certa stagione (in questo caso stagione 2019 - 2020), per tutte le configurazioni.
	 * Si sceglie l'ultima stagione in quanto è possibile fare un confronto nelle tre configurazioni perchè altrimenti l'algoritmo sarebbe lo stesso per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return
	 */
	
	public double Greater_than(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration, final int[] random_goals, int[] random_saves) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
				
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Stagioni");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
					           
					for (int i = 0; i < (all_gioc.size() + all_por.size()); i++) {
						
						Mongo_Export greater_than = new Mongo_Export(collection, 10, random_goals[i], random_saves[i]);
						
						Thread thread = new Thread(greater_than);
						thread.start();
						thread.join();
						
						nano = nano + greater_than.getNano();
						
						count_gioc++;
						
					}
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            for (int i = 0; i < (all_gioc.size() + all_por.size()); i++) {
					
					Mongo_Export_2 greater_than = new Mongo_Export_2(collection_2, 10, random_goals[i], random_saves[i]);
					
					Thread thread = new Thread(greater_than);
					thread.start();
					thread.join();
					
					nano = nano + greater_than.getNano();
					
					count_gioc++;
					
				}
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            for (int i = 0; i < (all_gioc.size() + all_por.size()); i++) {
					
					Mongo_Export_3 greater_than = new Mongo_Export_3(collection_3, 10, random_goals[i], random_saves[i]);
					
					Thread thread = new Thread(greater_than);
					thread.start();
					thread.join();
					
					nano = nano + greater_than.getNano();
					
					count_gioc++;
					
				}
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    	
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Greater_than().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per ricercare i giocatori che in una certa stagione (in questo caso la stagione 2019-2020) hanno militato in una certa squadra, per le tre configurazioni.
	 * Si sceglie l'ultima stagione in quanto è possibile fare un confronto nelle tre configurazioni perchè altrimenti l'algoritmo sarebbe lo stesso per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @param random_squad
	 * @return ms
	 */
	
	public double Find_by_Squad(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration, final String[] random_squad) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
				
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Stagioni");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
					           
					for (int i = 0; i < (all_gioc.size() + all_por.size()); i++) {
						
						Mongo_Export find_by_squad = new Mongo_Export(collection, 11, random_squad[i]);
						
						Thread thread = new Thread(find_by_squad);
						thread.start();
						thread.join();
						
						nano = nano + find_by_squad.getNano();
						
						count_gioc++;
						
					}
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            for (int i = 0; i < (all_gioc.size() + all_por.size()); i++) {
					
					Mongo_Export_2 find_by_squad = new Mongo_Export_2(collection_2, 11, random_squad[i]);
					
					Thread thread = new Thread(find_by_squad);
					thread.start();
					thread.join();
					
					nano = nano + find_by_squad.getNano();
					
					count_gioc++;
					
				}
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            for (int i = 0; i < (all_gioc.size() + all_por.size()); i++) {
					
					Mongo_Export_3 find_by_squad = new Mongo_Export_3(collection_3, 11, random_squad[i]);
					
					Thread thread = new Thread(find_by_squad);
					thread.start();
					thread.join();
					
					nano = nano + find_by_squad.getNano();
					
					count_gioc++;
					
				}
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    	
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Find_by_squad().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per visualizzare una data stagione (in questo caso la stagione 2019-2020) di un calciatore, per tutte le configurazioni.
	 * Si sceglie l'ultima stagione in quanto è possibile fare un confronto nelle tre configurazioni perchè altrimenti l'algoritmo sarebbe lo stesso per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double View_season(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
				
		try {
			
			int count_gioc = 1;
	        
	    	double nano = 0;
	    	
	    	if(configuration == 1) {
	    	
	    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
	    	
	    	}
	    	
	    	switch(configuration) {
	    	
	    	case 1:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
				
	            /** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export view_season = new Mongo_Export(giocatore, null, collection, 12);
						
						Thread thread = new Thread(view_season);
						thread.start();
						thread.join();
						
						nano = nano + view_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export view_season = new Mongo_Export(null, portiere, collection, 12);
						
						Thread thread = new Thread(view_season);
						thread.start();
						thread.join();
						
						nano = nano + view_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 2:
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_2 view_season = new Mongo_Export_2(giocatore, null, collection_2, 12);
						
						Thread thread = new Thread(view_season);
						thread.start();
						thread.join();
						
						nano = nano + view_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_2 view_season = new Mongo_Export_2(null, portiere, collection_2, 12);
						
						Thread thread = new Thread(view_season);
						thread.start();
						thread.join();
						
						nano = nano + view_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    		
	    		break;
	    		
	    	case 3:		
	    		
	    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
				
	            	/** Ricerca giocatori. */
					for (Giocatore giocatore : all_gioc) {
						
						Mongo_Export_3 view_season = new Mongo_Export_3(giocatore, null, collection_3, 12);
						
						Thread thread = new Thread(view_season);
						thread.start();
						thread.join();
						
						nano = nano + view_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
					
					/** Ricerca portieri. */
					for (Portiere portiere : all_por) {
						
						Mongo_Export_3 view_season = new Mongo_Export_3(null, portiere, collection_3, 12);
						
						Thread thread = new Thread(view_season);
						thread.start();
						thread.join();
						
						nano = nano + view_season.getNano();
						
						count_gioc++;
						
					}
					/**--------------------------------------------*/
				
				ms = nano/1000000;
		    	
		    	mongo.Disconnection();
	    	
	    		break;
	    		
	    	default:   		
	    		break;
	    	
	    	}
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Delete_season().");
		}
		
		return ms;
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per calcolare la somma di un campo per ogni stagione del calciatore, per i giocatori si calcolerà la somma dei 'goals', per i portieri si calcolerà la somma dei 'saves', per le tre configurazioni dei database.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Sum(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
		
		try {
		
		int count_gioc = 1;
        
    	double nano = 0;
    	
    	if(configuration == 1) {
    	
    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
    	
    	}
    	
    	switch(configuration) {
    	
    	case 1:
    		
    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
			
            /** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export sum_goals = new Mongo_Export(giocatore, null, collection, 13);
					
					Thread thread = new Thread(sum_goals);
					thread.start();
					thread.join();
					
					nano = nano + sum_goals.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export sum_saves = new Mongo_Export(null, portiere, collection, 13);
					
					Thread thread = new Thread(sum_saves);
					thread.start();
					thread.join();
					
					nano = nano + sum_saves.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 2:
    		
    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_2 sum_goals = new Mongo_Export_2(giocatore, null, collection_2, 13);
					
					Thread thread = new Thread(sum_goals);
					thread.start();
					thread.join();
					
					nano = nano + sum_goals.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_2 sum_saves = new Mongo_Export_2(null, portiere, collection_2, 13);
					
					Thread thread = new Thread(sum_saves);
					thread.start();
					thread.join();
					
					nano = nano + sum_saves.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 3:		
    		
    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_3 sum_goals = new Mongo_Export_3(giocatore, null, collection_3, 13);
					
					Thread thread = new Thread(sum_goals);
					thread.start();
					thread.join();
					
					nano = nano + sum_goals.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_3 sum_saves = new Mongo_Export_3(null, portiere, collection_3, 13);
					
					Thread thread = new Thread(sum_saves);
					thread.start();
					thread.join();
					
					nano = nano + sum_saves.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    	
    		break;
    		
    	default:   		
    		break;
    		
    	}
    	
		}catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Sum().");
		}
		
		return ms;	
		
	}
	
	/**
	 * Funzione che calcola i millisecondi che ci vogliono per aggiungere un nuovo field ('mileage' per i giocatori, 'mistakes' per i portieri) all'ultima stagione (2019-2020), per tutte le configurazioni.
	 * @param all_gioc
	 * @param all_por
	 * @param configuration
	 * @return ms
	 */
	
	public double Add_field(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por, int configuration) {
		
		double ms = 0;
		
		final Mongo mongo = new Mongo();
		
		try {
		
		int count_gioc = 1;
        
    	double nano = 0;
    	
    	if(configuration == 1) {
    	
    	this.Delete_and_Insert(all_gioc, all_por, "Link calciatore");	/**Cancellazione databases e ricreazione databases con i nuovi documenti. */   
    	
    	}
    	
    	switch(configuration) {
    	
    	case 1:
    		
    		mongo.Connection("localhost", 27017, "FootballStats", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
			
            /** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export add_field = new Mongo_Export(giocatore, null, collection, 14);
					
					Thread thread = new Thread(add_field);
					thread.start();
					thread.join();
					
					nano = nano + add_field.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export add_field = new Mongo_Export(null, portiere, collection, 14);
					
					Thread thread = new Thread(add_field);
					thread.start();
					thread.join();
					
					nano = nano + add_field.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 2:
    		
    		mongo.Connection("localhost", 27017, "FootballStats_2", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_2 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_2 add_field = new Mongo_Export_2(giocatore, null, collection_2, 14);
					
					Thread thread = new Thread(add_field);
					thread.start();
					thread.join();
					
					nano = nano + add_field.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_2 add_field = new Mongo_Export_2(null, portiere, collection_2, 14);
					
					Thread thread = new Thread(add_field);
					thread.start();
					thread.join();
					
					nano = nano + add_field.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    		
    		break;
    		
    	case 3:		
    		
    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection_3 = mongo.getMongoCollection();
			
            	/** Ricerca giocatori. */
				for (Giocatore giocatore : all_gioc) {
					
					Mongo_Export_3 add_field = new Mongo_Export_3(giocatore, null, collection_3, 14);
					
					Thread thread = new Thread(add_field);
					thread.start();
					thread.join();
					
					nano = nano + add_field.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
				
				/** Ricerca portieri. */
				for (Portiere portiere : all_por) {
					
					Mongo_Export_3 add_field = new Mongo_Export_3(null, portiere, collection_3, 14);
					
					Thread thread = new Thread(add_field);
					thread.start();
					thread.join();
					
					nano = nano + add_field.getNano();
					
					count_gioc++;
					
				}
				/**--------------------------------------------*/
			
			ms = nano/1000000;
	    	
	    	mongo.Disconnection();
    	
    		break;
    		
    	default:   		
    		break;
    		
    	}
    	
		}catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Add_field().");
		}
		
		return ms;	
		
	}
	
	/**
	 * Funzione che cancella i databases e li ricrea inserendo i documenti di 'all_gioc' e 'all_por' in tutti e tre i database.
	 * @param all_gioc
	 * @param all_por
	 */
	
	public void Delete_and_Insert(ArrayList<Giocatore> all_gioc, ArrayList<Portiere> all_por, String field) {
		
			DB1 db1 = new DB1();
			DB2 db2 = new DB2();
			DB3 db3 = new DB3();
		
		try {
	    	
	    	this.Delete_DB(); 
	    	
	    	System.out.println("\nINSERIMENTO IN 'FootballStats' DI " + (all_gioc.size() + all_por.size()) + " CALCIATORI.\n");
	    	db1.Insert_Calciatori(all_gioc, all_por);
	    	db1.Create_index(field);
	    	
	    	System.out.println("\nINSERIMENTO IN 'FootballStats_2' DI " + (all_gioc.size() + all_por.size()) + " CALCIATORI.\n");
	    	db2.Insert_Calciatori(all_gioc, all_por);
	    	
	    	if(field.equals("Stagioni"))
	    		db2.Create_index("Ultima stagione");
	    	else
	    		db2.Create_index(field);
	    	
	    	System.out.println("\nINSERIMENTO IN 'FootballStats_3' DI " + (all_gioc.size() + all_por.size()) + " CALCIATORI.\n");
	    	db3.Insert_Calciatori(all_gioc, all_por);
	    	
	    	if(field.equals("Stagioni"))
	    		db3.Create_index("Ultima stagione");
	    	else
	    		db3.Create_index(field);
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Delete_and_Insert().");
		}
		
	}
	
	/**
	 * Funzione che cancella i database.
	 */
	
	public void Delete_DB() {
		
		Mongo mongo = new Mongo();
		
		System.out.println("CANCELLAZIONE DEI DATABASE.....\n");
		
		/** Cancellazione dei tre database. */
    	mongo.Drop_database("FootballStats");
    	mongo.Drop_database("FootballStats_2");
    	mongo.Drop_database("FootballStats_3");
    	/**---------------------------------*/
    	
	}
	
	/**
	 * Funzione che aggiunge a tutti i documenti la stessa stagione.
	 * @param all_gioc
	 * @param all_por
	 */
	
	public void Add_Same_Season(final ArrayList<Giocatore> all_gioc, final ArrayList<Portiere> all_por) {
		
		try {
			
			DB1 db1 = new DB1();
			DB2 db2 = new DB2();
			DB3 db3 = new DB3();
			
			System.out.println("AGGIUNTA IN 'FootballStats' DELLA STAGIONE IN COMUNE A TUTTI I CALCIATORI.\n");
			db1.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());
			
			System.out.println("AGGIUNTA IN 'FootballStats_2' DELLA STAGIONE IN COMUNE A TUTTI I CALCIATORI.\n");
			db2.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());
			
			System.out.println("AGGIUNTA IN 'FootballStats_3' DELLA STAGIONE IN COMUNE A TUTTI I CALCIATORI.\n");
			db3.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());
			
		} catch (Exception e) {
			System.out.println("Errore in Performance_evaluation() - Add_Same_Season().");
		}
		
	}
	
	
	/**
	 * Funzione che restituisce il documento da aggiungere a tutti i giocatori (stagione uguale per tutti).
	 * @return d_g
	 */
	
	public Document Standard_document_stagione_giocatore() {

		Document d_g = new Document();
		d_g.put("season", "2050-2051");
		d_g.put("age", 55);
		d_g.put("squad", "Ancona");
		d_g.put("country", "it ITA");
		d_g.put("comp_level", "Promozione italiana");
		d_g.put("lg_finish", "1st");
		d_g.put("games", 111);
        d_g.put("games_starts", 111);
        d_g.put("minutes", 111);
        d_g.put("goals", 111);
        d_g.put("assists", 111);
        d_g.put("pens_made", 111);
        d_g.put("pens_att", 111);
        d_g.put("cards_yellow", 111);
        d_g.put("cards_red", 111);
        d_g.put("goals_per90", 1.11);
        d_g.put("assists_per90", 1.11);
        d_g.put("goals_assists_per90", 1.11);
        d_g.put("goals_pens_per90", 1.11);
        d_g.put("goals_assists_pens_per90", 1.11);
        d_g.put("xg", 1.11);
        d_g.put("npxg", 1.11);
        d_g.put("xa", 1.11);
        d_g.put("xg_per90", 1.11);
        d_g.put("xa_per90", 1.11);
        d_g.put("xg_xa_per90", 1.11);
        d_g.put("npxg_per90", 1.11);
        d_g.put("npxg_xa_per90", 1.11);

        return d_g;

	}

	/**
	 * Funzione che restituisce il documento da aggiungere a tutti i portieri (stagione uguale per tutti).
	 * @return d_p
	 */

	public Document Standard_document_stagione_portiere() {

		Document d_p = new Document();
		d_p.put("season", "2050-2051");
		d_p.put("age", 55);
		d_p.put("squad", "Ancona");
		d_p.put("country", "it ITA");
		d_p.put("comp_level", "Promozione italiana");
		d_p.put("lg_finish", "1st");
        d_p.put("games_gk", 111);
        d_p.put("games_starts_gk",111);
        d_p.put("minutes_gk", 111);
        d_p.put("goals_against_gk", 111);
        d_p.put("goals_against_gk_per90", 1.11);
        d_p.put("shot_on_target_against", 111);
        d_p.put("saves", 111);
        d_p.put("save_pct", 1.11);
        d_p.put("wins_gk", 111);
        d_p.put("draws_gk", 111);
        d_p.put("losses_gk", 111);
        d_p.put("clean_sheets", 111);
        d_p.put("clean_sheets_pct", 1.11);
        d_p.put("pens_att_gk", 111);
        d_p.put("pens_allowed", 111);
        d_p.put("pens_saved", 111);
        d_p.put("pens_missed_gk", 111);

		return d_p;

	}

}

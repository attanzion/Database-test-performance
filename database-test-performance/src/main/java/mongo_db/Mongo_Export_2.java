package mongo_db;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import classi.Giocatore;
import classi.Portiere;
import classi.Stagione_Calciatore;
import classi.Stats;

public class Mongo_Export_2 implements Runnable{

	private Giocatore giocatore = null;
	private Portiere portiere = null;
	private MongoCollection<Document> collection = null;
	private int operazione = 0;
	private Document document = null;
	
	private long nano = 0;
	
	/**
	 * Costruttore 1 della classe 'Mongo_Export_2'.
	 * @param giocatore_
	 * @param portiere_
	 * @param collection_
	 * @param operazione_
	 */
	
	public Mongo_Export_2(Giocatore giocatore_, Portiere portiere_, MongoCollection<Document> collection_, int operazione_) {
		
		this.setGiocatore(giocatore_);
		this.setPortiere(portiere_);
		this.setCollection(collection_);
		this.setOperazione(operazione_);
		
	}
	
	/**
	 * Costruttore 2 della classe 'Mongo_Export_2'.
	 * @param giocatore_
	 * @param portiere_
	 * @param collection_
	 * @param operazione_
	 * @param document_
	 */
	
	public Mongo_Export_2(Giocatore giocatore_, Portiere portiere_, MongoCollection<Document> collection_, int operazione_, Document document_) {
		
		this.setGiocatore(giocatore_);
		this.setPortiere(portiere_);
		this.setCollection(collection_);
		this.setOperazione(operazione_);
		this.setDocument(document_);
		
	}
	
	/**
	 * CODICE ESEGUITO DAL THREAD.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		/** Lo switch sceglie quale operazione sul database 'FootballStats_2' eseguire in base al valore di 'operazione'. */
		switch (this.operazione) {
		
			case 1:			/** Operazione di INSERIMENTO. */				
				
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
		            

		    		long start = System.nanoTime();
					
		            collection.insertOne(doc_giocatore); 
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
		    		
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
		              		    		
			    		long start = System.nanoTime();
		  			
		               collection.insertOne(doc_portiere);
						
						long end = System.nanoTime();
						
						this.setNano(end - start);
		    	}
				
				break;
				
			case 2:
				
				if(portiere == null) {
					
						 long start = System.nanoTime(); 
						 
						 ArrayList<Document> doc_list = collection.find(eq("Link calciatore", this.giocatore.getLink_calciatore())).into(new ArrayList<Document>());
				         
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
					 
				         Document ultima_stagione = new Document();
				         Document penultima_stagione = new Document();
			            
			            for (Document d : doc_list) {
							
			            	ultima_stagione = (Document) d.get("Ultima stagione");
			            	penultima_stagione = (Document) d.get("Penultima stagione");
			            	
						}
			            
			            BasicDBObject updateFields = new BasicDBObject();
			            updateFields.append("Ultima stagione", this.document);
			            updateFields.append("Penultima stagione", ultima_stagione);
			            BasicDBObject setQuery = new BasicDBObject();
			            setQuery.append("$set", updateFields);
			            
			            start = System.nanoTime();
			            
			            collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()),  push("Stagioni", penultima_stagione));
			            collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()), setQuery);
			            
			            end = System.nanoTime();
			            
			            long parziale = this.nano + (end -start);
			            
			            this.setNano(parziale);
					
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
					ArrayList<Document> doc_list = collection.find(eq("Link calciatore", this.portiere.getLink_calciatore())).into(new ArrayList<Document>());
		            
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
		            Document ultima_stagione = new Document();
		            Document penultima_stagione = new Document();
		            
		            for (Document d : doc_list) {
						
		            	ultima_stagione = (Document) d.get("Ultima stagione");
		            	penultima_stagione = (Document) d.get("Penultima stagione");
		            	
					}
		            
		            BasicDBObject updateFields = new BasicDBObject();
		            updateFields.append("Ultima stagione", this.document);
		            updateFields.append("Penultima stagione", ultima_stagione);
		            BasicDBObject setQuery = new BasicDBObject();
		            setQuery.append("$set", updateFields);
		            
		            start = System.nanoTime();
		            
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), setQuery);
					
		            end = System.nanoTime();
		            
		            long parziale = this.nano + (end -start);
		            
		            this.setNano(parziale);
		            
				}
				
				break;
				
			case 3:		/** Operazione di UPDATE ULTIMA STAGIONE. */
				
				if(portiere == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore())), Updates.combine(Updates.set("Ultima stagione.games", 222),Updates.set("Ultima stagione.games_starts", 222)));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
			    	collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.combine(Updates.set("Ultima stagione.goals_against_gk", 222),Updates.set("Ultima stagione.saves", 222)));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
				}
				
				break;
				
			case 4:		/** Operazione di RICERCA CALCIATORE. */
				
				if(portiere == null) {
					
					long start = System.nanoTime();
					
					ArrayList<Document> doc_gioc = collection.find(eq("Nome", this.giocatore.getNome_calciatore())).into(new ArrayList<Document>());
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					if(doc_gioc.size() != 0) {
						
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Giocatore trovato." );
						
					} else {
						
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Giocatore non trovato." );
						
					}
					
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
					ArrayList<Document> doc_por = collection.find(eq("Nome", this.portiere.getNome_calciatore())).into(new ArrayList<Document>());
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					if(doc_por.size() != 0) {
						
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Portiere trovato." );
						
					} else {
						
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Portiere non trovato." );
						
					}
					
				}
				
				break;
				
			case 5:
				
				break;
				
			case 6:		/** Operazione di CALCOLO MEDIA. */
				
				int seasons = this.Count_season();
				
				if(portiere == null) {
					
					 	 Document match = new Document();
					 		  match.append("Nome", this.giocatore.getNome_calciatore());
					
					if(seasons == 1) {
						
						long start = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match)));
						 
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
						 
						 for (Document document : doc) {
							 
							 Document docu = (Document) document.get("Ultima stagione");
							
							 System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Media goals: " + docu.get("goals"));
							 
						}
						
					} else if(seasons == 2) {
						
						 Document proj = new Document();
						 	  proj.append("Nome", 1);
						 	  proj.append("runningGoals", new Document("$add", Arrays.asList("$Ultima stagione.goals", "$Penultima stagione.goals" )));
						
						 Document average = new Document();
						 	  average.append("avg_goals", new Document("$divide", Arrays.asList("$runningGoals", 2)));
						 
						 Document proj2 = new Document();
						 	  proj2.append("avg_goals", 1);
						 	  proj2.append("Nome", 1);
						 
						 long start = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match),
								 new Document("$project", proj),
								 new Document("$addFields", average),
								 new Document("$project", proj2)));
						 
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
						 
						 for (Document document : doc) {
							
							 System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Media goals: " + document.get("avg_goals"));
							 
						}
						
					} else if(seasons == 3) {
			
						 Document sums = new Document("arraySum", new Document("$sum", "$Stagioni.goals"));
						 sums.append("arrayCount", new Document("$size", "$Stagioni"));
				
						 Document proj = new Document();
						 proj.append("Nome", 1);
						 proj.append("runningGoals", new Document("$add", Arrays.asList("$arraySum", "$Ultima stagione.goals", "$Penultima stagione.goals" )));
						 proj.append("runningSeasons", new Document("$add", Arrays.asList("$arrayCount", 2)));
						 
						 Document average = new Document();
						 average.append("avg_goals", new Document("$divide", Arrays.asList("$runningGoals", "$runningSeasons")));
						 
						 Document proj2 = new Document();
						 proj2.append("avg_goals", 1);
						 proj2.append("Nome", 1);
						 
						 long start = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match),
								 new Document("$addFields", sums),
								 new Document("$project", proj),
								 new Document("$addFields", average),
								 new Document("$project", proj2)));
						 
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
						 
						 for (Document document : doc) {
							
							 System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Media goals: " + document.get("avg_goals"));
							 
						}
						 
						}
					
				} else if(giocatore == null) {
					
					Document match = new Document();
					 		 match.append("Nome", this.portiere.getNome_calciatore());
					
					if(seasons == 1) {
						
						long start = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match)));
						 
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
						 
						 for (Document document : doc) {
							 
							 Document docu = (Document) document.get("Ultima stagione");
							
							 System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Media goals: " + docu.get("saves"));
							 
						}
						
					} else if(seasons == 2) {
				
						Document proj = new Document();
								 proj.append("Nome", 1);
								 proj.append("runningSaves", new Document("$add", Arrays.asList("$Ultima stagione.saves", "$Penultima stagione.saves" )));
								 
								 
						Document average = new Document();
								 average.append("avg_saves", new Document("$divide", Arrays.asList("$runningSaves", 2)));
								 
						Document proj2 = new Document();
								 proj2.append("avg_saves", 1);
								 proj2.append("Nome", 1);
		
						long start = System.nanoTime();
						
						AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								new Document("$match", match),
								new Document("$project", proj),
								new Document("$addFields", average),
								new Document("$project", proj2)));
							
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
						 
						 for (Document document : doc) {
							
							 System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Media saves: " + document.get("avg_saves"));
							 
						}
						
					} else if(seasons == 3) {
						
						Document sums = new Document("arraySum", new Document("$sum", "$Stagioni.saves"));
								 sums.append("arrayCount", new Document("$size", "$Stagioni"));
						
						Document proj = new Document();
								 proj.append("Nome", 1);
								 proj.append("runningSaves", new Document("$add", Arrays.asList("$arraySum", "$Ultima stagione.saves", "$Penultima stagione.saves" )));
								 proj.append("runningSeasons", new Document("$add", Arrays.asList("$arrayCount", 2)));
								 
						Document average = new Document();
								 average.append("avg_saves", new Document("$divide", Arrays.asList("$runningSaves", "$runningSeasons")));
								 
						Document proj2 = new Document();
								 proj2.append("avg_saves", 1);
								 proj2.append("Nome", 1);
		
						long start = System.nanoTime();
						
						AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								new Document("$match", match),
								new Document("$addFields", sums),
								new Document("$project", proj),
								new Document("$addFields", average),
								new Document("$project", proj2)));
							
						 long end = System.nanoTime();
							
						 this.setNano(end - start);
						 
						 for (Document document : doc) {
							
							 System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Media saves: " + document.get("avg_saves"));
							 
						}
					}
					
				}
				
				break;
				
			case 7:
				break;
				
			case 8:		/** Operazione di CANCELLAZIONE CALCIATORE */
				
				if(portiere == null) {
					
					long start = System.nanoTime();
					
					this.collection.deleteOne(eq("Link calciatore", this.giocatore.getLink_calciatore()));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Cancellato dal database.");
					
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
					this.collection.deleteOne(eq("Link calciatore", this.portiere.getLink_calciatore()));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Cancellato dal database.");
					
				}
				
				break;
	
			default:
				break;
		}
		
	}
	
	/**
	 * Funzione che calcola il numero di stagioni di un calciatore.
	 * @param nome
	 * @return seasons
	 */
	
	@SuppressWarnings("unchecked")
	public int Count_season() {
		
		int seasons = 0;
			
		ArrayList<Document> docs = new ArrayList<Document>();
				
		if(portiere == null) {
			
			this.collection.find(eq("Nome", this.giocatore.getNome_calciatore())).into(docs);
			
		} else if(giocatore == null) {
			
			this.collection.find(eq("Nome", this.portiere.getNome_calciatore())).into(docs);
			
		}
		
		for (Document document : docs) {
			
			ArrayList<Document> stagioni = (ArrayList<Document>) document.get("Stagioni");
			Document pen = (Document) document.get("Penultima stagione");
			
			if(pen.isEmpty()) {
				
				seasons = 1;
				
			} else if(stagioni.size() == 0 && !pen.isEmpty()) {
				
				seasons = 2;
				
			} else {
				
				seasons = 3;
				
			}
			
		}
				
		
		return seasons;
		
	}
	
	/**
	 * Funzione che setta 'giocatore'.
	 * @param giocatore
	 */
	
	public void setGiocatore(Giocatore giocatore_) {
		
		this.giocatore = giocatore_;
		
	}
	
	/**
	 * Funzione che setta 'portiere'
	 * @param portiere
	 */
	
	public void setPortiere(Portiere portiere_) {
		
		this.portiere = portiere_;
		
	}
	
	/**
	 * Funzione che setta 'collection'.
	 * @param collection
	 */
	
	public void setCollection(MongoCollection<Document> collection_) {
		
		this.collection = collection_;
		
	}
	
	/**
	 * Funzione che setta 'operazione'.
	 * @param operazione
	 */
	
	public void setOperazione(int operazione_) {
		
		this.operazione = operazione_;
		
	}
	
	/**
	 * Funzione che setta 'nano'.
	 * @param nano
	 */
	
	public void setNano(long nano_) {
		
		this.nano = nano_;
		
	}
	
	/**
	 * Funzione che setta 'document'.
	 * @param document_
	 */
	
	public void setDocument(Document document_) {
		
		this.document = document_;
		
	}
	
	/**
	 * Funzione che ritorna 'giocatore'.
	 * @return giocatore
	 */
	
	public Giocatore getGiocatore() {
		
		return this.giocatore;
		
	}
	
	/**
	 * Funzione che ritorna 'portiere'.
	 * @return portiere
	 */
	
	public Portiere getPortiere() {
		
		return this.portiere;
		
	}
	
	/**
	 * Funzione che ritorna 'collection'.
	 * @return collection
	 */
	
	public MongoCollection<Document> getCollection() {
		
		return this.collection;
		
	}
	
	/**
	 * Funzione che ritorna 'operazione'.
	 * @return operazione
	 */
	
	public int getOperazione() {
		
		return this.operazione;
		
	}
	
	/**
	 * Funzione che ritorna 'nano'.
	 * @return nano
	 */
	
	public long getNano() {
		
		return this.nano;
		
	}
	
	/**
	 * Funzione che ritorna 'document'.
	 * @return document
	 */
	
	public Document getDocument() {
		
		return this.document;
		
	}
	
}

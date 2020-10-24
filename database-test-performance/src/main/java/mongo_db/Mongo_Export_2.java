package mongo_db;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
	private int random_goals = 0;
	private int random_saves = 0;
	private String random_squad = null;
	
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
	 * Costruttore 3 della classe 'Mongo_Export'.
	 * @param collection_
	 * @param operazione_
	 * @param random_goals_
	 * @param random_saves_
	 */
	
	public Mongo_Export_2(MongoCollection<Document> collection_, int operazione_, int random_goals_, int random_saves_) {
		
		this.setCollection(collection_);
		this.setOperazione(operazione_);
		this.setRandom_goals(random_goals_);
		this.setRandom_saves(random_saves_);
		
	}
	
	/**
	 * Costruttore 4 della classe 'Mongo_Export'.
	 * @param collection_
	 * @param operazione_
	 * @param random_squad_
	 */
	
	public Mongo_Export_2(MongoCollection<Document> collection_, int operazione_, String random_squad_) {
		
		this.setCollection(collection_);
		this.setOperazione(operazione_);
		this.setRandom_squad(random_squad_);
		
	}
	
	/**
	 * CODICE ESEGUITO DAL THREAD.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		try {
		
		/** Lo switch sceglie quale operazione sul database 'FootballStats_2' eseguire in base al valore di 'operazione'. */
		switch (this.operazione) {
		
			case 1:			/** Operazione di INSERIMENTO. */				
				
		    	if(this.portiere == null) {
		    		
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
		            

		    		long start1 = System.nanoTime();
					
		            collection.insertOne(doc_giocatore); 
					
					long end1 = System.nanoTime();
					
					this.setNano(end1 - start1);
		    		
		    	} else if(this.giocatore == null) {
		    		
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
		              		    		
			    		long start1 = System.nanoTime();
		  			
		               collection.insertOne(doc_portiere);
						
						long end1 = System.nanoTime();
						
						this.setNano(end1 - start1);
		    	}
				
				break;
				
			case 2:		/** Operazione di UPDATE NUOVA STAGIONE. */
				
				if(this.portiere == null) {
					
						 long start2 = System.nanoTime(); 
						 
						 ArrayList<Document> doc_list = collection.find(eq("Link calciatore", this.giocatore.getLink_calciatore())).into(new ArrayList<Document>());
				         
						 long end2 = System.nanoTime();
							
						 this.setNano(end2 - start2);
					 
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
			            
			            start2 = System.nanoTime();
			            
			            collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()),  push("Stagioni", penultima_stagione));
			            collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()), setQuery);
			            
			            end2 = System.nanoTime();
			            
			            long parziale2 = this.nano + (end2 -start2);
			            
			            this.setNano(parziale2);
					
				} else if(this.giocatore == null) {
					
					long start2 = System.nanoTime();
					
					ArrayList<Document> doc_list = collection.find(eq("Link calciatore", this.portiere.getLink_calciatore())).into(new ArrayList<Document>());
		            
					long end2 = System.nanoTime();
					
					this.setNano(end2 - start2);
					
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
		            
		            start2 = System.nanoTime();
		            
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), setQuery);
					
		            end2 = System.nanoTime();
		            
		            long parziale2 = this.nano + (end2 -start2);
		            
		            this.setNano(parziale2);
		            
				}
				
				break;
				
			case 3:		/** Operazione di UPDATE ULTIMA STAGIONE. */
				
				if(this.portiere == null) {
					
					long start3 = System.nanoTime();
					
					collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore())), Updates.combine(Updates.set("Ultima stagione.games", 222),Updates.set("Ultima stagione.games_starts", 222)));
					
					long end3 = System.nanoTime();
					
					this.setNano(end3 - start3);
					
				} else if(this.giocatore == null) {
					
					long start3 = System.nanoTime();
					
			    	collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.combine(Updates.set("Ultima stagione.goals_against_gk", 222),Updates.set("Ultima stagione.saves", 222)));
					
					long end3 = System.nanoTime();
					
					this.setNano(end3 - start3);
					
				}
				
				break;
				
			case 4:		/** Operazione di RICERCA CALCIATORE. */
				
				if(this.portiere == null) {
					
					long start4 = System.nanoTime();
					
					ArrayList<Document> doc_gioc = collection.find(eq("Nome", this.giocatore.getNome_calciatore())).into(new ArrayList<Document>());
					
					long end4= System.nanoTime();
					
					this.setNano(end4 - start4);
					
				} else if(this.giocatore == null) {
					
					long start4 = System.nanoTime();
					
					ArrayList<Document> doc_por = collection.find(eq("Nome", this.portiere.getNome_calciatore())).into(new ArrayList<Document>());
					
					long end4 = System.nanoTime();
					
					this.setNano(end4 - start4);
					
				}
				
				break;
				
			case 5:		/** Operazione di RICERCA DI UNA STATISTICA DI UN CALCIATORE in una certa stagione. */
				
				if(this.portiere == null) {
					
					Document match = new Document();
					 match.append("Link calciatore", this.giocatore.getLink_calciatore());
					 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Ultima stagione.minutes", 1);
							 
					long start5 = System.nanoTime();
							 
					AggregateIterable<Document> doc = collection.aggregate(Arrays.asList(
												new Document("$match", match),
												new Document("$project", proj)));
					
					long end5 = System.nanoTime();
					
					this.setNano(end5 - start5);
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
					 match.append("Link calciatore", this.portiere.getLink_calciatore());
					 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Ultima stagione.minutes_gk", 1);
							 
					long start5 = System.nanoTime();
							 
					AggregateIterable<Document> doc = collection.aggregate(Arrays.asList(
												new Document("$match", match),
												new Document("$project", proj)));
					
					long end5 = System.nanoTime();
					
					this.setNano(end5 - start5);
					
				}
				
				break;
				
			case 6:		/** Operazione di CALCOLO MEDIA. */
				
				int seasons = this.Count_season();
				
				if(this.portiere == null) {
					
					 	 Document match = new Document();
					 	 		match.append("Link calciatore", this.giocatore.getLink_calciatore());
					
					if(seasons == 1) {
						
						long start6 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match)));
						 
						 long end6 = System.nanoTime();
							
						 this.setNano(end6 - start6);
						
					} else if(seasons == 2) {
						
						 Document proj = new Document();
						 	  proj.append("Nome", 1);
						 	  proj.append("runningGoals", new Document("$add", Arrays.asList("$Ultima stagione.goals", "$Penultima stagione.goals" )));
						
						 Document average = new Document();
						 	  average.append("avg_goals", new Document("$divide", Arrays.asList("$runningGoals", 2)));
						 
						 Document proj2 = new Document();
						 	  proj2.append("avg_goals", 1);
						 	  proj2.append("Nome", 1);
						 
						 long start6 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match),
								 new Document("$project", proj),
								 new Document("$addFields", average),
								 new Document("$project", proj2)));
						 
						 long end6 = System.nanoTime();
							
						 this.setNano(end6 - start6);
						
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
						 
						 long start6 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match),
								 new Document("$addFields", sums),
								 new Document("$project", proj),
								 new Document("$addFields", average),
								 new Document("$project", proj2)));
						 
						 long end6 = System.nanoTime();
							
						 this.setNano(end6 - start6);
						 
						}
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
						match.append("Link calciatore", this.portiere.getLink_calciatore());
					
					if(seasons == 1) {
						
						long start6 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match)));
						 
						 long end6 = System.nanoTime();
							
						 this.setNano(end6 - start6);
						
					} else if(seasons == 2) {
				
						Document proj = new Document();
								 proj.append("Nome", 1);
								 proj.append("runningSaves", new Document("$add", Arrays.asList("$Ultima stagione.saves", "$Penultima stagione.saves" )));
								 
								 
						Document average = new Document();
								 average.append("avg_saves", new Document("$divide", Arrays.asList("$runningSaves", 2)));
								 
						Document proj2 = new Document();
								 proj2.append("avg_saves", 1);
								 proj2.append("Nome", 1);
		
						long start6 = System.nanoTime();
						
						AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								new Document("$match", match),
								new Document("$project", proj),
								new Document("$addFields", average),
								new Document("$project", proj2)));
							
						 long end6 = System.nanoTime();
							
						 this.setNano(end6 - start6);
						
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
		
						long start6 = System.nanoTime();
						
						AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								new Document("$match", match),
								new Document("$addFields", sums),
								new Document("$project", proj),
								new Document("$addFields", average),
								new Document("$project", proj2)));
							
						 long end6 = System.nanoTime();
							
						 this.setNano(end6 - start6);
					}
					
				}
				
				break;
				
			case 7:		/** Operazione di CANCELLAZIONE DI UN CAMPO di una stagione */
				
				if(portiere == null) {
					
					Document filter = new Document();
					 filter.append("Link calciatore", this.giocatore.getLink_calciatore());
					 
					Document update = new Document();
							 update.append("$unset", new Document("Ultima stagione.npxg_xa_per90", 1));
							 
					long start7 = System.nanoTime();
							 
					collection.updateOne(filter, update);
					
					long end7 = System.nanoTime();
					
					this.setNano(end7 - start7);
					
				} else if(giocatore == null) {
					
					Document filter = new Document();
					 filter.append("Link calciatore", this.portiere.getLink_calciatore());
					 
					Document update = new Document();
							 update.append("$unset", new Document("Ultima stagione.goals_against_gk_per90", 1));
				
					long start7 = System.nanoTime();
							 
					collection.updateOne(filter, update);
					
					long end7 = System.nanoTime();
					
					this.setNano(end7 - start7);
					
				}
				
				break;
				
			case 8:		/** Operazione di CANCELLAZIONE CALCIATORE */
				
				if(this.portiere == null) {
					
					long start8 = System.nanoTime();
					
					this.collection.deleteOne(eq("Link calciatore", this.giocatore.getLink_calciatore()));
					
					long end8 = System.nanoTime();
					
					this.setNano(end8 - start8);
					
				} else if(this.giocatore == null) {
					
					long start8 = System.nanoTime();
					
					this.collection.deleteOne(eq("Link calciatore", this.portiere.getLink_calciatore()));
					
					long end8 = System.nanoTime();
					
					this.setNano(end8 - start8);
					
				}
				
				break;
				
			case 9:		/** Operazione di CANCELLAZIONE DI UNA STAGIONE.*/
				
				int num_s = this.Count_season();				
				
				if(this.portiere == null) {
					
					Document pen_s = null; 		/** Documento penultima stagione. */
					Document ter_s = null;		/** Documento terzultima stagione. */
					
					Document filter = new Document();
							 filter.append("Link calciatore", this.giocatore.getLink_calciatore());
					 
					Document update = new Document();
							 update.append("$set", new Document("Ultima stagione", null));
					
					Document pull = new Document();
							 pull.append("$pull", new Document("Stagioni", new Document("season", "2017-2018")));
							 
					if(num_s == 1) {
						
						long start9 = System.nanoTime();
						 
						this.collection.updateOne(filter, update);
						
						long end9 = System.nanoTime();
						
						this.setNano(end9 - start9);
						
					} else if(num_s == 2) {
						
						 long start9 = System.nanoTime();
						
						ArrayList<Document> doc_c = this.collection.find(filter).into(new ArrayList<Document>());
						
						long end9 = System.nanoTime();
						
						long parziale9 = (end9 - start9);
						
						for (Document document : doc_c) {
							
							pen_s = (Document) document.get("Penultima stagione");
							
						}
						
						start9 = System.nanoTime();
						
						this.collection.updateOne(filter, Updates.combine(Updates.set("Ultima stagione", pen_s), Updates.set("Penultima stagione", null)));
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						this.setNano(parziale9);
						
					} else if(num_s == 3) {
					
						long start9 = System.nanoTime();
						 
						this.collection.updateOne(filter, update);
						
						long end9 = System.nanoTime();
						
						long parziale9 = end9 - start9;
						
						start9 = System.nanoTime();
						
						ArrayList<Document> doc_c = this.collection.find(filter).into(new ArrayList<Document>());
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						for (Document document : doc_c) {
							
							pen_s = (Document) document.get("Penultima stagione");
							
							ArrayList<Document> doc_s = (ArrayList<Document>) document.get("Stagioni");
							
							/** Scorrimento documenti 'Stagioni'.*/
							for (Document document2 : doc_s) {
								
								if(document2.getString("season").equals("2017-2018")) {
									
									ter_s = document2;
									
								}
								
							}
							/**------------------------------------*/
							
						}
						
						start9 = System.nanoTime();
						
						this.collection.updateOne(filter, Updates.combine(Updates.set("Ultima stagione", pen_s), Updates.set("Penultima stagione", ter_s), pull));
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						this.setNano(parziale9);
					
					}
					
				} else if(this.giocatore == null) {
					
					Document pen_s = null; 		/** Documento penultima stagione. */
					Document ter_s = null;		/** Documento terzultima stagione. */
					
					Document filter = new Document();
					 filter.append("Link calciatore", this.portiere.getLink_calciatore());
			 
					Document update = new Document();
							 update.append("$set", new Document("Ultima stagione", null));
					
					Document pull = new Document();
							 pull.append("$pull", new Document("Stagioni", new Document("season", "2017-2018")));
							 
					if(num_s == 1) {
						
						long start9 = System.nanoTime();
						 
						this.collection.updateOne(filter, update);
						
						long end9 = System.nanoTime();
						
						this.setNano(end9 - start9);
						
					} else if(num_s == 2) {
						
						 long start9 = System.nanoTime();
						
						ArrayList<Document> doc_c = this.collection.find(filter).into(new ArrayList<Document>());
						
						long end9 = System.nanoTime();
						
						long parziale9 = (end9 - start9);
						
						for (Document document : doc_c) {
							
							pen_s = (Document) document.get("Penultima stagione");
							
						}
						
						start9 = System.nanoTime();
						
						this.collection.updateOne(filter, Updates.combine(Updates.set("Ultima stagione", pen_s), Updates.set("Penultima stagione", null)));
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						this.setNano(parziale9);
						
					} else if(num_s == 3) {
					
						long start9 = System.nanoTime();
						 
						this.collection.updateOne(filter, update);
						
						long end9 = System.nanoTime();
						
						long parziale9 = end9 - start9;
						
						start9 = System.nanoTime();
						
						ArrayList<Document> doc_c = this.collection.find(filter).into(new ArrayList<Document>());
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						
						for (Document document : doc_c) {
							
							pen_s = (Document) document.get("Penultima stagione");
							
							ArrayList<Document> doc_s = (ArrayList<Document>) document.get("Stagioni");
							
							/** Scorrimento documenti 'Stagioni'.*/	
							for (Document document2 : doc_s) {
								
								if(document2.getString("season").equals("2017-2018")) {
									
									ter_s = document2;
									
								}
								
							}
							/**------------------------------------*/
							
						}
							
						
						start9 = System.nanoTime();
						
						this.collection.updateOne(filter, Updates.combine(Updates.set("Ultima stagione", pen_s), Updates.set("Penultima stagione", ter_s), pull));
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9- start9);
						
						this.setNano(parziale9);
					
					}
					
				}
				
				break;
				
			case 10:		/** Operazione di RICERCA GIOCATORE con una statistica maggiore di un certo valore in una data stagione. */
				
				Document filter_gioc = new Document();
				 		 filter_gioc.append("Ultima stagione.goals", new Document("$gt", this.random_goals));
				 
				Document filter_por = new Document();
				 		 filter_por.append("Ultima stagione.saves", new Document("$gt", this.random_saves));
				 
				 long start10 = System.nanoTime();
				 		 
				 ArrayList<Document> doc_g = collection.find(filter_gioc).into(new ArrayList<Document>());
				 ArrayList<Document> doc_p = collection.find(filter_por).into(new ArrayList<Document>());
				 
				 long end10 = System.nanoTime();
				 
				 this.setNano(end10 - start10);
				
				break;
				
			case 11:		/**Operazione di RICERCA TRAMITE SQUADRA in una cera stagione.*/
				
				Document filter_squad = new Document();
		 		 		 filter_squad.append("Ultima stagione.squad", this.random_squad);		 
				 
				 long start11 = System.nanoTime();
				 		 
				 ArrayList<Document> doc_c = collection.find(filter_squad).into(new ArrayList<Document>());
				 			 
				 long end11 = System.nanoTime();
				 
				 this.setNano(end11 - start11);
				
				break;
				
			case 12:		/** Operazione di VISUALIZZAZIONE DI UNA STAGIONE. */
				
				if(portiere == null) {
					
					Document match = new Document();
							 match.append("Link calciatore", this.giocatore.getLink_calciatore());
							 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Ultima stagione", 1);
					
					long start12 = System.nanoTime();
							 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
																								new Document("$match", match),
																								new Document("$project", proj)));
					
					long end12 = System.nanoTime();
					 
					this.setNano(end12 - start12);
							 
				}else if(giocatore == null){
					
					Document match = new Document();
					 match.append("Link calciatore", this.portiere.getLink_calciatore());
					 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Ultima stagione", 1);
					
					long start12 = System.nanoTime();
							 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
																								new Document("$match", match),
																								new Document("$project", proj)));
					
					long end12 = System.nanoTime();
					 
					this.setNano(end12 - start12);
				
				}
				
				break;
				
			case 13:		/** Operazione CALCOLO SOMMA di una statistica per tutte le stagioni. */
				
				int seasons_ = this.Count_season();
				
				if(this.portiere == null) {
					
					 	 Document match = new Document();
					 		  match.append("Link calciatore", this.giocatore.getLink_calciatore());
					
					if(seasons_ == 1) {
						
						long start13 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match)));
						 
						 long end13 = System.nanoTime();
							
						 this.setNano(end13 - start13);
						
					} else if(seasons_ == 2) {
						
						 Document proj = new Document();
						 	  proj.append("Nome", 1);
						 	  proj.append("sum_goals", new Document("$add", Arrays.asList("$Ultima stagione.goals", "$Penultima stagione.goals" )));
						 
						 long start13 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match),
								 new Document("$project", proj)));
						 
						 long end13 = System.nanoTime();
							
						 this.setNano(end13 - start13);
						
					} else if(seasons_ == 3) {
			
						 Document sums = new Document("arraySum", new Document("$sum", "$Stagioni.goals"));
				
						 Document proj = new Document();
						 proj.append("Nome", 1);
						 proj.append("sum_goals", new Document("$add", Arrays.asList("$arraySum", "$Ultima stagione.goals", "$Penultima stagione.goals" )));
						 
						 long start13 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match),
								 new Document("$addFields", sums),
								 new Document("$project", proj)));
						 
						 long end13 = System.nanoTime();
							
						 this.setNano(end13 - start13);
						 
						}
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
					 		 match.append("Link calciatore", this.portiere.getLink_calciatore());
					
					if(seasons_ == 1) {
						
						long start13 = System.nanoTime();
						 
						 AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								 new Document("$match", match)));
						 
						 long end13 = System.nanoTime();
							
						 this.setNano(end13 - start13);
						
					} else if(seasons_ == 2) {
				
						Document proj = new Document();
								 proj.append("Nome", 1);
								 proj.append("sum_saves", new Document("$add", Arrays.asList("$Ultima stagione.saves", "$Penultima stagione.saves" )));
		
						long start13 = System.nanoTime();
						
						AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								new Document("$match", match),
								new Document("$project", proj)));
							
						 long end13 = System.nanoTime();
							
						 this.setNano(end13 - start13);
						
					} else if(seasons_ == 3) {
						
						Document sums = new Document("arraySum", new Document("$sum", "$Stagioni.saves"));
						
						Document proj = new Document();
								 proj.append("Nome", 1);
								 proj.append("sum_saves", new Document("$add", Arrays.asList("$arraySum", "$Ultima stagione.saves", "$Penultima stagione.saves" )));
							
						long start13 = System.nanoTime();
						
						AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
								new Document("$match", match),
								new Document("$addFields", sums),
								new Document("$project", proj)));
							
						 long end13 = System.nanoTime();
							
						 this.setNano(end13 - start13);
						 
					}
					
				}
				
				break;
				
			case 14:		/** Operazione AGGIUNTA NUOVO FIELD	*/
				
				if(portiere == null) {
					
					long start14 = System.nanoTime();
					
					this.collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()), Updates.set("Ultima stagione.mileage", 222));
					
					long end14 = System.nanoTime();
					
					this.setNano(end14 - start14);
					
				} else if(giocatore == null) {
					
					long start14 = System.nanoTime();
					
					this.collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.set("Ultima stagione.mistakes", 222));
					
					long end14 = System.nanoTime();
					
					this.setNano(end14 - start14);
					
				} 
				
				break;
	
			default:
				break;
		}
		
	}
	catch (Exception e) {
		System.out.println(e);
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
	
	/**
	 * Funzione che setta 'random_goals'.
	 * @param random_goals
	 */
	
	public void setRandom_goals(int random_goals_) {
		this.random_goals = random_goals_;
	}

	/**
	 * Funzione che setta 'random_saves'.
	 * @param random_saves
	 */
	
	public void setRandom_saves(int random_saves_) {
		this.random_saves = random_saves_;
	}
	
	/**
	 * Funzione che setta 'random_squad'.
	 * @param random_squad 
	 */
	
	public void setRandom_squad(String random_squad) {
		this.random_squad = random_squad;
	}
	
}

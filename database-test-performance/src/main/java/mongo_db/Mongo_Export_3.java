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

public class Mongo_Export_3 implements Runnable {
	
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
	 * Costruttore 1 della classe 'Mongo_Export_3'.
	 * @param giocatore
	 * @param portiere
	 * @param collection
	 * @param operazione
	 */
	
	public Mongo_Export_3(Giocatore giocatore_, Portiere portiere_, MongoCollection<Document> collection_, int operazione_) {
		
		this.setGiocatore(giocatore_);
		this.setPortiere(portiere_);
		this.setCollection(collection_);
		this.setOperazione(operazione_);
		
	}
	
	/**
	 * Costruttore 2 della classe 'Mongo_Export_3'.
	 * @param giocatore_
	 * @param portiere_
	 * @param collection_
	 * @param operazione_
	 * @param document_
	 */
	
	public Mongo_Export_3(Giocatore giocatore_, Portiere portiere_, MongoCollection<Document> collection_, int operazione_, Document document_) {
		
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
	
	public Mongo_Export_3(MongoCollection<Document> collection_, int operazione_, int random_goals_, int random_saves_) {
		
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
	
	public Mongo_Export_3(MongoCollection<Document> collection_, int operazione_, String random_squad_) {
		
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
		
		/** Lo switch sceglie quale operazione sul database 'FootballStats_3' eseguire in base al valore di 'operazione'. */
		switch (this.operazione) {
		
			case 1:			/** Operazione di INSERIMENTO. */
				
				if(this.portiere == null) {
					
		    		ArrayList<Integer> games = new ArrayList<Integer>();
					ArrayList<Integer> games_starts = new ArrayList<Integer>();
					ArrayList<Integer> minutes = new ArrayList<Integer>();
					ArrayList<Integer> goals = new ArrayList<Integer>();
					ArrayList<Integer> assists = new ArrayList<Integer>();
					ArrayList<Integer> pens_made = new ArrayList<Integer>();
					ArrayList<Integer> pens_att = new ArrayList<Integer>();
					ArrayList<Integer> cards_yellow = new ArrayList<Integer>();
					ArrayList<Integer> cards_red = new ArrayList<Integer>();
					 
					ArrayList<Double> goals_per90 = new ArrayList<Double>();
					ArrayList<Double> assists_per90 = new ArrayList<Double>();
					ArrayList<Double> goals_assists_per90 = new ArrayList<Double>();
					ArrayList<Double> goals_pens_per90 = new ArrayList<Double>();
					ArrayList<Double> goals_assists_pens_per90 = new ArrayList<Double>();
					ArrayList<Double> xg = new ArrayList<Double>();
					ArrayList<Double> npxg = new ArrayList<Double>();
					ArrayList<Double> xa = new ArrayList<Double>();
					ArrayList<Double> xg_per90 = new ArrayList<Double>();
					ArrayList<Double> xa_per90 = new ArrayList<Double>();
					ArrayList<Double> xg_xa_per90 = new ArrayList<Double>();
					ArrayList<Double> npxg_per90 = new ArrayList<Double>();
					ArrayList<Double> npxg_xa_per90 = new ArrayList<Double>();
				
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
		                     	
		                     	if (s.getNome_stat().equals("games")) {
		                     		
		                     		games.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("games_starts")) {
									
									games_starts.add(s.getInt_stat());

								} else if(s.getNome_stat().equals("minutes")) {
									
									minutes.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("goals")) {
									
									goals.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("assists")) {
									
									assists.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("pens_made")) {
									
									pens_made.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("pens_att")) {
									
									pens_att.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("cards_yellow")) {
									
									cards_yellow.add(s.getInt_stat());
									
								} else if(s.getNome_stat().equals("cards_red")) {
									
									cards_red.add(s.getInt_stat());
									
								}
		                     	
		                     } else if(s.getType().equals("double")) {
		                     	
		                     	doc_stats.put(s.getNome_stat(), s.getDouble_stat());
		                     	
		                     	if (s.getNome_stat().equals("goals_per90")) {
		                     		
		                     		goals_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("assists_per90")) {
									
									assists_per90.add(s.getDouble_stat());

								} else if(s.getNome_stat().equals("goals_assists_per90")) {
									
									goals_assists_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("goals_pens_per90")) {
									
									goals_pens_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("goals_assists_pens_per90")) {
									
									goals_assists_pens_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("xg")) {
									
									xg.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("npxg")) {
									
									npxg.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("xa")) {
									
									xa.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("xg_per90")) {
									
									xg_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("xa_per90")) {
									
									xa_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("xg_xa_per90")) {
									
									xg_xa_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("npxg_per90")) {
									
									npxg_per90.add(s.getDouble_stat());
									
								} else if(s.getNome_stat().equals("npxg_xa_per90")) {
									
									npxg_xa_per90.add(s.getDouble_stat());
									
								}
		                     	
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
		             doc_giocatore.put("games", games);
		             doc_giocatore.put("games_starts", games_starts);
		             doc_giocatore.put("minutes", minutes);
		             doc_giocatore.put("goals", goals);
		             doc_giocatore.put("assists", assists);
		             doc_giocatore.put("pens_made", pens_made);
		             doc_giocatore.put("pens_att", pens_att);
		             doc_giocatore.put("cards_yellow", cards_yellow);
		             doc_giocatore.put("cards_red", cards_red);
		             doc_giocatore.put("goals_per90", goals_per90);
		             doc_giocatore.put("assists_per90", assists_per90);
		             doc_giocatore.put("goals_assists_per90", goals_assists_per90);
		             doc_giocatore.put("goals_pens_per90", goals_pens_per90);
		             doc_giocatore.put("goals_assists_pens_per90", goals_assists_pens_per90);
		             doc_giocatore.put("xg", xg);
		             doc_giocatore.put("npxg", npxg);
		             doc_giocatore.put("xa", xa);
		             doc_giocatore.put("xg_per90", xg_per90);
		             doc_giocatore.put("xa_per90", xa_per90);
		             doc_giocatore.put("xg_xa_per90", xg_xa_per90);
		             doc_giocatore.put("npxg_per90", npxg_per90);
		             doc_giocatore.put("npxg_xa_per90", npxg_xa_per90);
		             doc_giocatore.put("Stagioni", stagioni);
		             
		             long start1 = System.nanoTime();
					
		             collection.insertOne(doc_giocatore);
						
						long end1 = System.nanoTime();
						
						this.setNano(end1 - start1);
						
		    	} else if(this.giocatore == null) {		
		    		
		    		
		    		 ArrayList<Integer> games_gk = new ArrayList<Integer>();
					 ArrayList<Integer> games_starts_gk = new ArrayList<Integer>();
					 ArrayList<Integer> minutes_gk = new ArrayList<Integer>();
					 ArrayList<Integer> goals_against_gk = new ArrayList<Integer>();
					 ArrayList<Integer> shot_on_target_against = new ArrayList<Integer>();
					 ArrayList<Integer> saves = new ArrayList<Integer>();
					 ArrayList<Integer> wins_gk = new ArrayList<Integer>();
					 ArrayList<Integer> draws_gk = new ArrayList<Integer>();
					 ArrayList<Integer> losses_gk = new ArrayList<Integer>();
					 ArrayList<Integer> clean_sheets = new ArrayList<Integer>();
					 ArrayList<Integer> pens_att_gk = new ArrayList<Integer>();
					 ArrayList<Integer> pens_allowed = new ArrayList<Integer>();
					 ArrayList<Integer> pens_saved = new ArrayList<Integer>();
					 ArrayList<Integer> pens_missed_gk = new ArrayList<Integer>();
					 
					 ArrayList<Double> goals_against_gk_per90 = new ArrayList<Double>();
					 ArrayList<Double> save_pct = new ArrayList<Double>();
					 ArrayList<Double> clean_sheets_pct = new ArrayList<Double>();
					
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
		                       	
		                       	if (s.getNome_stat().equals("games_gk")) {
		                        		
		                        		games_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("games_starts_gk")) {
										
										games_starts_gk.add(s.getInt_stat());

									} else if(s.getNome_stat().equals("minutes_gk")) {
										
										minutes_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("goals_against_gk")) {
										
										goals_against_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("shot_on_target_against")) {
										
										shot_on_target_against.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("saves")) {
										
										saves.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("wins_gk")) {
										
										wins_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("draws_gk")) {
										
										draws_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("losses_gk")) {
										
										losses_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("clean_sheets")) {
										
										clean_sheets.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("pens_att_gk")) {
										
										pens_att_gk.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("pens_allowed")) {
										
										pens_allowed.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("pens_saved")) {
										
										pens_saved.add(s.getInt_stat());
										
									} else if(s.getNome_stat().equals("pens_missed_gk")) {
										
										pens_missed_gk.add(s.getInt_stat());
										
									}
		                       	
		                       } else if(s.getType().equals("double")) {
		                       	
		                       	doc_stats.put(s.getNome_stat(), s.getDouble_stat());
		                       	
		                       	if (s.getNome_stat().equals("goals_against_gk_per90")) {
		                        		
		                       		goals_against_gk_per90.add(s.getDouble_stat());
										
									} else if(s.getNome_stat().equals("save_pct")) {
										
										save_pct.add(s.getDouble_stat());

									} else if(s.getNome_stat().equals("clean_sheets_pct")) {
										
										clean_sheets_pct.add(s.getDouble_stat());
										
									}
		                       	
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
		               doc_portiere.put("games_gk", games_gk);
		               doc_portiere.put("games_starts_gk", games_starts_gk);
		               doc_portiere.put("minutes_gk", minutes_gk);
		               doc_portiere.put("goals_against_gk", goals_against_gk);
		               doc_portiere.put("goals_against_gk_per90", goals_against_gk_per90);
		               doc_portiere.put("shot_on_target_against", shot_on_target_against);
		               doc_portiere.put("saves", saves);
		               doc_portiere.put("save_pct", save_pct);
		               doc_portiere.put("wins_gk", wins_gk);
		               doc_portiere.put("draws_gk", draws_gk);
		               doc_portiere.put("losses_gk", losses_gk);
		               doc_portiere.put("clean_sheets", clean_sheets);
		               doc_portiere.put("clean_sheets_pct", clean_sheets_pct);
		               doc_portiere.put("pens_att_gk", pens_att_gk);
		               doc_portiere.put("pens_allowed", pens_allowed);
		               doc_portiere.put("pens_saved", pens_saved);
		               doc_portiere.put("pens_missed_gk", pens_missed_gk);
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
		            
		            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), setQuery);
		            
		            end2 = System.nanoTime();
		            
		            long parziale2 = this.nano + (end2 - start2);
		            
		            this.setNano(parziale2);
		            		            
		            BasicDBObject updateArray = new BasicDBObject();
		            updateArray.append("games", this.document.getInteger("games"));
		            updateArray.append("games_starts", this.document.getInteger("games_starts"));
		            updateArray.append("minutes", this.document.getInteger("minutes"));
		            updateArray.append("goals", this.document.getInteger("goals"));
		            updateArray.append("assists", this.document.getInteger("assists"));
		            updateArray.append("pens_made", this.document.getInteger("pens_made"));
		            updateArray.append("pens_att", this.document.getInteger("pens_att"));
		            updateArray.append("cards_yellow", this.document.getInteger("cards_yellow"));
		            updateArray.append("cards_red", this.document.getInteger("cards_red"));
		            updateArray.append("goals_per90", this.document.getDouble("goals_per90"));
		            updateArray.append("assists_per90", this.document.getDouble("assists_per90"));
		            updateArray.append("goals_assists_per90", this.document.getDouble("goals_assists_per90"));
		            updateArray.append("goals_pens_per90", this.document.getDouble("goals_pens_per90"));
		            updateArray.append("goals_assists_pens_per90", this.document.getDouble("goals_assists_pens_per90"));
		            updateArray.append("xg", this.document.getDouble("xg"));
		            updateArray.append("npxg", this.document.getDouble("npxg"));
		            updateArray.append("xa", this.document.getDouble("xa"));
		            updateArray.append("xg_per90", this.document.getDouble("xg_per90"));
		            updateArray.append("xa_per90", this.document.getDouble("xa_per90"));
		            updateArray.append("xg_xa_per90", this.document.getDouble("xg_xa_per90"));
		            updateArray.append("npxg_per90", this.document.getDouble("npxg_per90"));
		            updateArray.append("npxg_xa_per90", this.document.getDouble("npxg_xa_per90"));
		            
		            BasicDBObject setQuery_array = new BasicDBObject();
		            setQuery_array.append("$push", updateArray);
		            
		            start2 = System.nanoTime();
		            
		            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), setQuery_array);
		            
		            end2 = System.nanoTime();
		            
		            parziale2 = this.nano + (end2 - start2);
		            
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
		            
		            collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), setQuery);
		            
		            end2 = System.nanoTime();
		            
		            long parziale = this.nano + (start2 - end2);
		            
		            this.setNano(parziale);
		            
		            BasicDBObject updateArray = new BasicDBObject();
		            updateArray.append("games_gk", this.document.getInteger("games_gk"));
		            updateArray.append("games_starts_gk", this.document.getInteger("games_starts_gk"));
		            updateArray.append("minutes_gk", this.document.getInteger("minutes_gk"));
		            updateArray.append("goals_against_gk", this.document.getInteger("goals_against_gk"));
		            updateArray.append("shot_on_target_against", this.document.getInteger("shot_on_target_against"));
		            updateArray.append("saves", this.document.getInteger("saves"));
		            updateArray.append("wins_gk", this.document.getInteger("wins_gk"));
		            updateArray.append("draws_gk", this.document.getInteger("draws_gk"));
		            updateArray.append("losses_gk", this.document.getInteger("losses_gk"));
		            updateArray.append("clean_sheets", this.document.getInteger("clean_sheets"));
		            updateArray.append("pens_att_gk", this.document.getInteger("pens_att_gk"));
		            updateArray.append("pens_allowed", this.document.getInteger("pens_allowed"));
		            updateArray.append("pens_saved", this.document.getInteger("pens_saved"));
		            updateArray.append("pens_missed_gk", this.document.getInteger("pens_missed_gk"));
		            updateArray.append("goals_against_gk_per90", this.document.getDouble("goals_against_gk_per90"));
		            updateArray.append("save_pct", this.document.getDouble("save_pct"));
		            updateArray.append("clean_sheets_pct", this.document.getDouble("clean_sheets_pct"));
		            
		            BasicDBObject setQuery_array = new BasicDBObject();
		            setQuery_array.append("$push", updateArray);
		            
		            start2 = System.nanoTime();
		            
		            collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), setQuery_array);
					
		            end2 = System.nanoTime();

		            parziale = this.nano + (end2 - start2);
		            
		            this.setNano(parziale);
		            
				}
				
				break;
				
			case 3:		/** Operazione di UPDATE ULTIMA STAGIONE. */
				
				if(this.portiere == null) {
					
					long start3 = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()), Updates.combine(Updates.popLast("goals"), Updates.popLast("assists")));
	            	collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore())), Updates.combine(Updates.set("Ultima stagione.goals", 222), Updates.set("Ultima stagione.assists", 222), Updates.push("goals", 222), Updates.push("assists", 222)));
					
	            	long end3 = System.nanoTime();
					
					this.setNano(end3 - start3);
	            	
				} else if(this.giocatore == null) {
					
					long start3 = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.combine(Updates.popLast("goals_against_gk"), Updates.popLast("saves")));
	            	collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.combine(Updates.set("Ultima stagione.goals_against_gk", 222),Updates.set("Ultima stagione.saves", 222), Updates.push("goals_against_gk", 222), Updates.push("saves", 222)));
					
					long end3 = System.nanoTime();
					
					this.setNano(end3 - start3);
					
				}
				
				break;
				
			case 4:		/** Operazione di RICERCA CALCIATORE. */
				
				if(this.portiere == null) {
					
					long start4 = System.nanoTime();
					
					ArrayList<Document> doc_gioc = collection.find(eq("Nome", this.giocatore.getNome_calciatore())).into(new ArrayList<Document>());
					
					long end4 = System.nanoTime();
					
					this.setNano(end4 - start4);
					
					if(doc_gioc.size() != 0) {
						
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Giocatore trovato." );
						
					} else {
						
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Giocatore non trovato." );
						
					}
					
				} else if(this.giocatore == null) {
					
					long start4 = System.nanoTime();
					
					ArrayList<Document> doc_por = collection.find(eq("Nome", this.portiere.getNome_calciatore())).into(new ArrayList<Document>());
					
					long end4 = System.nanoTime();
					
					this.setNano(end4 - start4);
					
					if(doc_por.size() != 0) {
						
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Portiere trovato." );
						
					} else {
						
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Portiere non trovato." );
						
					}
					
				}
				
				break;
				
			case 5:		/** Operazione di RICERCA DI UNA STATISTICA DI UN CALCIATORE in una certa stagione. */
				
				if(this.portiere == null) {
					
					Document match = new Document();
					 match.append("Nome", this.giocatore.getNome_calciatore());
					 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Ultima stagione.minutes", 1);
							 
					long start5 = System.nanoTime();
							 
					AggregateIterable<Document> doc = collection.aggregate(Arrays.asList(
												new Document("$match", match),
												new Document("$project", proj)));
					
					long end5 = System.nanoTime();
					
					this.setNano(end5 - start5);
					
					for (Document document : doc) {
						
						Document d = (Document) document.get("Ultima stagione");						
						
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Minuti giocati nella stagione 2019-2020: " + d.get("minutes"));						
						
					}
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
					 match.append("Nome", this.portiere.getNome_calciatore());
					 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Ultima stagione.minutes_gk", 1);
							 
					long start5 = System.nanoTime();
							 
					AggregateIterable<Document> doc = collection.aggregate(Arrays.asList(
												new Document("$match", match),
												new Document("$project", proj)));
					
					long end5 = System.nanoTime();
					
					this.setNano(end5 - start5);
					
					for (Document document : doc) {
						
						Document d = (Document) document.get("Ultima stagione");						
						
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Minuti giocati nella stagione 2019-2020: " + d.get("minutes_gk"));						
						
					}
					
				}
				
				break;
				
			case 6:		/** Operazione di CALCOLO MEDIA. */
				
				if(this.portiere == null) {
					
					Document match = new Document();
					 match.append("Nome", this.giocatore.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("avg_goals", new Document("$avg", "$goals"));
		
					long start6 = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end6 = System.nanoTime();
					
					this.setNano(end6 - start6);
					
					for (Document document : doc) {
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Media goals: " + document.get("avg_goals"));
					}
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
					 match.append("Nome", this.portiere.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("avg_saves", new Document("$avg", "$saves"));
		
					long start6 = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end6 = System.nanoTime();
					
					this.setNano(end6 - start6);
					
					for (Document document : doc) {
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Media saves: " + document.get("avg_saves"));
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
							 
					collection.updateOne(filter, Updates.combine(update, Updates.popLast("npxg_xa_per90")));				
					
					long end7 = System.nanoTime();
					
					this.setNano(end7 - start7);
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Il campo 'npxg_xa_per90' eliminato dalla stagione 2019-2020.");
					
				} else if(giocatore == null) {
					
					Document filter = new Document();
					 filter.append("Link calciatore", this.portiere.getLink_calciatore());
					 
					Document update = new Document();
							 update.append("$unset", new Document("Ultima stagione.goals_against_gk_per90", 1));
				
					long start7 = System.nanoTime();
							 
					collection.updateOne(filter, Updates.combine(update, Updates.popLast("goals_against_gk_per90")));
					
					long end7 = System.nanoTime();
					
					this.setNano(end7 - start7);
					
					System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Il campo 'goals_against_gk_per90' eliminato dalla stagione 2019-2020.");
					 
				}
				
				break;
				
			case 8:		/** Operazione di CANCELLAZIONE CALCIATORE */
				
				if(this.portiere == null) {
					
					long start8 = System.nanoTime();
					
					this.collection.deleteOne(eq("Link calciatore", this.giocatore.getLink_calciatore()));
					
					long end8 = System.nanoTime();
					
					this.setNano(end8 - start8);
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Cancellato dal database.");
					
				} else if(this.giocatore == null) {
					
					long start8 = System.nanoTime();
					
					this.collection.deleteOne(eq("Link calciatore", this.portiere.getLink_calciatore()));
					
					long end8 = System.nanoTime();
					
					this.setNano(end8 - start8);
					
					System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Cancellato dal database.");
					
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
						 
							this.collection.updateOne(filter, Updates.combine( 	  update,
																		          Updates.popLast("games"),
																		          Updates.popLast("games_start"),
																		          Updates.popLast("minutes"),
																		          Updates.popLast("goals"),
																		          Updates.popLast("assists"),
																		          Updates.popLast("pens_made"),
																		          Updates.popLast("pens_att"),
																		          Updates.popLast("cards_yellow"),
																		          Updates.popLast("cards_red"),
																		          Updates.popLast("goals_per90"),
																		          Updates.popLast("assists_per90"),
																		          Updates.popLast("goals_assists_per90"),
																		          Updates.popLast("goals_pens_per90"),
																		          Updates.popLast("goals_assists_pens_per90"),
																		          Updates.popLast("xg"),
																		          Updates.popLast("npxg"),
																		          Updates.popLast("xa"),
																		          Updates.popLast("xg_per90"),
																		          Updates.popLast("xa_per90"),
																		          Updates.popLast("xg_xa_per90"),
																		          Updates.popLast("npxg_per90"),
																		          Updates.popLast("npxg_xa_per90")
																		          ));
						
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
						
						this.collection.updateOne(    filter, Updates.combine(
																			  Updates.set("Ultima stagione", pen_s),
																			  Updates.set("Penultima stagione", null),
																	          Updates.popLast("games"),
																	          Updates.popLast("games_start"),
																	          Updates.popLast("minutes"),
																	          Updates.popLast("goals"),
																	          Updates.popLast("assists"),
																	          Updates.popLast("pens_made"),
																	          Updates.popLast("pens_att"),
																	          Updates.popLast("cards_yellow"),
																	          Updates.popLast("cards_red"),
																	          Updates.popLast("goals_per90"),
																	          Updates.popLast("assists_per90"),
																	          Updates.popLast("goals_assists_per90"),
																	          Updates.popLast("goals_pens_per90"),
																	          Updates.popLast("goals_assists_pens_per90"),
																	          Updates.popLast("xg"),
																	          Updates.popLast("npxg"),
																	          Updates.popLast("xa"),
																	          Updates.popLast("xg_per90"),
																	          Updates.popLast("xa_per90"),
																	          Updates.popLast("xg_xa_per90"),
																	          Updates.popLast("npxg_per90"),
																	          Updates.popLast("npxg_xa_per90")
																	          ));
						
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
						
						this.collection.updateOne(	  filter, Updates.combine(
															  			      Updates.set("Ultima stagione", pen_s),
																			  Updates.set("Penultima stagione", ter_s),
																			  pull,
																	          Updates.popLast("games"),
																	          Updates.popLast("games_start"),
																	          Updates.popLast("minutes"),
																	          Updates.popLast("goals"),
																	          Updates.popLast("assists"),
																	          Updates.popLast("pens_made"),
																	          Updates.popLast("pens_att"),
																	          Updates.popLast("cards_yellow"),
																	          Updates.popLast("cards_red"),
																	          Updates.popLast("goals_per90"),
																	          Updates.popLast("assists_per90"),
																	          Updates.popLast("goals_assists_per90"),
																	          Updates.popLast("goals_pens_per90"),
																	          Updates.popLast("goals_assists_pens_per90"),
																	          Updates.popLast("xg"),
																	          Updates.popLast("npxg"),
																	          Updates.popLast("xa"),
																	          Updates.popLast("xg_per90"),
																	          Updates.popLast("xa_per90"),
																	          Updates.popLast("xg_xa_per90"),
																	          Updates.popLast("npxg_per90"),
																	          Updates.popLast("npxg_xa_per90")
											          ));
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						this.setNano(parziale9);
					
					}
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Stagione 2019-2020 cancellata.");
					
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
						 
						this.collection.updateOne(filter, Updates.combine(	update,
																			Updates.popLast("games_gk"),
																			Updates.popLast("games_starts_gk"),
																			Updates.popLast("minutes_gk"),
																			Updates.popLast("goals_against_gk"),
																			Updates.popLast("shot_on_target_against"),
																			Updates.popLast("saves"),
																			Updates.popLast("save_pct"),
																			Updates.popLast("wins_gk"),
																			Updates.popLast("draws_gk"),
																			Updates.popLast("losses_gk"),
																			Updates.popLast("clean_sheets"),
																			Updates.popLast("clean_sheets_pct"),
																			Updates.popLast("pens_att_gk"),
																			Updates.popLast("pens_allowed"),
																			Updates.popLast("pens_saved"),
																			Updates.popLast("pens_missed_gk")
																			));
						
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
						
						this.collection.updateOne(filter, Updates.combine(
																			Updates.set("Ultima stagione", pen_s), 
																			Updates.set("Penultima stagione", null), 
																			Updates.popLast("games_gk"),
																			Updates.popLast("games_starts_gk"),
																			Updates.popLast("minutes_gk"),
																			Updates.popLast("goals_against_gk"),
																			Updates.popLast("shot_on_target_against"),
																			Updates.popLast("saves"),
																			Updates.popLast("save_pct"),
																			Updates.popLast("wins_gk"),
																			Updates.popLast("draws_gk"),
																			Updates.popLast("losses_gk"),
																			Updates.popLast("clean_sheets"),
																			Updates.popLast("clean_sheets_pct"),
																			Updates.popLast("pens_att_gk"),
																			Updates.popLast("pens_allowed"),
																			Updates.popLast("pens_saved"),
																			Updates.popLast("pens_missed_gk")
																			
																			));
						
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
						
						this.collection.updateOne(filter, Updates.combine(
																			Updates.set("Ultima stagione", pen_s),
																			Updates.set("Penultima stagione", ter_s), 
																			pull,
																			Updates.popLast("games_gk"),
																			Updates.popLast("games_starts_gk"),
																			Updates.popLast("minutes_gk"),
																			Updates.popLast("goals_against_gk"),
																			Updates.popLast("shot_on_target_against"),
																			Updates.popLast("saves"),
																			Updates.popLast("save_pct"),
																			Updates.popLast("wins_gk"),
																			Updates.popLast("draws_gk"),
																			Updates.popLast("losses_gk"),
																			Updates.popLast("clean_sheets"),
																			Updates.popLast("clean_sheets_pct"),
																			Updates.popLast("pens_att_gk"),
																			Updates.popLast("pens_allowed"),
																			Updates.popLast("pens_saved"),
																			Updates.popLast("pens_missed_gk")
																			));
						
						end9 = System.nanoTime();
						
						parziale9 = parziale9 + (end9 - start9);
						
						this.setNano(parziale9);
					
					}
			
			System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Stagione 2019-2020 cancellata.");
					
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
				 
				 System.out.println("\nTROVATI " + (doc_g.size() + doc_p.size()) + " documenti in totale.");
				 
				 if( doc_g.size()!=0 ) {
					 
					 for (Document document : doc_g) {
						
						System.out.println("GIOCATORI:\n\nEsempio:");
						System.out.println(document);
						break;
						
					}
					 
					 if( (doc_g.size()-1) != 0 ) {
						 
						 System.out.println("\nE altri: " + (doc_g.size()-1) + " documenti.");
						 
					 }
					 
				 }
				 
				 if( doc_p.size()!=0 ) {
					 
					 for (Document document : doc_p) {
						
						System.out.println("\nPORTIERI:\n\nEsempio:");
						System.out.println(document);
						break;
						
					}
					 
					 if( (doc_p.size()-1) != 0 ) {
						 
						 System.out.println("\nE altri: " + (doc_p.size()-1) + " documenti.");
						 
					 }
					 
				 }
				break;
				
			case 11:		/**Operazione di RICERCA TRAMITE SQUADRA in una cera stagione.*/
				
				Document filter_squad = new Document();
		 		 		 filter_squad.append("Ultima stagione.squad", this.random_squad);		 
				 
				 long start11 = System.nanoTime();
				 		 
				 ArrayList<Document> doc_c = collection.find(filter_squad).into(new ArrayList<Document>());
				 			 
				 long end11 = System.nanoTime();
				 
				 this.setNano(end11 - start11);
				 
				 System.out.println("\nTROVATI " + (doc_c.size()) + " documenti in totale.");
				 
				 if( doc_c.size()!=0 ) {
					 
					 for (Document document : doc_c) {
						
						System.out.println("\nEsempio:");
						System.out.println(document);
						break;
						
					}
					 
					 if( (doc_c.size()-1) != 0 ) {
						 
						 System.out.println("\nE altri: " + (doc_c.size()-1) + " documenti.");
						 
					 }
					 
				 }
				
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
					
					for (Document document : doc) {
						System.out.println("\nGIOCATORE: " + this.giocatore.getNome_calciatore() + "\nSTAGIONE TROVATA: " + document);
					}
							 
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
					
					for (Document document : doc) {
						System.out.println("\nPORTIERE: " + this.portiere.getNome_calciatore() + "\nSTAGIONE TROVATA: " + document);
					}
					
				}
				
				break;
				
			case 13:		/** Operazione CALCOLO SOMMA di una statistica per tutte le stagioni. */
				
				if(this.portiere == null) {
					
					Document match = new Document();
					 match.append("Link calciatore", this.giocatore.getLink_calciatore());
					 
					Document proj = new Document();
								 proj.append("sum_goals", new Document("$sum", "$goals"));
		
					long start13 = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end13 = System.nanoTime();
					
					this.setNano(end13 - start13);
					
					for (Document document : doc) {
						System.out.println("\nGIOCATORE: " + this.giocatore.getNome_calciatore() + " - Somma goals: " + document.get("sum_goals"));
					}
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
					 match.append("Link calciatore", this.portiere.getLink_calciatore());
					 
					Document proj = new Document();
								 proj.append("sum_saves", new Document("$sum", "$saves"));
		
					long start13 = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end13 = System.nanoTime();
					
					this.setNano(end13 - start13);
					
					for (Document document : doc) {
						System.out.println("\nPORTIERE: " + this.portiere.getNome_calciatore() + " - Somma saves: " + document.get("sum_saves"));
					}
					
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

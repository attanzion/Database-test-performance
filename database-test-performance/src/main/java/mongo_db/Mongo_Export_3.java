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

public class Mongo_Export_3 implements Runnable {
	
	private Giocatore giocatore = null;
	private Portiere portiere = null;
	private MongoCollection<Document> collection = null;
	private int operazione = 0;
	private Document document = null;
	
	private long nano = 0;
	
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
	 * CODICE ESEGUITO DAL THREAD.
	 */

	@Override
	public void run() {
		
		/** Lo switch sceglie quale operazione sul database 'FootballStats_3' eseguire in base al valore di 'operazione'. */
		switch (this.operazione) {
		
			case 1:			/** Operazione di INSERIMENTO. */
				
				if(portiere == null) {
					
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
		             
		             long start = System.nanoTime();
					
		             collection.insertOne(doc_giocatore);
						
						long end = System.nanoTime();
						
						this.setNano(end - start);
						
		    	} else if(giocatore == null) {		
		    		
		    		
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
		            
		            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), setQuery);
		            
		            end = System.nanoTime();
		            
		            long parziale = this.nano + (end - start);
		            
		            this.setNano(parziale);
		            		            
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
		            
		            start = System.nanoTime();
		            
		            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), setQuery_array);
		            
		            end = System.nanoTime();
		            
		            parziale = this.nano + (end - start);
		            
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
		            
		            collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()),  push("Stagioni", penultima_stagione));
		            collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), setQuery);
		            
		            end = System.nanoTime();
		            
		            long parziale = this.nano + (start - end);
		            
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
		            
		            start = System.nanoTime();
		            
		            collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), setQuery_array);
					
		            end = System.nanoTime();

		            parziale = this.nano + (end - start);
		            
		            this.setNano(parziale);
		            
				}
				
				break;
				
			case 3:		/** Operazione di UPDATE ULTIMA STAGIONE. */
				
				if(portiere == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()), Updates.combine(Updates.popLast("goals"), Updates.popLast("assists")));
	            	collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore())), Updates.combine(Updates.set("Ultima stagione.goals", 222), Updates.set("Ultima stagione.assists", 222), Updates.push("goals", 222), Updates.push("assists", 222)));
					
	            	long end = System.nanoTime();
					
					this.setNano(end - start);
	            	
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.combine(Updates.popLast("goals_against_gk"), Updates.popLast("saves")));
	            	collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()), Updates.combine(Updates.set("Ultima stagione.goals_against_gk", 222),Updates.set("Ultima stagione.saves", 222), Updates.push("goals_against_gk", 222), Updates.push("saves", 222)));
					
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
				
				if(portiere == null) {
					
					Document match = new Document();
					 match.append("Nome", this.giocatore.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("avg_goals", new Document("$avg", "$goals"));
		
					long start = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					for (Document document : doc) {
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Media goals: " + document.get("avg_goals"));
					}
					
				} else if(giocatore == null) {
					
					Document match = new Document();
					 match.append("Nome", this.portiere.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("avg_saves", new Document("$avg", "$saves"));
		
					long start = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					for (Document document : doc) {
						System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Media saves: " + document.get("avg_saves"));
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

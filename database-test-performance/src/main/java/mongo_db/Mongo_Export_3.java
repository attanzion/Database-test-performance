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

public class Mongo_Export_3 {

	/**
     * Costruttore della classe "Mongo_Export_2".
     */

    public Mongo_Export_3() {
    }
    
    /**
     * Funzione che memorizza i calciatori presenti negli array 'all_gioc' e 'all_por' nel database.
     * @param all_por
     * @param all_gioc
     */

    public void Insert_Calciatori(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
    		
    		mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
    		
    		MongoCollection<Document> collection = mongo.getMongoCollection();
    		
    		System.out.println("EXPORT GIOCATORI -  Configurazione 3. \n \n");
    		
    		 int count_gioc = 1;
    		
    		for (Giocatore giocatore : all_gioc) {       //Per ogni giocatore in 'all_gioc'
    			
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
    			
                 collection.insertOne(doc_giocatore);
                 
                 System.out.println(giocatore.getNome_calciatore() + " EXPORTED! \nCalciatore num: " + count_gioc);
                 
                 count_gioc++;
                 
    		}
    		
    		System.out.println("EXPORT PORTIERI. \n \n");
    		
    		for (Portiere portiere : all_por) {       //Per ogni giocatore in 'all_gioc'
    			
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
	                
	                doc_giocatore.put("Ultima stagione", doc_ultima_stagione);
	                doc_giocatore.put("Penultima stagione", doc_penultima_stagione);
	                doc_giocatore.put("games_gk", games_gk);
	                doc_giocatore.put("games_starts_gk", games_starts_gk);
	                doc_giocatore.put("minutes_gk", minutes_gk);
	                doc_giocatore.put("goals_against_gk", goals_against_gk);
	                doc_giocatore.put("goals_against_gk_per90", goals_against_gk_per90);
	                doc_giocatore.put("shot_on_target_against", shot_on_target_against);
	                doc_giocatore.put("saves", saves);
	                doc_giocatore.put("save_pct", save_pct);
	                doc_giocatore.put("wins_gk", wins_gk);
	                doc_giocatore.put("draws_gk", draws_gk);
	                doc_giocatore.put("losses_gk", losses_gk);
	                doc_giocatore.put("clean_sheets", clean_sheets);
	                doc_giocatore.put("clean_sheets_pct", clean_sheets_pct);
	                doc_giocatore.put("pens_att_gk", pens_att_gk);
	                doc_giocatore.put("pens_allowed", pens_allowed);
	                doc_giocatore.put("pens_saved", pens_saved);
	                doc_giocatore.put("pens_missed_gk", pens_missed_gk);
	                doc_giocatore.put("Stagioni", stagioni);
	   			
	                collection.insertOne(doc_giocatore);
	                
	                System.out.println(portiere.getNome_calciatore() + " EXPORTED! \nCalciatore num: " + count_gioc);
	                
	                count_gioc++;
                
   		}
    		
    		mongo.Disconnection();
    			
		} catch (Exception e) {
			
			System.out.println("Errore in Mongo_Export_2 - Insert_Calciatori(). \n \n" + e);
			
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
	            
	            mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
	            
	            MongoCollection<Document> collection = mongo.getMongoCollection();
	            
	            int count_gioc = 1;
	            
	            for(Giocatore giocatore : all_gioc ) {
	            
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
	            
	            
	            BasicDBObject updateArray = new BasicDBObject();
	            updateArray.append("games", document.getInteger("games"));
	            updateArray.append("games_starts", document.getInteger("games_starts"));
	            updateArray.append("minutes", document.getInteger("minutes"));
	            updateArray.append("goals", document.getInteger("goals"));
	            updateArray.append("assists", document.getInteger("assists"));
	            updateArray.append("pens_made", document.getInteger("pens_made"));
	            updateArray.append("pens_att", document.getInteger("pens_att"));
	            updateArray.append("cards_yellow", document.getInteger("cards_yellow"));
	            updateArray.append("cards_red", document.getInteger("cards_red"));
	            updateArray.append("goals_per90", document.getDouble("goals_per90"));
	            updateArray.append("assists_per90", document.getDouble("assists_per90"));
	            updateArray.append("goals_assists_per90", document.getDouble("goals_assists_per90"));
	            updateArray.append("goals_pens_per90", document.getDouble("goals_pens_per90"));
	            updateArray.append("goals_assists_pens_per90", document.getDouble("goals_assists_pens_per90"));
	            updateArray.append("xg", document.getDouble("xg"));
	            updateArray.append("npxg", document.getDouble("npxg"));
	            updateArray.append("xa", document.getDouble("xa"));
	            updateArray.append("xg_per90", document.getDouble("xg_per90"));
	            updateArray.append("xa_per90", document.getDouble("xa_per90"));
	            updateArray.append("xg_xa_per90", document.getDouble("xg_xa_per90"));
	            updateArray.append("npxg_per90", document.getDouble("npxg_per90"));
	            updateArray.append("npxg_xa_per90", document.getDouble("npxg_xa_per90"));
	            
	            BasicDBObject setQuery_array = new BasicDBObject();
	            setQuery_array.append("$push", updateArray);
	            
	            collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), setQuery_array);
	            
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
		            
		            
		            BasicDBObject updateArray = new BasicDBObject();
		            updateArray.append("games_gk", document_por.getInteger("games_gk"));
		            updateArray.append("games_starts_gk", document_por.getInteger("games_starts_gk"));
		            updateArray.append("minutes_gk", document_por.getInteger("minutes_gk"));
		            updateArray.append("goals_against_gk", document_por.getInteger("goals_against_gk"));
		            updateArray.append("shot_on_target_against", document_por.getInteger("shot_on_target_against"));
		            updateArray.append("saves", document_por.getInteger("saves"));
		            updateArray.append("wins_gk", document_por.getInteger("wins_gk"));
		            updateArray.append("draws_gk", document_por.getInteger("draws_gk"));
		            updateArray.append("losses_gk", document_por.getInteger("losses_gk"));
		            updateArray.append("clean_sheets", document_por.getInteger("clean_sheets"));
		            updateArray.append("pens_att_gk", document_por.getInteger("pens_att_gk"));
		            updateArray.append("pens_allowed", document_por.getInteger("pens_allowed"));
		            updateArray.append("pens_saved", document_por.getInteger("pens_saved"));
		            updateArray.append("pens_missed_gk", document_por.getInteger("pens_missed_gk"));
		            updateArray.append("goals_against_gk_per90", document_por.getDouble("goals_against_gk_per90"));
		            updateArray.append("save_pct", document_por.getDouble("save_pct"));
		            updateArray.append("clean_sheets_pct", document_por.getDouble("clean_sheets_pct"));
		            
		            BasicDBObject setQuery_array = new BasicDBObject();
		            setQuery_array.append("$push", updateArray);
		            
		            collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), setQuery_array);
		            
		            System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - AGGIORNATO. \nCalciatore num: " + count_gioc);
		            
		            count_gioc++;
	            	
	            }
	            
	            mongo.Disconnection();
				
			} catch (Exception e) {
				
				System.out.println("Errore in Mongo_Export_3 - Insert_new_season(). \n \n" + e);
				
			}
		    	
	 }
    
    /**
     * Funzione che aggiorna alcuni valori dell'ultima stagione di un calciatore, per il portiere 'goals_against_gk' e 'saves', per il giocatore 'goals' e 'assists'.
     * Inoltre aggiorna anche i valori negli array rispettivi (prima con un 'pop' poi con un 'push').
     * @param all_por
     * @param all_gioc
     */
    
    public void Update_last_season(ArrayList<Portiere> all_por, ArrayList<Giocatore> all_gioc) {
    	
    	try {
    		
    		Mongo mongo = new Mongo();
            
            mongo.Connection("localhost", 27017, "FootballStats_3", "Calciatori");   //Connessione a MongoDB.
            
            MongoCollection<Document> collection = mongo.getMongoCollection();
            
            int count_gioc = 1;
            
            for(Giocatore giocatore : all_gioc) {
            	
            	collection.updateOne(eq("Link calciatore", giocatore.getLink_calciatore()), Updates.combine(Updates.popLast("goals"), Updates.popLast("assists")));
            	collection.updateOne(and(eq("Link calciatore", giocatore.getLink_calciatore())), Updates.combine(Updates.set("Ultima stagione.goals", 222), Updates.set("Ultima stagione.assists", 222), Updates.push("goals", 222), Updates.push("assists", 222)));
            	
            	System.out.println("GIOCATORE: " + giocatore.getNome_calciatore() + " - CAMPI AGGIORNATI. \nCalciatore num: " + count_gioc);
            	
            	count_gioc++;
            	
            }
            
            for(Portiere portiere : all_por) {
            	
            	collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), Updates.combine(Updates.popLast("goals_against_gk"), Updates.popLast("saves")));
            	collection.updateOne(eq("Link calciatore", portiere.getLink_calciatore()), Updates.combine(Updates.set("Ultima stagione.goals_against_gk", 222),Updates.set("Ultima stagione.saves", 222), Updates.push("goals_against_gk", 222), Updates.push("saves", 222)));
            	
            	System.out.println("PORTIERE: " + portiere.getNome_calciatore() + " - CAMPI AGGIORNATI. \nCalciatore num: " + count_gioc);
            	
            	count_gioc++;
            	
            }
			
		} catch (Exception e) {
			
			System.out.println("Errore in Mongo_Export - Update_last_season(). \n \n" + e);
			
		}
    	
    }
    
}


package mongo_db;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import classi.Giocatore;
import classi.Portiere;
import classi.Stagione_Calciatore;
import classi.Stats;

public class Mongo_Export implements Runnable{
	
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
	 * Costruttore 1 della classe 'Mongo_Export'.
	 * @param giocatore_
	 * @param portiere_
	 * @param collection_
	 * @param operazione_
	 */
	
	public Mongo_Export(Giocatore giocatore_, Portiere portiere_, MongoCollection<Document> collection_, int operazione_) {
		
		this.setGiocatore(giocatore_);
		this.setPortiere(portiere_);
		this.setCollection(collection_);
		this.setOperazione(operazione_);
		
	}
	
	/**
	 * Costruttore 2 della classe 'Mongo_Export'.
	 * @param giocatore_
	 * @param portiere_
	 * @param collection_
	 * @param operazione_
	 * @param document_
	 */
	
	public Mongo_Export(Giocatore giocatore_, Portiere portiere_, MongoCollection<Document> collection_, int operazione_, Document document_) {
		
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
	
	public Mongo_Export(MongoCollection<Document> collection_, int operazione_, int random_goals_, int random_saves_) {
		
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
	
	public Mongo_Export(MongoCollection<Document> collection_, int operazione_, String random_squad_) {
		
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
		/** Lo switch sceglie quale operazione sul database 'FootballStats' eseguire in base al valore di 'operazione'. */
		switch (this.operazione) {
		
			case 1:			/** Operazione di INSERIMENTO. */		
				
				if(this.portiere == null) {	
		    		
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
		             
		             long start1 = System.nanoTime();
		             
		             this.collection.insertOne(doc_giocatore);   //Si memorizza il documento nel database
		             
		             long end1 = System.nanoTime();
		             
		             this.setNano(end1 - start1);
		    		
		    	} else if(this.giocatore == null) {
		    		
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
		            
		            long start1 = System.nanoTime();
		            
		            this.collection.insertOne(doc_portiere);    //Si memorizza il documento nel database
		            
		            long end1 = System.nanoTime();
		            
		            this.setNano(end1 - start1);
		    		
		    	}						
				
				break;
				
			case 2:			/** Operazione di UPDATE NUOVA STAGIONE. */
				
				if(this.portiere == null) {
					
					long start2 = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()),  push("Stagioni", this.document));
					
					long end2 = System.nanoTime();
					
					this.setNano(end2 - start2);
					
				} else if(this.giocatore == null) {
					
					long start2 = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()),  push("Stagioni", this.document));
					
					long end2 = System.nanoTime();
					
					this.setNano(end2 - start2);
					
				}
				
				break;
				
			case 3:			/** Operazione di UPDATE ULTIMA STAGIONE. */
				
				if(this.portiere == null) {
					
					long start3 = System.nanoTime();
					
					collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore()), eq("Stagioni.season", "2019-2020")), Updates.combine(Updates.set("Stagioni.$.games", 222),Updates.set("Stagioni.$.games_starts", 222)));
					
					long end3 = System.nanoTime();
					
					this.setNano(end3 - start3);
					
				} else if(this.giocatore == null) {
					
					long start3 = System.nanoTime();
					
					collection.updateOne(and(eq("Link calciatore", this.portiere.getLink_calciatore()), eq("Stagioni.season", "2019-2020")), Updates.combine(Updates.set("Stagioni.$.goals_against_gk", 222),Updates.set("Stagioni.$.saves", 222)));
					
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
							 proj.append("Stagioni", Document.parse("{ $filter : { input: '$Stagioni', as: 'stagioni', cond: { $eq: [ '$$stagioni.season', '2019-2020' ] }}}"));
					
					Document proj2 = new Document();
							 proj2.append("Nome", 1);
							 proj2.append("Stagioni.minutes", 1);
							 
					long start5 = System.nanoTime();
							 
					AggregateIterable<Document> doc = collection.aggregate(Arrays.asList(
												new Document("$match", match),
												new Document("$project", proj),
												new Document("$project", proj2)));
					
					long end5 = System.nanoTime();
					
					this.setNano(end5 - start5);
					
					for (Document document : doc) {
						
						ArrayList<Document> d = (ArrayList<Document>) document.get("Stagioni");
						
						for (Document d2 : d) {
							System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Minuti giocati nella stagione 2019-2020: " + d2.get("minutes"));
						}
						
					}
					
				} else if(this.giocatore == null) {
					
					Document match = new Document();
					 match.append("Nome", this.portiere.getNome_calciatore());
					 
					Document proj = new Document();
							 proj.append("Nome", 1);
							 proj.append("Stagioni", Document.parse("{ $filter : { input: '$Stagioni', as: 'stagioni', cond: { $eq: [ '$$stagioni.season', '2019-2020' ] }}}"));
					
					Document proj2 = new Document();
							 proj2.append("Nome", 1);
							 proj2.append("Stagioni.minutes_gk", 1);
							 
					long start5 = System.nanoTime();
							 
					AggregateIterable<Document> doc = collection.aggregate(Arrays.asList(
												new Document("$match", match),
												new Document("$project", proj),
												new Document("$project", proj2)));
					
					long end5 = System.nanoTime();
					
					this.setNano(end5 - start5);
					
					for (Document document : doc) {
						
						ArrayList<Document> d = (ArrayList<Document>) document.get("Stagioni");
						
						for (Document d2 : d) {
							System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Minuti giocati nella stagione 2019-2020: " + d2.get("minutes_gk"));
						}
						
					}
					
				}
				
				break;
				
			case 6:		/** Operazione di CALCOLO MEDIA. */
				
				if(this.portiere == null) {
					
					Document match = new Document();
					 match.append("Nome", this.giocatore.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("avg_goals", new Document("$avg", "$Stagioni.goals"));
		
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
								 proj.append("avg_saves", new Document("$avg", "$Stagioni.saves"));
		
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
					 filter.append("Stagioni", new Document("$elemMatch", new Document("season", "2019-2020")));
					 
					Document update = new Document();
							 update.append("$unset", new Document("Stagioni.$.npxg_xa_per90", 1));
					
					long start7 = System.nanoTime();
							 
					collection.updateOne(filter, update);
					
					long end7 = System.nanoTime();
					
					this.setNano(end7 - start7);
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Il campo 'npxg_xa_per90' eliminato dalla stagione 2019-2020.");
					
				} else if(giocatore == null) {
					
					Document filter = new Document();
					 filter.append("Link calciatore", this.portiere.getLink_calciatore());
					 filter.append("Stagioni", new Document("$elemMatch", new Document("season", "2019-2020")));
					 
					Document update = new Document();
							 update.append("$unset", new Document("Stagioni.$.goals_against_gk_per90", 1));
							 
					long start7 = System.nanoTime();
							 
					collection.updateOne(filter, update);
					
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
				
				if(this.portiere == null) {
					
					Document filter = new Document();
					 		 filter.append("Link calciatore", this.giocatore.getLink_calciatore());
					 
					Document update = new Document();
							 update.append("$pull", new Document("Stagioni", new Document("season", "2019-2020")));
							 
					long start9 = System.nanoTime();
					 
					this.collection.updateOne(filter, update);
					
					long end9 = System.nanoTime();
					
					this.setNano(end9 - start9);
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Stagione 2019-2020 cancellata.");
					
				} else if(this.giocatore == null) {
					
					Document filter = new Document();
							 filter.append("Link calciatore", this.portiere.getLink_calciatore());
			 
					Document update = new Document();
							 update.append("$pull", new Document("Stagioni", new Document("season", "2019-2020")));
							 
					long start9 = System.nanoTime();
					 
					this.collection.updateOne(filter, update);
					
					long end9 = System.nanoTime();
					
					this.setNano(end9 - start9);
					
					System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - Stagione 2019-2020 cancellata.");
					
				}
				
				break;
				
			case 10:		/** Operazione di RICERCA GIOCATORE con una statistica maggiore di un certo valore in una data stagione. */
				
				
				Document filter_gioc = new Document();
				 		 filter_gioc.append("Stagioni", Document.parse("{ $elemMatch : { 'season': '2019-2020', 'goals' : { $gt : " + this.random_goals + " }}}"));
				 
				Document filter_por = new Document();
				 		 filter_por.append("Stagioni", Document.parse("{ $elemMatch : { 'season': '2019-2020', 'saves' : { $gt : " + this.random_saves + " }}}"));
				 
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
		 		 		 filter_squad.append("Stagioni", Document.parse("{ $elemMatch : { 'season': '2019-2020', 'squad' : '" + this.random_squad + "' }}"));
		 
				 
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
							 proj.append("Stagioni", Document.parse("{ $filter : { input: '$Stagioni', as: 'stagioni', cond: { $eq: [ '$$stagioni.season', '2019-2020' ] }}}"));
					
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
							 proj.append("Stagioni", Document.parse("{ $filter : { input: '$Stagioni', as: 'stagioni', cond: { $eq: [ '$$stagioni.season', '2019-2020' ] }}}"));
					
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
								 proj.append("sum_goals", new Document("$sum", "$Stagioni.goals"));
		
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
					 match.append("Nome", this.portiere.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("sum_saves", new Document("$sum", "$Stagioni.saves"));
		
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
				
			case 14:		/** Operazione AGGIUNTA NUOVO FIELD	*/
				
				if(portiere == null) {
					
					long start14 = System.nanoTime();
					
					this.collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore()), eq("Stagioni.season", "2019-2020")), Updates.set("Stagioni.$.mileage", 222));
					
					long end14 = System.nanoTime();
					
					this.setNano(end14 - start14);
					
					System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - 'mileage' aggiunto all'ultima stagione.");
					
				} else if(giocatore == null) {
					
					long start14 = System.nanoTime();
					
					this.collection.updateOne(and(eq("Link calciatore", this.portiere.getLink_calciatore()), eq("Stagioni.season", "2019-2020")), Updates.set("Stagioni.$.mistakes", 222));
					
					long end14 = System.nanoTime();
					
					this.setNano(end14 - start14);
					
					System.out.println("PORTIERE: " + this.portiere.getNome_calciatore() + " - 'mistakes' aggiunto all'ultima stagione.");
					
				} 
				
				break;
	
			default:
				break;
		}
		
			}catch (Exception e) {
				
				System.out.println("Errore in Mongo_Export - run()");
				
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

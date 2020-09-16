package mongo_db;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;
import java.util.Arrays;

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
	 * CODICE ESEGUITO DAL THREAD.
	 */

	@Override
	public void run() {
		
		/** Lo switch sceglie quale operazione sul database 'FootballStats' eseguire in base al valore di 'operazione'. */
		switch (this.operazione) {
		
			case 1:			/** Operazione di INSERIMENTO. */		
				
				if(portiere == null) {	
		    		
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
		             
		             long start = System.nanoTime();
		             
		             this.collection.insertOne(doc_giocatore);   //Si memorizza il documento nel database
		             
		             long end = System.nanoTime();
		             
		             this.setNano(end - start);
		    		
		    	} else if(giocatore == null) {
		    		
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
		            
		            long start = System.nanoTime();
		            
		            this.collection.insertOne(doc_portiere);    //Si memorizza il documento nel database
		            
		            long end = System.nanoTime();
		            
		            this.setNano(end - start);
		    		
		    	}						
				
				break;
				
			case 2:			/** Operazione di UPDATE NUOVA STAGIONE. */
				
				if(portiere == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.giocatore.getLink_calciatore()),  push("Stagioni", this.document));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(eq("Link calciatore", this.portiere.getLink_calciatore()),  push("Stagioni", this.document));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
				}
				
				break;
				
			case 3:			/** Operazione di UPDATE ULTIMA STAGIONE. */
				
				if(portiere == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(and(eq("Link calciatore", this.giocatore.getLink_calciatore()), eq("Stagioni.season", "2050-2051")), Updates.combine(Updates.set("Stagioni.$.games", 222),Updates.set("Stagioni.$.games_starts", 222)));
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
				} else if(giocatore == null) {
					
					long start = System.nanoTime();
					
					collection.updateOne(and(eq("Link calciatore", this.portiere.getLink_calciatore()), eq("Stagioni.season", "2050-2051")), Updates.combine(Updates.set("Stagioni.$.goals_against_gk", 222),Updates.set("Stagioni.$.saves", 222)));
					
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
								 proj.append("avg_goals", new Document("$avg", "$Stagioni.goals"));
		
					long start = System.nanoTime();
								 
					AggregateIterable<Document> doc = this.collection.aggregate(Arrays.asList(
							new Document("$match", match),
							new Document("$project", proj)));	
					
					long end = System.nanoTime();
					
					this.setNano(end - start);
					
					for (Document document : doc) {
						System.out.println("GIOCATORE: " + this.giocatore.getNome_calciatore() + " - Media saves: " + document.get("avg_goals"));
					}
					
				} else if(giocatore == null) {
					
					Document match = new Document();
					 match.append("Nome", this.portiere.getNome_calciatore());
					 
					Document proj = new Document();
								 proj.append("avg_saves", new Document("$avg", "$Stagioni.saves"));
		
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

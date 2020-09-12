package mongo_db;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

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

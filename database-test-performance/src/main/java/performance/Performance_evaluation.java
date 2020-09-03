package performance;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import com.google.common.base.Stopwatch;

import classi.Giocatore;
import classi.Portiere;
import mongo_db.Mongo;
import mongo_db.Mongo_Export;
import mongo_db.Mongo_Export_2;
import mongo_db.Mongo_Export_3;
import random.Generatore;

public class Performance_evaluation {
	
	/**
	 * Costruttore della classe 'Performance_operation'.
	 */
	
	public Performance_evaluation() {
	}
	
	/**
	 * Funzione che calcola e restituisce i millisecondi che ci son voluti per eseguire le operazioni di inserimento per ogni configurazione, in base al numero di calciatori indicati.
	 * @param numero
	 */
	
	public void Inserimento(int numero) {
		
		try {
		
		Generatore generatore = new Generatore();
		Generatore generatore_null = new Generatore();
		Mongo_Export mongo_export = new Mongo_Export();
		Mongo_Export_2 mongo_export_2 = new Mongo_Export_2();
		Mongo_Export_3 mongo_export_3 = new Mongo_Export_3();
	
		generatore.Genera_Calciatori(numero);
		
		generatore_null.Genera_Calciatori(1);
		
		ArrayList<Giocatore> all_gioc = generatore.getAll_gioc();
		ArrayList<Portiere> all_por = generatore.getAll_por();
		
		ArrayList<Giocatore> all_gioc_null = generatore_null.getAll_gioc();
		ArrayList<Portiere> all_por_null = generatore_null.getAll_por();
		
		Stopwatch time = null;
		Stopwatch time_2 = null;
		Stopwatch time_3 = null;
		
		long ms = 0;
		long ms_2 = 0;
		long ms_3 = 0;
		
		time = Stopwatch.createStarted();
		mongo_export.Insert_Calciatori(all_por_null, all_gioc_null);
		mongo_export.Delete_Calciatori(all_por_null, all_gioc_null);
		time.stop();
		
		time = Stopwatch.createStarted();
		mongo_export.Insert_Calciatori(all_por, all_gioc);
		time.stop();
		
		time_2 = Stopwatch.createStarted();
		mongo_export_2.Insert_Calciatori(all_por, all_gioc);
		time_2.stop();
		
		time_3 = Stopwatch.createStarted();
		mongo_export_3.Insert_Calciatori(all_por, all_gioc);
		time_3.stop();
		
		
		ms = time.elapsed(TimeUnit.MILLISECONDS);
		ms_2 = time_2.elapsed(TimeUnit.MILLISECONDS);
		ms_3 = time_3.elapsed(TimeUnit.MILLISECONDS);
		
		System.out.println("Tempo inserimento di " + numero + " calciatori per la CONFIGURAZIONE 1: " + ms + " ms.");
		System.out.println("Tempo inserimento di " + numero + " calciatori per la CONFIGURAZIONE 2: " + ms_2 + " ms.");
		System.out.println("Tempo inserimento di " + numero + " calciatori per la CONFIGURAZIONE 3: " + ms_3 + " ms.");
		
		} catch (Exception e) {
			
			System.out.println("Errore in Performace_evaluation - Inserimento().");
			
		}
		
	}
	
	/**
	 * Funzione che calcola e restituisce i millisecondi che ci son voluti per eseguire le operazioni di aggiunta di una nuova stagione per ogni configurazione, in base al numero di calciatori indicati.
	 * N.B.: Per questioni di comodità viene cancellato il vecchio database, aggiunti il numero di calciatori random indicati (ricreando i database), e aggiunti a tutti i giocatori la stessa stagione e a tutti i portieri la stessa stagione (valutando i millisecondi).
	 * @param numero
	 */
	
	public void Update_new_season(int numero) {
		
		try {
			
			Generatore generatore = new Generatore();
			Generatore generatore_null = new Generatore();
			Mongo mongo = new Mongo();
			Mongo_Export mongo_export = new Mongo_Export();
			Mongo_Export_2 mongo_export_2 = new Mongo_Export_2();
			Mongo_Export_3 mongo_export_3 = new Mongo_Export_3();
			
			generatore.Genera_Calciatori(numero);
			
			generatore_null.Genera_Calciatori(1);
			
			mongo.Drop_database("FootballStats"); 		/** Cancellazione database 'FootballStats. */
			mongo.Drop_database("FootballStats_2");		/** Cancellazione database 'FootballStats_2. */
			mongo.Drop_database("FootballStats_3");		/** Cancellazione database 'FootballStats_3. */
			
			ArrayList<Giocatore> all_gioc = generatore.getAll_gioc();
			ArrayList<Portiere> all_por = generatore.getAll_por();
			
			ArrayList<Giocatore> all_gioc_null = generatore_null.getAll_gioc();
			ArrayList<Portiere> all_por_null = generatore_null.getAll_por();
			
			Stopwatch time = null;
			Stopwatch time_2 = null;
			Stopwatch time_3 = null;
			
			long ms = 0;
			long ms_2 = 0;
			long ms_3 = 0;
			
			mongo_export.Insert_Calciatori(all_por, all_gioc);		/** Inserimento calciatori in FootballStats. */
			mongo_export_2.Insert_Calciatori(all_por, all_gioc);	/** Inserimento calciatori in FootballStats_2. */
			mongo_export_3.Insert_Calciatori(all_por, all_gioc);	/** Inserimento calciatori in FootballStats_3. */
			
			/** Inserimento nullo per eliminare il ritardo del primo 'time' avviato. */
			time = Stopwatch.createStarted();		
			mongo_export.Insert_Calciatori(all_por_null, all_gioc_null);
			mongo_export.Delete_Calciatori(all_por_null, all_gioc_null);
			time.stop();		
			/******/
			
			time = Stopwatch.createStarted();
			mongo_export.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());
			time.stop();
			
			time_2 = Stopwatch.createStarted();
			mongo_export_2.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());
			time_2.stop();
			
			time_3 = Stopwatch.createStarted();
			mongo_export_3.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());
			time_3.stop();		
			
			ms = time.elapsed(TimeUnit.MILLISECONDS);
			ms_2 = time_2.elapsed(TimeUnit.MILLISECONDS);
			ms_3 = time_3.elapsed(TimeUnit.MILLISECONDS);
			
			System.out.println("Tempo inserimento di una nuova stagione per " + numero + " calciatori, per la CONFIGURAZIONE 1: " + ms + " ms.");
			System.out.println("Tempo inserimento di una nuova stagione per " + numero + " calciatori, per la CONFIGURAZIONE 2: " + ms_2 + " ms.");
			System.out.println("Tempo inserimento di una nuova stagione per " + numero + " calciatori, per la CONFIGURAZIONE 3: " + ms_3 + " ms.");
			
		} catch (Exception e) {
			
			System.out.println("Errore in Performace_evaluation - Update_new_season().");
			
		}
		
	}
	
	/**
	 * Funzione che calcola e restituisce i millisecondi che ci son voluti per eseguire le operazioni di aggiornamento di due campi dell'ultima stagione per ogni configurazione, in base al numero di calciatori indicati.
	 * Vengono aggiornati per i giocatori 'Goals' e 'Assists', per i portieri 'Goals_against_gk' e 'Saves'.
	 * N.B.: Per questioni di comodità viene cancellato il vecchio database, aggiunti il numero di calciatori random indicati (ricreando i database), aggiunti a tutti i giocatori la stessa stagione e a tutti i portieri la stessa stagione, e modificati i valori dei campi dell'ultima stagione aggiunta (valutando i millisecondi).
	 * 		 I valori cambiano da 111 a 222.
	 * @param numero
	 */
	
	public void Update_last_season(int numero) {
		
		try {
			
			Generatore generatore = new Generatore();
			Generatore generatore_null = new Generatore();
			Mongo mongo = new Mongo();
			Mongo_Export mongo_export = new Mongo_Export();
			Mongo_Export_2 mongo_export_2 = new Mongo_Export_2();
			Mongo_Export_3 mongo_export_3 = new Mongo_Export_3();
			
			generatore.Genera_Calciatori(numero);
			
			generatore_null.Genera_Calciatori(1);
			
			mongo.Drop_database("FootballStats"); 		/** Cancellazione database 'FootballStats. */
			mongo.Drop_database("FootballStats_2");		/** Cancellazione database 'FootballStats_2. */
			mongo.Drop_database("FootballStats_3");		/** Cancellazione database 'FootballStats_3. */
			
			ArrayList<Giocatore> all_gioc = generatore.getAll_gioc();
			ArrayList<Portiere> all_por = generatore.getAll_por();
			
			ArrayList<Giocatore> all_gioc_null = generatore_null.getAll_gioc();
			ArrayList<Portiere> all_por_null = generatore_null.getAll_por();
			
			Stopwatch time = null;
			Stopwatch time_2 = null;
			Stopwatch time_3 = null;
			
			long ms = 0;
			long ms_2 = 0;
			long ms_3 = 0;
			
			mongo_export.Insert_Calciatori(all_por, all_gioc);		/** Inserimento calciatori in FootballStats. */
			mongo_export_2.Insert_Calciatori(all_por, all_gioc);	/** Inserimento calciatori in FootballStats_2. */
			mongo_export_3.Insert_Calciatori(all_por, all_gioc);	/** Inserimento calciatori in FootballStats_3. */
			
			mongo_export.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());			/** Aggiunta nuova stagione ai calciatori in FootballStats. */
			mongo_export_2.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());		/** Aggiunta nuova stagione ai calciatori in FootballStats_2. */
			mongo_export_3.Insert_new_season(all_por, all_gioc, this.Standard_document_stagione_giocatore(), this.Standard_document_stagione_portiere());		/** Aggiunta nuova stagione ai calciatori in FootballStats_3. */
			
			/** Inserimento nullo per eliminare il ritardo del primo 'time' avviato. */
			time = Stopwatch.createStarted();		
			mongo_export.Insert_Calciatori(all_por_null, all_gioc_null);
			mongo_export.Delete_Calciatori(all_por_null, all_gioc_null);
			time.stop();		
			/******/
			
			time = Stopwatch.createStarted();
			mongo_export.Update_last_season(all_por, all_gioc);
			time.stop();
			
			time_2 = Stopwatch.createStarted();
			mongo_export_2.Update_last_season(all_por, all_gioc);
			time_2.stop();
			
			time_3 = Stopwatch.createStarted();
			mongo_export_3.Update_last_season(all_por, all_gioc);
			time_3.stop();		
			
			ms = time.elapsed(TimeUnit.MILLISECONDS);
			ms_2 = time_2.elapsed(TimeUnit.MILLISECONDS);
			ms_3 = time_3.elapsed(TimeUnit.MILLISECONDS);
			
			System.out.println("\nTempo aggiornamento di campi dell'ultima stagione per " + numero + " calciatori, per la CONFIGURAZIONE 1: " + ms + " ms.");
			System.out.println("Tempo aggiornamento di campi dell'ultima stagione per " + numero + " calciatori, per la CONFIGURAZIONE 2: " + ms_2 + " ms.");
			System.out.println("Tempo aggiornamento di campi dell'ultima stagione per " + numero + " calciatori, per la CONFIGURAZIONE 3: " + ms_3 + " ms.");
			
		} catch (Exception e) {
			
			System.out.println("Errore in Performace_evaluation - Update_last_season().");
			
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

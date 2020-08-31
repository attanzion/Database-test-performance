package performance;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import classi.Giocatore;
import classi.Portiere;
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
		
	}

}

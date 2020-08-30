package classi;

import java.util.ArrayList;

/**
 * Classe che descrive l'oggetto 'Squadra'. 
 */

public class Squadra {
	
	String nome_campionato = null;					/** Nome del campionato di appartenenza della squadra considerata */
	String nome_squadra = null;						/** Nome della squadra */
	ArrayList<Stagione_Squadra> stag_s = null;		/** Arraylist che contiene gli oggetti 'Stagione_Squadra' per ogni anno della squadra considerato. */
	
	/**
	 * Costruttore della classe 'Squadra'.
	 * @param nome_campionato
	 * @param nome_squadra
	 */

	public Squadra(String nome_campionato, String nome_squadra) {
		
		this.setNome_Campionato(nome_campionato);
		this.setNome_squadra(nome_squadra);
		this.stag_s = new ArrayList<Stagione_Squadra>();
		
	}
	
	/**
	 * Funzione che setta il nome del campionato.
	 * @param nome_campionato
	 */
	
	public void setNome_Campionato(String nome_campionato) {
		
		this.nome_campionato = nome_campionato;
		
	}
	
	/**
	 * Funzione che ritorna il nome del campionato.
	 * @return nome_campionato
	 */
	
	public String getNome_Campionato() {
		
		return this.nome_campionato;
		
	}
	
	/**
	 * Funzione che setta il nome della squadra.
	 * @param nome_squadra
	 */
	
	public void setNome_squadra(String nome_squadra) {
		
		this.nome_squadra = nome_squadra;
		
	}
	
	/**
	 * Funzione che ritorna il nome della squadra.
	 * @return nome_squadra
	 */
	
	public String getNome_squadra() {
		
		return this.nome_squadra;
		
	}
	
	/**
	 * Funzione che aggiunge un'oggetto 'Stagione_Squadra' all'arraylist 'stag_s'.
	 * @param stagione_squadra
	 */
	
	public void add_stagione_squadra(Stagione_Squadra stagione_squadra) {
		
		this.stag_s.add(stagione_squadra);
		
	}
	
	/**
	 * Funzione che ritorna l'arraylist 'stag_s'.
	 * @return stag_s
	 */
	
	public ArrayList<Stagione_Squadra> getStag_s() {
		
		return this.stag_s;
		
	}
}

package classi;

import java.util.ArrayList;

/**
 * Classe che descrive l'oggetto 'Campionato'.
 */

public class Campionato {
	
	private String nome_campionato = null;   				/** Nome del campionato. */
	private ArrayList<Stagione_Campionato> stag_c = null;	/** Arraylist che contiene gli oggetti 'Stagione_Campionato' per ogni anno del campionato considerato. */
	
	/**
	 * Costruttore classe 'Campionato'.
	 * @param nome_campionato
	 */

	public Campionato(String nome_campionato) {
		
		this.setNome_campionato(nome_campionato);
		this.stag_c = new ArrayList<Stagione_Campionato>();
		
	}

	/**
	 * Funzione che ritorna il nome del campionato.
	 * @return nome_campionato
	 */
	
	public String getNome_campionato() {
		
		return this.nome_campionato;
		
	}

	/**
	 * Funzione che setta il nome del campionato.
	 * @param nome_campionato
	 */
	
	public void setNome_campionato(String nome_campionato) {
		
		this.nome_campionato = nome_campionato;
		
	}
	
	/**
	 * Funzione che aggiunge l'oggetto di tipo 'Stagione_Campionato' all'arraylist 'stag_c'.
	 * @param stagione_campionato
	 */
	
	public void add_stagione_campionato(Stagione_Campionato stagione_campionato) {
		
		this.stag_c.add(stagione_campionato);
		
	}

	/**
	 * Funzione che ritorna l'arraylist 'stag_c'.
	 * @return stag_c
	 */
	
	public ArrayList<Stagione_Campionato> getStag_c() {
		
		return this.stag_c;
		
	}

}

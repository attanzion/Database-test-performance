package classi;

/**
 * Classe che descrive l'oggetto 'Calciatore' 
 */

public class Calciatore {
	
	String nome_calciatore = null; 						/** Nome + Cognome del calciatore/portiere */
	String nazionalita = null;							/** Nazionalità del calciatore/portiere */
	String ruolo = null;								/** Ruolo del calciatore/portiere */
	String data_nascita = null;							/** Data di nascita del calciatore/portiere */
	String link_calciatore = null;						/**	Link del calciatore/portiere */
	
	/**
	 * Costruttore della classe 'Calciatore' (non portiere).
	 * @param nome_calciatore
	 * @param link_calciatore
	 */

	public Calciatore(String nome_calciatore, String link_calciatore) {
		
		this.setNome_calciatore(nome_calciatore);
		this.setLink_calciatore(link_calciatore);
		
	}
	
	/**
	 * Funzione che ritorna 'nome_calciatore'.
	 * @return nome_calciatore
	 */

	public String getNome_calciatore() {

		return this.nome_calciatore;

	}
	
	/**
	 * Funzione che setta il nome del calciatore/portiere.
	 * @param nome_giocatore
	 */

	public void setNome_calciatore(String nome_calciatore) {

		this.nome_calciatore = nome_calciatore;

	}
	
	/**
	 * Funzione che ritorna 'nazionalita'.
	 * @return
	 */

	public String getNazionalita() {

		return this.nazionalita;

	}
	
	/**
	 * Funzione che setta la nazionalità del calciatore/portiere.
	 * @param nazionalita
	 */

	public void setNazionalita(String nazionalita) {

		this.nazionalita = nazionalita;

	}
	
	/**
	 * Funzione che ritorna 'ruolo'.
	 * @return ruolo
	 */

	public String getRuolo() {

		return this.ruolo;

	}
	
	/**
	 * Funzione che setta il ruolo del calciatore/portiere.
	 * @param ruolo
	 */

	public void setRuolo(String ruolo) {

		this.ruolo = ruolo;

	}
	
	/**
	 * Funzione che ritorna 'data_nascita'
	 * @return data_nascita
	 */

	public String getData_nascita() {

		return this.data_nascita;

	}
	
	/**
	 * Funzione che setta la data di nascita del calciatore/portiere.
	 * @param data_nascita
	 */

	public void setData_nascita(String data_nascita) {

		this.data_nascita = data_nascita;

	}

	/**
	 * Funzione che ritorna 'link_calciatore'.
	 * @return link_calciatore
	 */

	public String getLink_calciatore() {

		return this.link_calciatore;

	}
	
	/**
	 * Funzione che setta il link del calciatore/portiere.
	 * @param link_giocatore
	 */

	public void setLink_calciatore(String link_calciatore) {

		this.link_calciatore = link_calciatore;

	}

}

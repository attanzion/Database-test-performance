package classi;

import java.util.ArrayList;

/**
 * Classe che descrive l'oggetto 'Competizioni'. 
 */

public class Competizioni {
	
	ArrayList<Campionato> camp = null;
	
	/**
	 * Costruttore classe 'Competizioni'.
	 */

	public Competizioni() {
		
		this.camp = new ArrayList<Campionato>();
		
	}
	
	/**
	 * Funzione che ritorna l'arraylist 'camp'.
	 */
	
	public ArrayList<Campionato> get_list_campionati(){
		
		return this.camp;
		
	}
	
	/**
	 * Funzione che aggiunge un oggetto di tipo 'Campionato' all'arraylist 'camp'.
	 * @param campionato 
	 */
	
	public void add_campionato(Campionato campionato) {
		
		this.camp.add(campionato);
		
	}

}

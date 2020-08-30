package classi;

import java.util.ArrayList;

/**
 * Classe che estende 'Giocatore', che descrive un calciatore che non è portiere.
 */

public class Giocatore extends Calciatore{

	private ArrayList<Stagione_Calciatore> stag_g = null;				/** Arraylist che contiene tutte le stagioni relative al calciatore considerato */

	/**
	 * Costruttore classe 'Giocatore' che estende 'Calciatore'.
	 * @param nome_calciatore
	 * @param link_calciatore
	 */

	public Giocatore(String nome_calciatore, String link_calciatore, ArrayList<Stagione_Calciatore> stag_g) {

		super(nome_calciatore, link_calciatore);

		this.setStag_g(stag_g);
		
	}

	/**
	 * Funzione che ritorna 'stag_g'.
	 * @return stag_g
	 */

	public ArrayList<Stagione_Calciatore> getStag_g() {

		return this.stag_g;

	}

	/**
	 * Funzione che setta 'stag_g', inizializzando l'arraylist.
	 */

	public void setStag_g(ArrayList<Stagione_Calciatore> stag_g) {

		this.stag_g = stag_g;

	}

	/**
	 * Funzione che aggiunge un oggetto 'Stagione_Calciatore' all'arraylist 'stag_g'.
	 * @param stagione_calciatore
	 */
	
	public void addStag_g(Stagione_Calciatore stagione_calciatore) {

		this.stag_g.add(stagione_calciatore);

	}

}

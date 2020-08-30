package classi;

import java.util.ArrayList;

/**
 * Classe che descrive l'oggetto 'Stagione_Campionato'.
 * Ogni 'Campionato' sarà formato da un più oggetti 'Stagione_Campionato'.
 */

public class Stagione_Campionato {
	
	private String anno = null;					/** Anno della stagione. */
	private String link_sc = null;				/** Link della stagione. */	
	private ArrayList<Squadra> squad = null;	/** Arraylist degli oggetti 'Squadra' che descrivono le squadre che fanno parte del campionato considerato nell'anno della stagione considerata */

	/**
	 * Costruttore della classe 'Stagione_Campionato'.
	 * @param anno
	 * @param link
	 */
	
	public Stagione_Campionato(String anno, String link_sc) {
		
		this.setAnno(anno);
		this.setLink_sc(link_sc);
		this.squad = new ArrayList<Squadra>();
		
	}

	/**
	 * Funzione che ritorna l'anno della stagione considerata.
	 * @return anno
	 */
	
	public String getAnno() {
		
		return this.anno;
		
	}

	/**
	 * Funzione che setta l'anno della stagione considerata.
	 * @param anno 
	 */
	
	public void setAnno(String anno) {
		
		this.anno = anno;
		
	}

	/**
	 * Funziona che ritorna il link della stagione considerata.
	 * @return link
	 */
	
	public String getLink_sc() {
		
		return this.link_sc;
		
	}

	/**
	 * Funzione che setta il link della stagione considerata.
	 * @param link
	 */
	
	public void setLink_sc(String link_sc) {
		
		this.link_sc = link_sc;
		
	}
	
	/**
	 * Funzione che aggiunge l'oggetto 'Squadra' all'arraylist 'squad'.
	 * @param squadra
	 */
	
	public void add_squadra(Squadra squadra) {
		
		this.squad.add(squadra);
		
	};
	
	/**
	 * Funzione che ritorna l'arraylist 'squad'.
	 * @return squad
	 */
	
	public ArrayList<Squadra> get_squad(){
		
		return this.squad;
		
	}

}

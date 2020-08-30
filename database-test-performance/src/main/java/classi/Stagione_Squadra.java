package classi;

import java.util.ArrayList;
/**
 * Classe che descrive l'oggetto 'Stagione_squadra'.
 * Ogni 'Squadra' sarà formato da un più oggetti 'Stagione_squadra' 
 */

public class Stagione_Squadra {
	
	String anno = null;
	String link_squadra = null;
	Rosa rosa = null;
	ArrayList<Calc_elements> calc_elements = null;
	
	
	/**
	 * Costruttore della classe 'Stagione_squadra'.
	 * @param anno
	 * @param link_squadra
	 */

	public Stagione_Squadra(String anno, String link_squadra) {
		
		this.setAnno(anno);
		this.setLink_squadra(link_squadra);
		this.setCalc_elements();
		this.setRosa();
		
	}
	
	/**
	 * Funzione che setta l'anno.
	 * @param anno
	 */
	
	public void setAnno(String anno) {
		
		this.anno = anno;
		
	}
	
	/**
	 * Funzione che ritorna l'anno.
	 * @return anno
	 */
	
	public String getAnno() {
		
		return this.anno;
		
	}
	
	/**
	 * Funzione che setta il link della squadra.
	 * @param link_squadra
	 */
	
	public void setLink_squadra(String link_squadra) {
		
		this.link_squadra = link_squadra;
		
	}
	
	/**
	 * Funzione che ritorna il link della squadra.
	 * @return link_squadra
	 */
	
	public String getLink_squadra() {
		
		return this.link_squadra;
		
	}

	/**
	 * Funzione che ritorna 'rosa'.
	 * @return rosa
	 */

	public Rosa getRosa() {

		return this.rosa;

	}

	/**
	 * Funzione che setta 'rosa'.
	 * @param rosa
	 */

	public void setRosa() {

		this.rosa = new Rosa();

	}

	/**
	 * Funzione che ritorna 'calc_elements'.
	 * @return calc_elements
	 */

	public ArrayList<Calc_elements> getCalc_elements() {

		return this.calc_elements;
		
	}

	/**
	 * Funzione che setta 'calc_elements'.
	 */

	public void setCalc_elements() {

		this.calc_elements = new ArrayList<Calc_elements>();

	}

	/**
	 * Funzione che aggiunge un oggetto 'Calc_elements' all'arraylist 'calc_elements'.
	 * @param calc_elements
	 */

	public void addCalc_elements(Calc_elements calc_elements) {

		this.calc_elements.add(calc_elements);

	}

	/**
	 * Funzione che aggiunge un oggetto 'Portiere' all'arraylist 'por' della classe 'Rosa'.
	 * @param portiere
	 */

	public void addPortiere_to_Rosa(Portiere portiere) {

		this.rosa.addPor(portiere);

	}

	/**
	 * Funzione che aggiunge un oggetto 'Giocatore' all'arraylist 'gioc' della classe 'Rosa'.
	 * @param giocatore
	 */

	public void addGiocatore_to_Rosa(Giocatore giocatore) {

		this.rosa.addGioc(giocatore);

	}

}

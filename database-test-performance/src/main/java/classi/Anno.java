package classi;

/**
 * Classe che descrive l'oggetto 'Anno'. 
 */

public class Anno {
	
	String anno = null;    						/** Anno (es. 2012-2013) */
	String link_anno = null;					/** Link dell'anno */
	
	/**
	 * Costruttore classe 'Anno'.
	 * @param anno
	 * @param link_anno
	 */

	public Anno(String anno, String link_anno) {
		
		this.setAnno(anno);
		this.setLink_anno(link_anno);
		
	}

	/**
	 * Funzione che setta 'anno'.
	 * @param anno
	 */

	public void setAnno(final String anno) {

		this.anno = anno;

	}

	/**
	 * Funzione che ritorna 'anno'.
	 * @return anno
	 */

	public String getAnno() {

		return this.anno;

	}

	/**
	 * Funzione che setta 'link_anno'. 
	 * @param link_anno
	 */

	public void setLink_anno(final String link_anno) {

		this.link_anno = link_anno;

	}

	/**
	 * Funzione che ritorna 'link_anno'.
	 * @return link_anno
	 */

	public String getLink_anno() {

		return this.link_anno;

	}

}

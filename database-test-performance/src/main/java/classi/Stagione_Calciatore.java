package classi;

import java.util.ArrayList;

/**
 * Classe che descrive la stagione di un calciatore.
 */

public class Stagione_Calciatore {

    private ArrayList<Stats> stats = null;

    /**
     * Costruttore della classe 'Stagione_Calciatore'.
     */

    public Stagione_Calciatore() {

        this.setStats();
        
    }

    /**
     * Funzione che ritorna 'stats'.
     * @return stats
     */

    public ArrayList<Stats> getStats() {

        return this.stats;

    }

    /**
     * Funzione che setta 'stats', inizializzando un nuovo arraylist.
     * @param stats
     */

    public void setStats() {

        this.stats = new ArrayList<Stats>();

    }

    /**
     * Funzione che aggiunge un oggetto di classe 'Stats' all'arraylist 'stats'.
     * @param stats
     */

    public void addStats(Stats stats) {

        this.stats.add(stats);

    }
    
    /**
     * Funzione che setta 'stats' ad un arraylist già esistente.
     * @param stats
     */
    
    public void setNewStats(ArrayList<Stats> stats) {
    	
    	this.stats = stats;
    	
    }
    
}
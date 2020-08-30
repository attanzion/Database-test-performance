package classi;

import java.util.ArrayList;

/**
 * Classe che estende 'Calciatore', che descrive un calciatore che è portiere.
 */
public class Portiere extends Calciatore {

    private ArrayList<Stagione_Calciatore> stag_p = null;                 /** Arraylist che contiene tutte le stagioni relative al calciatore considerato */

    /**
     * Costruttore della classe 'Portiere' che estende 'Calciatore'.
     * @param nome_calciatore
     * @param link_calciatore
     */

    public Portiere(String nome_calciatore, String link_calciatore, ArrayList<Stagione_Calciatore> stag_p) {

        super(nome_calciatore, link_calciatore);

        this.setStag_p(stag_p);
           
    }

    /**
     * Funzione che ritorna 'stag_p'.
     * @return stag_p
     */

    public ArrayList<Stagione_Calciatore> getStag_p() {

        return this.stag_p;

    }

    /**
     * Funzione che setta 'stag_p', inizializzando l'arraylist.
     */

    public void setStag_p(ArrayList<Stagione_Calciatore> stag_p) {

        this.stag_p = stag_p;

    }

    /**
     * Funzione aggiunge un oggetto 'Stagione_Portiere' all'arraylist 'stag_p'.
     * @param stagione_portiere
     */

    public void addStag_p(Stagione_Calciatore stagione_portiere) {

        stag_p.add(stagione_portiere);

    }
    
}
package classi;

import java.util.ArrayList;

/**
 * Classe che descrive la rosa di una squadra, ovvero dai calciatori di tipo 'Giocatore' e 'Portiere' da cui è composta.
 */

public class Rosa {
 
    private ArrayList<Portiere> por = null;         /** Arraylist che contiene tutti gli oggetti 'Portiere' di una squadra. */
    private ArrayList<Giocatore> gioc = null;       /** Arraylist che contiene tutti gli oggetti 'Giocatore' (non Portiere) di una squadra. */

    /**
     * Costruttore della classe 'Rosa'.
     */

    public Rosa() {

        this.setGioc();
        this.setPor();

    }

    /**
     * Funzione che ritorna 'por'.
     * @return por
     */

    public ArrayList<Portiere> getPor() {

        return this.por;

    }

    /**
     * Funzione che setta 'por', inizializzando l'arraylist.
     * @param por
     */

    public void setPor() {

        this.por = new ArrayList<Portiere>();

    }

    /**
     * Funzione che ritorna 'gioc'.
     * @return gioc
     */

    public ArrayList<Giocatore> getGioc() {

        return this.gioc;

    }

    /**
     * Funzione che setta 'gioc', inizializzando l'arraylist.
     * @param gioc
     */

    public void setGioc() {

        this.gioc = new ArrayList<Giocatore>();

    }

    /**
     * Funzione che aggiunge un oggetto 'Giocatore' all'arraylist 'gioc'.
     * @param giocatore
     */

    public void addGioc(Giocatore giocatore) {

        this.gioc.add(giocatore);

    }

    /**
     * Funzione che aggiunge un oggetto 'Portiere' all'arraylist 'por'.
     * @param portiere
     */

    public void addPor(Portiere portiere) {

        this.por.add(portiere);

    }
    
}
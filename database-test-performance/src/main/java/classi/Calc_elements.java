package classi;

/**
 * Classe ausiliaria che memorizza i nomi dei calciatori e i loro link, in modo che possano essere utilizzati nello scraping delle statistiche dei calciatori.
 */

public class Calc_elements {

    private String nome_calciatore = null;
    private String link_calciatore = null;
    private String error = null;

    /**
     * Costruttore della classe 'Calc_elements'.
     * @param nome_calciatore
     * @param link_calciatore
     */

    public Calc_elements(String nome_calciatore, String link_calciatore) {

        this.setLink_calciatore(link_calciatore);
        this.setNome_calciatore(nome_calciatore);

    }

    /**
     * Funzione che ritorna 'nome_calciatore'.
     * @return nome_calciatore
     */

    public String getNome_calciatore() {

        return this.nome_calciatore;

    }

    /**
     * Funzione che setta 'nome_calciatore'.
     * @param nome_calciatore
     */

    public void setNome_calciatore(String nome_calciatore) {

        this.nome_calciatore = nome_calciatore;

    }

    /**
     * Funzione che ritorna 'link_calciatore'.
     * @return link_calciatore
     */

    public String getLink_calciatore() {

        return link_calciatore;

    }

    /**
     * Funzione che setta 'link_calciatore'.
     * @param link_calciatore
     */

    public void setLink_calciatore(String link_calciatore) {

        this.link_calciatore = link_calciatore;

    }

    /**
     * Funzione che ritorna 'error'.
     * @return error
     */

    public String getError() {

        return this.error;

    }

    /**
     * Funzione che setta 'error'.
     * @param error
     */

    public void setError(String error) {

        this.error = error;
        
    }
    
}
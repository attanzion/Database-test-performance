package classi;

/**
 * Classe che descrive una statistica, tipo chiave-valore, dove la chiave è 'nome_stat' e il valore è 'valore_stat'.
 */

public class Stats {

    private String nome_stat = null;
    private String string_stat = null;
    private int int_stat = 0;
    private double double_stat = 0.00;
    private String type = null;

    /**
     * Costruttore della classe 'Stats' per le stringhe.
     * @param nome_stat
     * @param string_stat
     */

    public Stats(String nome_stat, String string_stat, String type) {

        this.setNome_stat(nome_stat);
        this.setString_stat(string_stat);
        this.setType(type);

    }
    
    /**
     * Costruttore della classe 'Stats' per gli interi.
     * @param nome_stat
     * @param int_stat
     */

    public Stats(String nome_stat, int int_stat, String type) {

        this.setNome_stat(nome_stat);
        this.setInt_stat(int_stat);
        this.setType(type);

    }
    
    /**
     * Costruttore della classe 'Stats' per i double.
     * @param nome_stat
     * @param valore_stat
     */

    public Stats(String nome_stat, double double_stat, String type) {

        this.setNome_stat(nome_stat);
        this.setDouble_stat(double_stat);
        this.setType(type);

    }

    /**
     * Funzione che ritorna 'nome_stat'.
     * @return nome_stat
     */

    public String getNome_stat() {

        return this.nome_stat;

    }

    /**
     * Funzione che setta 'nome_stat'.
     * @param nome_stat
     */

    public void setNome_stat(String nome_stat) {

        this.nome_stat = nome_stat;

    }

    /**
     * Funzione che ritorna 'valore_stat'.
     * @return valore_stat
     */

    public String getString_stat() {

        return this.string_stat;

    }

    /**
     * Funzione che setta 'valore_stat'.
     * @param valore_stat
     */

    public void setString_stat(String string_stat) {

        this.string_stat = string_stat;

    }

	/**
	 * Funzione che ritorna 'int_stat'.
	 * @return int_stat
	 */
    
	public int getInt_stat() {
		
		return this.int_stat;
		
	}

	/**
	 * Funzione che setta 'int_stat'.
	 * @param int_stat
	 */
	
	public void setInt_stat(int int_stat) {
		
		this.int_stat = int_stat;
		
	}

	/**
	 * Funzione che ritorna 'double_stat'.
	 * @return double_stat
	 */
	
	public double getDouble_stat() {
		
		return this.double_stat;
		
	}

	/**
	 * Funzione che setta 'double_stat'.
	 * @param double_stat
	 */
	
	public void setDouble_stat(double double_stat) {
		
		this.double_stat = double_stat;
		
	}

	/**
	 * Funzione che ritorna 'type'.
	 * @return type
	 */
	public String getType() {
		
		return this.type;
		
	}

	/**
	 * Funzione che setta 'type'.
	 * @param type
	 */
	
	public void setType(String type) {
		
		this.type = type;
		
	}
    
}
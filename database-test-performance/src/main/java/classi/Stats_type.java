package classi;

public class Stats_type {
	
	/**
	 * Costruttore della classe 'Stats_type'.
	 */
	
	public Stats_type() {
		
	}
	
    /**
     * Funzione che valuta il tipo di una statistica.
     * @param nome_stat
     * @return type
     */
    
    public String Stats_Type(String nome_stat) {
    	
    	String type = null;
    	
    	try {
    		
    		if(nome_stat.equals("season")||nome_stat.equals("squad")||nome_stat.equals("country")||nome_stat.equals("comp_level")||nome_stat.equals("lg_finish")) {
    			
    			type = "string";
    			
    		} else if (nome_stat.equals("age")||nome_stat.equals("games")||nome_stat.equals("games_starts")||nome_stat.equals("minutes")||nome_stat.equals("goals")||nome_stat.equals("assists")||
    				   nome_stat.equals("pens_made")||nome_stat.equals("pens_att")||nome_stat.equals("cards_yellow")||nome_stat.equals("cards_red")||nome_stat.equals("games_gk")||nome_stat.equals("games_starts_gk")||
    				   nome_stat.equals("minutes_gk")||nome_stat.equals("goals_against_gk")||nome_stat.equals("shots_on_target_against")||nome_stat.equals("saves")||nome_stat.equals("wins_gk")||nome_stat.equals("draws_gk")||
    				   nome_stat.equals("losses_gk")||nome_stat.equals("clean_sheets")||nome_stat.equals("pens_att_gk")||nome_stat.equals("pens_allowed")||nome_stat.equals("pens_saved")||nome_stat.equals("pens_missed_gk")) {
    			
    			type = "int";
    			
    		} else if (nome_stat.equals("goals_per90")||nome_stat.equals("assists_per90")||nome_stat.equals("goals_assists_per90")||nome_stat.equals("goals_pens_per90")||nome_stat.equals("goals_assists_pens_per90")||nome_stat.equals("goals_against_per90_gk")||nome_stat.equals("save_pct")||
    				   nome_stat.equals("clean_sheets_pct")||nome_stat.equals("xg")||nome_stat.equals("npxg")||nome_stat.equals("xa")||nome_stat.equals("xg_per90")||nome_stat.equals("xa_per90")||nome_stat.equals("xg_xa_per90")
    				   ||nome_stat.equals("npxg_per90")||nome_stat.equals("npxg_xa_per90")) {
    			
    			type = "double"; 
    			
    		} else {
    			
    			type = "string";
    			
    		}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Grabber_Stats - Stats_Type()");
			
		}
    	
    	return type;
    	
    }

}

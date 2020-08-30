package random;

import java.util.ArrayList;
import java.util.Random;

import classi.Stagione_Calciatore;
import classi.Stats;

public class Generatore_stagioni {
	
	private ArrayList<Stagione_Calciatore> stag_c = null;
	private ArrayList<String> info_squad = null;
	
	
	/**
	 * Costruttore della classe 'Generatore_stagioni'.
	 */
	
	public Generatore_stagioni(ArrayList<Stagione_Calciatore> stag_c, ArrayList<String> info_squad) {
		
		this.setStag_c(stag_c);
		this.setInfo_squad(info_squad);
		
	}
	
	/**
	 * Funzione che genera randomicamente una stagione.
	 * @param eta
	 * @param ruolo
	 * @return stats
	 */
	
	public void Random_stagione_giocatore(String ruolo, String season, int age) {
		
		try {
			
			Stagione_Calciatore stagione_calciatore = new Stagione_Calciatore();
			String[] info = this.Random_info();
			
			int games = this.Random_games();
			int games_starts = this.Random_games_starts(games);
			int minutes = this.Random_minutes(games, games_starts);
			
			String squad = this.Random_squad(info);
			String country = this.Random_country(info);
			String comp_level = this.Random_comp_level(info);
			String lg_finish = this.Random_lg_finish(info);
			
			stagione_calciatore.addStats(new Stats("season", season, "string"));											/** Season stagione. */
			stagione_calciatore.addStats(new Stats("age", age, "int"));
			stagione_calciatore.addStats(new Stats("squad", squad, "string"));
			stagione_calciatore.addStats(new Stats("country", country, "string"));
			stagione_calciatore.addStats(new Stats("com_level", comp_level, "string"));
			stagione_calciatore.addStats(new Stats("lg_finish", lg_finish, "string"));
			
			if(!ruolo.equals("GK")) {
			
				int goals = this.Random_goals(games, ruolo);
				int assists = this.Random_assists(games, ruolo);			
				int pens_att = this.Random_pens_att(goals, ruolo);
				int pens_made = this.Random_pens_made(pens_att);
				int cards_yellow = this.Random_cards_yellow(games, ruolo);
				int cards_red = this.Random_cards_red(games, ruolo);
				
				double goals_per90 = this.Goals_per90(minutes, goals);
				double assists_per90 = this.Assists_per90(minutes, assists);
				double goals_assists_per90 = this.Tronca_double(goals_per90 + assists_per90, "goals_assists_per90");
				double goals_pens_per90 = this.Goals_pens_per90(goals, minutes, pens_made);
				double goals_assists_pens_per90 = this.Goals_assists_pens_per90(goals, assists, minutes, pens_made);
				double xg = this.Random_xG(goals);
				double npxg = this.Random_npxG(goals, pens_made, xg);
				double xa = this.Random_xA(assists);
				double xg_per90 = this.xG_per90(minutes, xg);
				double xa_per90 = this.xA_per90(minutes, xa);
				double xg_xa_per90 = this.Tronca_double(xa_per90 + xg_per90, "xg_xa_per90");
				double npxg_per90 = this.npxG_per90(minutes, npxg);
				double npxg_xa_per90 = this.Tronca_double(npxg_per90 + xa_per90, "npxg_xa_per90"); 
				
				stagione_calciatore.addStats(new Stats("games", games, "int"));												/** Numero di partite giocate. */
				stagione_calciatore.addStats(new Stats("games_starts", games_starts, "int"));								/** Numero di partite giocate da titolare. */
				stagione_calciatore.addStats(new Stats("minutes", minutes, "int"));											/** Numero di minuti giocati. */	
				stagione_calciatore.addStats(new Stats("goals", goals, "int"));												/** Numero di golas segnati. */
				stagione_calciatore.addStats(new Stats("assists", assists, "int"));											/** Numero di assists effettuati. */
				stagione_calciatore.addStats(new Stats("pens_made", pens_made, "int"));										/** Numero di rigori segnati. */
				stagione_calciatore.addStats(new Stats("pens_att", pens_att, "int"));										/** Numero di rigori totali tirati. */
				stagione_calciatore.addStats(new Stats("cards_yellow", cards_yellow, "int")); 								/** Numero di cartellini gialli. */
				stagione_calciatore.addStats(new Stats("cards_red", cards_red, "int")); 									/** Numero di cartellini rossi. */
				stagione_calciatore.addStats(new Stats("goals_per90", goals_per90, "double"));								/** Numero di goals fatti in 90 minuti. */
				stagione_calciatore.addStats(new Stats("assists_per90", assists_per90, "double"));							/** Numero di assists fatti in 90 minuti. */
				stagione_calciatore.addStats(new Stats("goals_assists_per90", goals_assists_per90, "double")); 				/** Somma di assist_per90 e goals_per90. */
				stagione_calciatore.addStats(new Stats("goals_pens_per90", goals_pens_per90, "double"));					/** Goals - rigori tirati per 90 minuti. */
				stagione_calciatore.addStats(new Stats("goals_assists_pens_per90", goals_assists_pens_per90, "double"));	/** Goals + assists - rigori tirati per 90 minuti. */
				stagione_calciatore.addStats(new Stats("xg", xg, "double"));												/** Expected Goals. */
				stagione_calciatore.addStats(new Stats("npxg", npxg, "double"));											/** Non-Penalty Expected Goals. */
				stagione_calciatore.addStats(new Stats("xa", xa, "double"));   												/** xG Assisted. */
				stagione_calciatore.addStats(new Stats("xg_per90", xg_per90, "double")); 									/** Expected Goals per 90 minutes. */
				stagione_calciatore.addStats(new Stats("xa_per90", xa_per90, "double"));									/** xG Assisted per 90 minutes. */
				stagione_calciatore.addStats(new Stats("xg_xa_per90", xg_xa_per90, "double"));								/** Expected Goals + Assist per 90 minutes. */
				stagione_calciatore.addStats(new Stats("npxg_per90", npxg_per90, "double"));								/** Non-Penalty Expected Goals per 90 minutes. */
				stagione_calciatore.addStats(new Stats("npxg_xa_per90", npxg_xa_per90, "double"));							/** Non-Penalty Expected Goals + xG Assisted per 90 minutes. */
				
				this.stag_c.add(stagione_calciatore);
			
			} else {
				
				int goals_against_gk = this.Random_goals_against(lg_finish, minutes);
				int shot_on_target_against = this.Shot_on_target_against(goals_against_gk);
				int saves = shot_on_target_against - goals_against_gk;
				int wins_gk = this.Random_wins(lg_finish, games_starts);
				int draws_gk = this.Random_draws(wins_gk, games_starts);
				int losses_gk = this.Losses_gk(games_starts, wins_gk, draws_gk);
				int clean_sheets = this.Random_clean_sheets(wins_gk + draws_gk);
				int pens_att_gk = this.Random_pens_att_gk(goals_against_gk);
				int pens_allowed = this.Random_pens_allowed(pens_att_gk, goals_against_gk);
				int pens_saved = this.Random_pens_saved(pens_att_gk, pens_allowed);
				int pens_missed_gk = this.Pens_missed_gk(pens_att_gk, pens_allowed, pens_saved);
				
				
				double goals_against_gk_per90 = this.Goals_against_per90(minutes, goals_against_gk);
				double save_pct = this.Save_pct(saves, shot_on_target_against);
				double clean_sheets_pct = this.Clean_sheets_pct(clean_sheets, games_starts);
				
				
				//STAGIONE PORTIERE
				stagione_calciatore.addStats(new Stats("games_gk", games, "int"));												/** Numero di partite giocate. */
				stagione_calciatore.addStats(new Stats("games_starts_gk", games_starts, "int"));								/** Numero di partite giocate da titolare. */
				stagione_calciatore.addStats(new Stats("minutes_gk", minutes, "int"));											/** Numero di minuti giocati. */	
				stagione_calciatore.addStats(new Stats("goals_against_gk", goals_against_gk, "int"));							/** Numero di goal subiti. */
				stagione_calciatore.addStats(new Stats("goals_against_gk_per90", goals_against_gk_per90, "double"));			/** Numero di goal subiti nei 90 minuti. */
				stagione_calciatore.addStats(new Stats("shot_on_target_against", shot_on_target_against, "int"));				/** Numero di tiri subiti nello specchio. */
				stagione_calciatore.addStats(new Stats("saves", saves, "int"));													/** Numero di tiri parati. */
				stagione_calciatore.addStats(new Stats("save_pct", save_pct, "double"));										/** Percentuale di tiri parati. */
				stagione_calciatore.addStats(new Stats("wins_gk", wins_gk, "int"));												/** Vittorie. */
				stagione_calciatore.addStats(new Stats("draws_gk", draws_gk, "int"));											/** Pareggi. */
				stagione_calciatore.addStats(new Stats("losses_gk", losses_gk, "int"));											/** Sconfitte. */
				stagione_calciatore.addStats(new Stats("clean_sheets", clean_sheets, "int"));									/** Numero di partite senza subire goal. */
				stagione_calciatore.addStats(new Stats("clean_sheets_pct", clean_sheets_pct, "double"));						/** Percentuale di clean sheets. */
				stagione_calciatore.addStats(new Stats("pens_att_gk", pens_att_gk, "int"));										/** Numero di rigori totali. */
				stagione_calciatore.addStats(new Stats("pens_allowed", pens_allowed, "int"));									/** Numero di rigori subiti. */
				stagione_calciatore.addStats(new Stats("pens_saved", pens_saved, "int"));										/** Numero di rigori parati. */
				stagione_calciatore.addStats(new Stats("pens_missed_gk", pens_missed_gk, "int"));								/** Numero di rigori sbagliati ma non parati. */
				
				this.stag_c.add(stagione_calciatore);
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_stagione().");
			
		}
		
	}
	
	/**
	 * Funzione che estrae la squadra dall'array 'info'.
	 * @param info
	 * @return squad
	 */
	
	public String Random_squad(String[] info) {
		
		String squad = info[1].trim();
		
		return squad;
		
	}
	
	/**
	 * Funzione che estrae la comp_level dall'array 'info'.
	 * @param info
	 * @return comp_level
	 */
	
	public String Random_comp_level (String[] info) {
		
		String comp_level = info[2].trim();
		
		return comp_level;
		
	}
	
	/**
	 * Funzione che estrae la country dall'array 'info'.
	 * @param info
	 * @return comp_level
	 */
	
	public String Random_country (String[] info) {
		
		String country = info[3].trim();
		
		return country;
		
	}
	
	/**
	 * Funzione che estrae la country dall'array 'info'.
	 * @param info
	 * @return comp_level
	 */
	
	public String Random_lg_finish (String[] info) {
		
		String lg_finish = null;
		
		try {
			
			String lg_finish_ = info[0].trim();
			
			if(lg_finish_.equals("1")) {
				
				lg_finish = lg_finish_ + "st";
				
			} else if(lg_finish_.equals("2")) {
				
				lg_finish = lg_finish_ + "nd";
				
			} else if(lg_finish_.equals("3")) {
				
				lg_finish = lg_finish_ + "rd";
				
			} else {
				
				lg_finish = lg_finish_ + "th";
				
			}
			
		}catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_lg_finish().");
			
		}
		
		
		return lg_finish;
		
	}
	
	/**
	 * Funzione che genera il numero di partite giocate in modo randomico.
	 * @return games
	 */
	
	public int Random_games() {
		
		int games = 0;
		
		try {
			
			Random r = new Random();
			
			games = r.ints(0, 38).findFirst().getAsInt();		
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_games().");
			
		}
		
		return games;
		
	}
	
	/**
	 * Funzione che genera il numero di partite giocate da titolare dal calciatore in modo randomico sulla base delle partite giocate.
	 * @param games
	 * @return games_starts
	 */
	
	public int Random_games_starts(int games) {
		
		Random r = new Random();
		
		int games_starts = 0;
		
		try {
			
			if(games != 0) {
		
				games_starts = r.ints(0, games).findFirst().getAsInt();
		
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_games_starts().");
			
		}
			
		return games_starts;
		
	}
	
	/**
	 * Funzione che calcola i minuti giocati in modo randomico sulla base delle partite giocate e da quelle giocate da titolare.
	 * @param games
	 * @param games_starts
	 * @return minutes
	 */
	
	public int Random_minutes(int games, int games_starts) {
		
		int minutes = 0;
		
		try {
			
			if(games != 0) {
			
			Random r = new Random();
			int minutes_starts = 0;
			
			int games_not_started = games - games_starts;
			
			if(games_starts != 0) {
				
				minutes_starts = r.ints(45*games_starts, 90*games_starts).findFirst().getAsInt();
				
			} 
			
			int minutes_not_started = r.ints(1*games_not_started, 45*games_not_started).findFirst().getAsInt();
			
			minutes = minutes_starts + minutes_not_started;
			
			}
			
		} catch (Exception e) {
				
			System.out.println("Errore in Generatore_stagioni - Random_minutes().");
			
		}
				
		return minutes;
		
	}
	
	/**
	 * Funzione che genera il numero di goals randomicamente sulla base del numero di partite giocate e il ruolo del calciatore.
	 * @param games
	 * @param ruolo
	 * @return goals
	 */
	
	public int Random_goals(int games, String ruolo) {
		
		int goals = 0;
		
		try {
			
			Random r = new Random();
			
			if(games != 0) {
				
				if(ruolo.equals("DF")&& (int) (3*games*0.08) != 0) {
					
					goals = r.ints(0 , (int) (3*games*0.08)).findFirst().getAsInt();
					
				} else if(ruolo.equals("MF") && (int) (3*games*0.15) != 0) {
					
					goals = r.ints(0 , (int) (3*games*0.15)).findFirst().getAsInt();
					
				} else if(ruolo.equals("FW") && (int) (3*games*0.3) != 0) {
					
					goals = r.ints(0 , (int) (3*games*0.3)).findFirst().getAsInt();
					
				}
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_goals().");
			
		}	
		
		return goals;
		
	}
	
	/**
	 * Funzione che genera il numero di assists randomicamente sulla base delle partite giocate e del ruolo.
	 * @param games
	 * @param ruolo
	 * @return assists
	 */
	
	public int Random_assists(int games, String ruolo) {
		
		int assists = 0;
		
		try {	
		
			Random r = new Random();
			
			if(games != 0) {
				
				if(ruolo.equals("DF") && (int) (3*games*0.05) != 0) {
					
					assists = r.ints(0 , (int) (3*games*0.05)).findFirst().getAsInt();
					
				} else if(ruolo.equals("MF") && (int) (3*games*0.15) != 0) {
					
					assists = r.ints(0 , (int) (3*games*0.15)).findFirst().getAsInt();
					
				} else if(ruolo.equals("FW") && (int) (3*games*0.12) != 0) {
					
					assists = r.ints(0 , (int) (3*games*0.12)).findFirst().getAsInt();
					
				}
				
			}
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_assists().");
			
		}
		
		return assists;
		
	}
	
	/**
	 * Funzione che genera il numero di rigori segnati randomicamente sulla base dei rigori totali tirati.
	 * @param pens_att
	 * @return pens_made
	 */
	
	public int Random_pens_made(int pens_att) {
		
		int pens_made = 0;
		
		try {			
		
			Random r = new Random();
			
			if(pens_att != 0) {
				
				pens_made = r.ints(0 + (int) (pens_att * 0.7), pens_att).findFirst().getAsInt();
				
			}
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_pens_made().");
			
		}
		
		return pens_made;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di rigori tirati sulla base del ruolo e dei goals.
	 * @param goals
	 * @param ruolo
	 * @return pens att
	 */
	
	public int Random_pens_att(int goals, String ruolo) {
		
		int pens_att = 0;
		
		try {
		
			Random r = new Random();
			
			if(goals != 0) {
				
				if(ruolo.equals("DF") && (int) (goals*0.02) != 0) {
	
					pens_att = r.ints(0, (int) (goals*0.02)).findFirst().getAsInt();
					
				} else if(ruolo.equals("MF") && (int) (goals*0.3) != 0) {
	
					pens_att = r.ints(0, (int) (goals*0.3)).findFirst().getAsInt();
					
				} if(ruolo.equals("FW") && (int) (goals*0.5) != 0) {
	
					pens_att = r.ints(0, (int) (goals*0.5)).findFirst().getAsInt();
					
				}
				
			}	
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_pens_att().");
			
		}
		
		return pens_att;
		
	}
	
	/**
	 * Funzione che genera il numero di cartellini gialli randomicamente sulla base delle partite giocate.
	 * @param games
	 * @param ruolo
	 * @return cards_yellow
	 */
	
	public int Random_cards_yellow(int games, String ruolo) {
		
		int cards_yellow = 0;
		
		try {	
			
			Random r = new Random();
			
			if(games != 0) {
				
				if(ruolo.equals("DF") && (int) (games*0.35) != 0) {
					
					cards_yellow = r.ints(0, (int) (games*0.35)).findFirst().getAsInt();
					
				} else if(ruolo.equals("MF") && (int) (games*0.25) != 0) {
					
					cards_yellow = r.ints(0, (int) (games*0.25)).findFirst().getAsInt();
					
				} else if(ruolo.equals("FW") && (int) (games*0.15) != 0) {
					
					cards_yellow = r.ints(0, (int) (games*0.15)).findFirst().getAsInt();
					
				} else if(ruolo.equals("GK") && (int) (games*0.15) != 0) {
					
					cards_yellow = r.ints(0, (int) (games*0.15)).findFirst().getAsInt();
					
				}
				
			}
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_cards_yellow().");
			
		}
		
		return cards_yellow;
		
	}
	
	/**
	 * Funzione che genera il numero di cartellini rossi randomicamente sulla base delle partite giocate.
	 * @param games
	 * @param ruolo
	 * @return cards_red
	 */
	
	public int Random_cards_red(int games, String ruolo) {
		
		int cards_red = 0;
		
		try {
			
			Random r = new Random();
			
			if(games != 0) {
				
				if(ruolo.equals("DF") && (int) (games*0.2) != 0) {
					
					cards_red = r.ints(0, (int) (games*0.2)).findFirst().getAsInt();
					
				} else if(ruolo.equals("MF") && (int) (games*0.15) != 0) {
					
					cards_red = r.ints(0, (int) (games*0.15)).findFirst().getAsInt();
					
				} else if(ruolo.equals("FW") && (int) (games*0.1) != 0) {
					
					cards_red = r.ints(0, (int) (games*0.1)).findFirst().getAsInt();
					
				} else if(ruolo.equals("GK") && (int) (games*0.1) != 0) {
					
					cards_red = r.ints(0, (int) (games*0.1)).findFirst().getAsInt();
					
				}
				
			} 
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_cards_red().");
			
		}		
		
		return cards_red;
		
	}
	
	/**
	 * Funzione che calcola il numero di goals fatti in 90 minuti.
	 * @param minutes
	 * @param goals
	 * @return goals_per90
	 */
	
	public double Goals_per90(int minutes, int goals) {
		
		double goals_per90 = 0.00;
		double goals_per90_ = 0.00;
		
		try {	
		
			if(minutes != 0 && goals != 0) {
				
				double minut = (minutes / 90);
				goals_per90_ = goals / minut;
				
			}
			
			goals_per90 = this.Tronca_double(goals_per90_, "Goals_per90");
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Goals_per90().");
			
		}
		
		return goals_per90;
		
	}
	
	/**
	 * Funzione che calcola il numero di assists fatti in 90 minuti.
	 * @param minutes
	 * @param assists
	 * @return assists_per90
	 */
	
	public double Assists_per90(int minutes, int assists) {
		
		double assists_per90 = 0.00;
		double assists_per90_ = 0.00;
		
		try {
			
			if(minutes != 0 && assists != 0) {
				
				double minut = (minutes / 90);
				assists_per90_ = assists / minut;
				
			}
			
			assists_per90 = this.Tronca_double(assists_per90_, "Assists_per90()");		
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Assists_per90().");
			
		}
		
		return assists_per90;
		
	}
	
	/**
	 * Funzione che calcola il numero di goals - rigori segnati per 90 minuti. 
	 * @param goals
	 * @param minutes
	 * @param pens_made
	 * @return goals_pens_per90
	 */
	
	public double Goals_pens_per90(int goals, int minutes, int pens_made) {
		
		double goals_pens_per90 = 0.00;
		double goals_pens_per90_ = 0.00;
		
		try {		
		
			if(goals != 0) {
				
				int gpp90 = goals - pens_made;
				double minut = minutes / 90;
				goals_pens_per90_ = gpp90 / minut;
				
			}
			
			goals_pens_per90 = this.Tronca_double(goals_pens_per90_, "Goals_pens_per90");	
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Goals_pens_per90().");
			
		}
		
		return goals_pens_per90;
		
	}
	
	/**
	 * Funzione che calcola il numero di goals + assists - rigori segnati per 90 minuti. 
	 * @param goals_assists_per90
	 * @param minutes
	 * @param pens_made
	 * @return goals_assists_pens_per90
	 */
	
	public double Goals_assists_pens_per90(int goals, int assists, int minutes, int pens_made) {
		
		double goals_assists_pens_per90 = 0.00;
		double goals_assists_pens_per90_ = 0.00;
		
		try {	
			
			if(goals != 0 || assists != 0) {
				
				int gpp90 = goals + assists - pens_made;
				double minut = minutes / 90;
				goals_assists_pens_per90_ = gpp90 / minut;
				
			}
			
			goals_assists_pens_per90 = this.Tronca_double(goals_assists_pens_per90_, "Goals_assists_pens_per90");
		
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Goals_assists_pens_per90().");
			
		}
		
		return goals_assists_pens_per90;
		
	}
	
	/**
	 * Funzione che genera randomicamente 'xG'.
	 * @param goals
	 * @return xg
	 */
	
	public double Random_xG(int goals) {
		
		double xg = 0.00;
		double xg_ = 0.00;
		
		try {
			
			Random r = new Random();
			
			int rangeMin = 0;
			int rangeMax =  goals + 1;
			
			if(goals - 5 <= 0) {
				
				rangeMin = 0;
				
			} else {
				
				rangeMin = goals - 5;
				
			}
			
			xg_ = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			
			xg = this.Tronca_double(xg_, "Random_xG");
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_xG().");
			
		}
		
		return xg;
		
	}
	
	/**
	 * Funzione che genera randomicamente 'npxG'.
	 * @param goals
	 * @param pens_made
	 * @param xg
	 * @return npxg
	 */
	
	public double Random_npxG(int goals, int pens_made, double xg) {
		
		double npxg = 0.00;
		double npxg_ = 0.00;
		
		try {
			
			Random r = new Random();
			
			if(pens_made == 0) {
				
				npxg = xg;
				
			} else {
			
				int rangeMin = 0;
				int rangeMax =  goals - pens_made + 1;
				
				if(goals - 5 <= 0) {
					
					rangeMin = 0;
					
				} else {
					
					rangeMin = goals - 5;
					
				}
				
				npxg_ = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				
				npxg = this.Tronca_double(npxg_, "Random_npxG");
			
			}
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_npxG().");
			
		}
				
		return npxg;
		
	}
	
	/**
	 * Funzione che genera randomicamente 'xA'.
	 * @param assists
	 * @return xa
	 */
	
	public double Random_xA(int assists) {
		
		double xa = 0.00;
		double xa_ = 0.00;
		
		try {
			
			Random r = new Random();
			
			int rangeMin = 0;
			int rangeMax =  assists + 1;
			
			if(assists - 5 <= 0) {
				
				rangeMin = 0;
				
			} else {
				
				rangeMin = assists - 5;
				
			}
			
			xa_ = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			
			xa = this.Tronca_double(xa_, "Random_xA");
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_xA().");
			
		}
		
		return xa;
		
	}
	
	/**
	 * Funzione che calcola xG_per90 sulla base di xG e i minuti giocati.
	 * @param minutes
	 * @param xg
	 * @return xg_per90
	 */
	
	public double xG_per90(int minutes, double xg) {
		
		double xg_per90 = 0.00;
		double xg_per90_ = 0.00;
		
		try {
		
			if(minutes != 0 && xg != 0.00) {
				
				double minut = minutes/90;
				
				xg_per90_ = xg / minut;
				
			}
				
			xg_per90 = this.Tronca_double(xg_per90_, "xG_per90");
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - xG_per90().");
			
		}
		
		return xg_per90;
		
	}
	
	/**
	 * Funzione che calcola xA_per90 sulla base di xA e i minuti giocati.
	 * @param minutes
	 * @param xa
	 * @return xa_per90
	 */
	
	public double xA_per90(int minutes, double xa) {
		
		double xa_per90 = 0.00;
		double xa_per90_ = 0.00;
		
		try {	
		
			if(minutes != 0 && xa != 0.00) {
				
				double minut = minutes/90;
				
				xa_per90_ = xa / minut;
				
			}
				
			xa_per90 = this.Tronca_double(xa_per90_, "xA_per90");
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - xA_per90().");
			
		}
		
		return xa_per90;
		
	}
	
	/**
	 * Funzione che calcola npxG_per90 sulla base di npxG e i minuti giocati.
	 * @param minutes
	 * @param npxg
	 * @return 
	 */
	
	public double npxG_per90(int minutes, double npxg) {
		
		double npxg_per90 = 0.00;
		double npxg_per90_ = 0.00;
		
		try {	
		
			if(minutes != 0 && npxg != 0.00) {
				
				double minut = minutes/90;
				
				npxg_per90_ = npxg / minut;
				
			}
				
			npxg_per90 = this.Tronca_double(npxg_per90_, "npxG_per90");
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - npxG_per90().");
			
		}
		
		return npxg_per90;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di gol subiti da un portiere sulla base della posizione in classifica della squadra e i minuti giocati.
	 * @param lg_finish
	 * @return goals_against_gk
	 */
	
	public int Random_goals_against(String lg_finish, int minutes){
		
		int goals_against_gk = 0;
		
		Random r = new Random();
		
		try {
			
			if(minutes < 1800) {
				
				int g = minutes/90;
				
				if(minutes == 0 || g == 0) {
					
					goals_against_gk = 0;
					
				} else {
				
					goals_against_gk = r.ints(0, g).findFirst().getAsInt();
				
				}
				
			} else if(lg_finish.equals("1st")||lg_finish.equals("2nd")||lg_finish.equals("3rd")||lg_finish.equals("4th")||lg_finish.equals("5th")||lg_finish.equals("6th")||lg_finish.equals("7th")||lg_finish.equals("8th")) {
				
				goals_against_gk = r.ints(20, 45).findFirst().getAsInt();
				
			} else if(lg_finish.equals("9th")||lg_finish.equals("10th")||lg_finish.equals("11th")||lg_finish.equals("12th")||lg_finish.equals("13th")||lg_finish.equals("14th")||lg_finish.equals("15th")||lg_finish.equals("16th")||lg_finish.equals("17th")) {
				
				goals_against_gk = r.ints(46, 65).findFirst().getAsInt();
				
			} else {
				
				goals_against_gk = r.ints(66, 110).findFirst().getAsInt();
				
			}
			
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_goals_against().");
			
		}
		
		return goals_against_gk;
		
	}
	
	/**
	 * Funzione che calcola i goal subiti da un portiene nei 90 minuti.
	 * @param minutes
	 * @param goals_against_gk
	 * @return goals_against_gk_per90
	 */
	
	public double Goals_against_per90(int minutes, int goals_against_gk) {
		
		double goals_against_gk_per90 = 0.0;
		
		try {
			
			if(minutes != 0 && goals_against_gk != 0) {
				
				double minut = minutes/90;
				
				double goals_against_gk_per90_ = goals_against_gk/minut;
				
				goals_against_gk_per90 = this.Tronca_double(goals_against_gk_per90_, "Goals_against_per90");
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Goals_against_per90().");
			
		}
		
		return goals_against_gk_per90;
		
	}
	
	/**
	 * Funzione che genera randomicamente i tiri nello specchio subiti dal portiere in base ai goal subiti.
	 * @param goals
	 * @return shot_on_target_against
	 */
	
	public int Shot_on_target_against(int goals_against_gk) {
		
		int shot_on_target_against = 0;
		
		Random r = new Random();
		
		try {
			
			if(goals_against_gk > 0) {
				
				shot_on_target_against = r.ints(goals_against_gk, goals_against_gk + r.ints(30, 60).findFirst().getAsInt()).findFirst().getAsInt();
				
			}
			
		}catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Shot_on_target_against().");
			
		}
		
		return shot_on_target_against;
		
	}
	
	/**
	 * Funzione che calcola la pecentuale di tiri parati sui tiri nello specchio.
	 * @param saves
	 * @param shot_on_target_against
	 * @return save_pct
	 */
	
	public double Save_pct(int saves, int shot_on_target_against) {
		
		double save_pct = 0.0;
		
		try {
			
			if(saves != 0.0 && shot_on_target_against != 0.0) {
				
				save_pct = this.Tronca_double(save_pct/shot_on_target_against, "Save_pct"); 
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Save_pct().");
			
		}
		
		return save_pct;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di vittorie in base alla posizione finale della squadra e le partite iniziate da titolare.
	 * @param lg_finish
	 * @param games_starts_gk
	 * @return wins_gk
	 * 
	 */
	
	public int Random_wins(String lg_finish, int games_starts_gk) {
		
		int wins_gk = 0;
		
		try {
			
			Random r = new Random();
			
			int g = 0;
			
			if(games_starts_gk == 0) {
				
				wins_gk = 0;
				
			} else if((lg_finish.equals("1st")||lg_finish.equals("2nd")||lg_finish.equals("3rd")||lg_finish.equals("4th")||lg_finish.equals("5th")||lg_finish.equals("6th")||lg_finish.equals("7th")||lg_finish.equals("8th"))) {
				
				g = games_starts_gk/2;
				
				wins_gk = r.ints(g, games_starts_gk + 1).findFirst().getAsInt();
				
			} else if(lg_finish.equals("9th")||lg_finish.equals("10th")||lg_finish.equals("11th")||lg_finish.equals("12th")||lg_finish.equals("13th")||lg_finish.equals("14th")||lg_finish.equals("15th")||lg_finish.equals("16th")||lg_finish.equals("17th")) {
				
				if((games_starts_gk/2 - 3) < 0 || games_starts_gk - 5 <= 0) {
					
					wins_gk = r.ints(0, games_starts_gk + 1).findFirst().getAsInt();
					
				} else {
					
					g = (games_starts_gk/2 - 3);
				
					wins_gk = r.ints(g, games_starts_gk - 5).findFirst().getAsInt();
						
				}
				
			} else {
				
				if((games_starts_gk/2 - 5) < 0 || games_starts_gk - 7 <= 0) {
					
					wins_gk = r.ints(0, games_starts_gk + 1).findFirst().getAsInt();
					
				} else {
					
					g = (games_starts_gk/2 - 5);
				
					wins_gk = r.ints(g, games_starts_gk - 7).findFirst().getAsInt();
						
				}
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_wins().");
			
		}
		
		return wins_gk;
		
	}
	
	/**
	 * Funione che calcola le sconfitte a partire dalle partite giocate da titolare, le vittorie e i pareggi.
	 * @param games_start
	 * @param wins
	 * @param draws
	 * @return losses_gk
	 */
	
	public int Losses_gk(int games_starts, int wins_gk, int draws_gk) {
		
		int losses_gk = games_starts - wins_gk - draws_gk ;
		
		return losses_gk;
		
	}
	
	/**
	 * Funzione che genera randomicamente le partite pareggiate sulla base delle partite giocate da titolare e le vittorie.
	 * @param wins_gk
	 * @param games_starts
	 * @return draws
	 */
	
	public int Random_draws(int wins_gk, int games_starts) {
		
		int draws = 0;
		
		try {
			
			Random r = new Random();
			
			if(games_starts != 0) {
				
				int games_rimanenti = games_starts - wins_gk;
				
				draws = r.ints(0, games_rimanenti + 1).findFirst().getAsInt();
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_draws().");
			
		}
		
		return draws;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di partite senza subire goals sulla base del numero di vittorie e pareggi.
	 * @param wins_draws
	 * @return clean_sheets
	 */
	
	public int Random_clean_sheets(int wins_draws) {
		
		int clean_sheets = 0;
		
		try {
			
			Random r = new Random();
			
			if(wins_draws != 0) {
				
				clean_sheets = r.ints(0, wins_draws + 1).findFirst().getAsInt();
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_clean_sheets().");
			
		}
		
		return clean_sheets;
		
	}
	
	/**
	 * Funzione che calcola la percntuale di clean sheets su il numero di partite iniziate.
	 * @param clean_sheets
	 * @param games_starts
	 * @return clean_sheets_pct
	 */
	
	public double Clean_sheets_pct(int clean_sheets, int games_starts) {
		
		double clean_sheets_pct = 0.00;
		double div = 0.00;
		
		try {
			
			if(games_starts != 0 && clean_sheets != 0) {
				
				 div = clean_sheets*100/games_starts;
				
				clean_sheets_pct = this.Tronca_double(div, "Clean_sheets_pct"); 
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Clean_sheets_pct().");
			
		}
		
		return clean_sheets_pct;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di rigori totali sulla base dei gol subiti.
	 * @param goals_against_gk
	 * @return pens_att_gk
	 */
	
	public int Random_pens_att_gk(int goals_against_gk) {
		
		int pens_att_gk = 0;
		
		try {
			
			Random r = new Random();
			
			pens_att_gk = r.ints(0, (int) (goals_against_gk*0.3) + 1).findFirst().getAsInt();
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_pens_att_gk().");
			
		}
		
		return pens_att_gk;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di rigori subiti non parati sulla base dei rigori totali.
	 * @param pens_att_gk
	 * @return pens_allowed
	 */
	
	public int Random_pens_allowed(int pens_att_gk, int goals_against_gk) {
		
		int pens_allowed = 0;
		
		try {
			
			Random r = new Random();
			
			if(goals_against_gk != 0 && pens_att_gk != 0) {
				
				pens_allowed = r.ints(0, pens_att_gk + 1).findFirst().getAsInt();
				
			}			
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_pens_att_gk().");
			
		}
		
		return pens_allowed;
		
	}
	
	/**
	 * Funzione che genera randomicamente il numero di rigori parati in base al numero di rigori totali e subiti.
	 * @param pens_att_gk
	 * @param pens_allowed
	 * @return pens_saved
	 */
	
	public int Random_pens_saved(int pens_att_gk, int pens_allowed) {
		
		int pens_saved = 0;
		
		try {
			
			Random r = new Random();
			
			if(pens_att_gk != 0) {
				
				pens_saved = r.ints(0, pens_att_gk - pens_allowed + 1).findFirst().getAsInt();
				
			} else if(pens_allowed == 0 && pens_att_gk != 0) {
				
				pens_saved = pens_att_gk;
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_pens_saved().");
			
		}
		
		return pens_saved;
		
	}
	
	/**
	 * Funzione che calcola il numero di rigori sbagliati ma non parati.
	 * @param pens_att_gk
	 * @param pens_allowed
	 * @param pens_saved
	 * @return pens_missed_gk
	 */
	
	public int Pens_missed_gk(int pens_att_gk, int pens_allowed, int pens_saved) {
		
		int pens_missed_gk = 0;
		
		try {
			
			if(pens_att_gk != 0) {
				
				pens_missed_gk = pens_att_gk - pens_allowed - pens_saved;
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_pens_missed_gk().");
			
		}
		
		return pens_missed_gk;
		
	}
	
	/**
	 * Funzione che tronca a due cifre decimali i double.
	 * @param d
	 * @return d
	 */
	
	public double Tronca_double(double d, String funz) {
		
		String gg = null;
		
		String g = String.valueOf(d);
		
		try {	
			
			if(!g.equals("Infinity")) {
			
			 g = String.valueOf(d);
			
			if(g.length()>=4) {
				
				gg = g.substring(0, 4); 
			
			} else {
				
				gg = g.substring(0, g.length());
				
			}
			
			d = Double.parseDouble(gg);
			
			} else {
				
				d = 0.0;
				
			}
		
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Tronca_double(" + funz +").");
			
		}
		
		return d;
		
	}
	
	/**
	 * Funzione che seleziona randomicamente una stringa di 'info_squad'.
	 * @return
	 */
	
	public String[] Random_info() {
		
		String[] info = null;
		
		try {
			
			Random r = new Random();
			
			int index = r.nextInt(this.info_squad.size());
			
			info = this.info_squad.get(index).split(",");
			
			
		} catch (Exception e) {
			
			System.out.println("Errore in Generatore_stagioni - Random_info().");
			
		}
		
		return info;				
		
	}

	/**
	 * Funzione che ritorna 'stag_c'.
	 * @return stag_c
	 */
	
	public ArrayList<Stagione_Calciatore> getStag_c() {
		
		return this.stag_c;
		
	}

	/**
	 * Funzione che setta 'stag_c'.
	 * @param stag_c
	 */
	
	public void setStag_c(ArrayList<Stagione_Calciatore> stag_c) {
		
		this.stag_c = stag_c;
		
	}

	/**
	 * @return the info_squad
	 */
	public ArrayList<String> getInfo_squad() {
		return this.info_squad;
	}

	/**
	 * @param info_squad the info_squad to set
	 */
	public void setInfo_squad(ArrayList<String> info_squad) {
		this.info_squad = info_squad;
	}

}

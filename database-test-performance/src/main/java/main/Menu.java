package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import classi.Giocatore;
import classi.Portiere;
import performance.Performance_evaluation;
import random.Generatore;

public class Menu {
	
	Scanner input = null;
	
	int operazione = 0;
	int numero_calciatori = 0;
	String scelta = null;
	
	double ms[] = {0, 0, 0};
	double somma_DB1 = 0;
	double somma_DB2 = 0;
	double somma_DB3 = 0;
	
	int[] random_goals = null;
	int[] random_saves = null;
	String[] random_squad = null;
	
	ArrayList<Giocatore> all_gioc = null;
	ArrayList<Portiere> all_por = null;
	
	/**
	 * Costruttore della classe 'Menu'.
	 */
	
	public Menu() {
		
		this.Home();
		
	}
	
	/**
	 * Funzione che riguarda il menù iniziale.
	 */
	
	public void Home() {
		
		this.setSomma_DB1(0);
		this.setSomma_DB2(0);
		this.setSomma_DB3(0);
		this.setMs(0, 0);
		this.setMs(1, 0);
		this.setMs(2, 0);
		
		System.out.println("\nOPERAZIONI:\n\n");
		System.out.println("1. Inserimento calciatori (Insert).\n");
		System.out.println("2. Aggiunta nuova stagione (Update_new_season).\n");
		System.out.println("3. Aggiornamento campi ultima stagione (Update_last_season).\n");
		System.out.println("4. Ricerca giocatore (Find_player).\n");
		System.out.println("5. Visualizzazione di una statistica dell'ultima stagione di un calciatore (Find_field).\n");
		System.out.println("6. Calcolo media di una statistica di un calciatore (Average).\n");
		System.out.println("7. Cancellazione di un campo dell'ultima stagione (Delete_field).\n");
		System.out.println("8. Cancellazione di un calciatore (Delete_player).\n");
		System.out.println("9. Cancellazione ultima stagione di un calciatore (Delete_season).\n");
		System.out.println("10. Visualizzazione giocatori con una statistica maggiore di un certo valore (Greater_than).\n");
		System.out.println("11. Visualizzazione calciatori che militano in una certa squadra nell'ultima stagione (Find_by_squad).\n");
		System.out.println("12. Visualizzazione statistiche dell'ultima stagione di un calciatore (View_season).\n");
		System.out.println("13. Calcolo somma di una statistica di un calciatore (Sum).\n");
		System.out.println("14. Aggiunta di un campo all'ultima stagione di un calciatore (Add_field).\n");
		System.out.println("0. Esci dall'applicazione. \n");
		
		this.Num_operazione();
		
	}
	
	/**
	 * Funzione che gestisce l'inserimento del numero dell'operazione.
	 */
	
	public void Num_operazione() {
		
		try {
		
			System.out.println("INSERIRE IL NUMERO DELL'OPERAZIONE DA ESEGUIRE: ");
			
			this.setInput();
			this.setOperazione();
			
			if(this.getOperazione() < 0 || this.getOperazione() > 14) {
				
				System.out.println("\nNon hai inserito un numero valido. Inserire una cifra da 0 a 14 corrispondente all'operazione scelta.\n");
				
				this.Num_operazione();
				
			} else {
				
				this.Num_calciatori();
					
				}
		
		} catch (Exception e) {
			
			System.out.println("\nNon hai inserito un numero valido. Inserire una cifra da 0 a 14 corrispondente all'operazione scelta.\n");
			
			this.Num_operazione();
			
		}				
		
	}
	
	/**
	 * Funzione che gestisce la creazione del numero di calciatori da testare.
	 */
	
	public void Num_calciatori() {
		
		try {
			
			if(this.getOperazione() == 0) {
				
				System.out.println("\nAPPLICAZIONE TERMINATA.");
				
				System.exit(1); 	/** Uscita dal programma.*/
				
			} else {
		
				System.out.println("\nQUANTI CALCIATORI VUOI TESTARE? INSERISCI IL NUMERO: ");
				
				this.setInput();
				this.setNumero_calciatori(); 
				
				if(this.numero_calciatori != 0) {
				
					Generatore gen = new Generatore();
					
					gen.Genera_Calciatori(this.numero_calciatori);
					
					this.setAll_gioc(gen.getAll_gioc());
					this.setAll_por(gen.getAll_por());
					
					System.out.println("\nGENERATI " + this.numero_calciatori + " CALCIATORI.\n");
				
					this.Tentativi();
					
					this.Calcolo_Media();
				
				} else {
					
					System.out.println("\nInserisci un numero diverso da 0.");
					
					this.Num_calciatori();
					
				}
			
			}
		
		} catch (Exception e) {
			
			System.out.println("\nNon hai inserito un numero valido.");
			
			this.Num_calciatori();
			
		}
		
	}
	
	/**
	 * Funzione che esegue la media dei tempi delle operazioni.
	 */
	
	public void Tentativi() {
		
		int tentativi = 8;
		int configurazioni = 3;
		
		if(this.operazione == 10) {
			
			this.setRandom_goals(this.Random_goals(this.all_gioc, this.all_por));
			this.setRandom_saves(this.Random_goals(this.all_gioc, this.all_por));
			
		} else if(this.operazione == 11) {
			
			this.setRandom_squad(this.Random_squad(this.all_gioc, this.all_por));
			
		}
		
		for (int i = 0; i < tentativi; i++) {
			
			for (int j = 1; j <= configurazioni; j++) {	
				
				this.Switch(j);
				
			}
			
			if(i>2) {
				
				double[] milliseconds = this.getMs();
				
				this.setSomma_DB1(milliseconds[0]);
				this.setSomma_DB2(milliseconds[1]);
				this.setSomma_DB3(milliseconds[2]);
				
			}
			
		}
		
	}
	
	/**
	 * Funzione che calcola la media dei tempi.
	 */
	
	public void Calcolo_Media() {
		
		double avg1 = this.somma_DB1/5;
		double avg2 = this.somma_DB2/5;
		double avg3 = this.somma_DB3/5;
		
		System.out.println("\nMEDIA TEMPI DATABASE 1: " + avg1 + " ms.");
		System.out.println("MEDIA TEMPI DATABASE 2: " + avg2 + " ms.");
		System.out.println("MEDIA TEMPI DATABASE 3: " + avg3 + " ms.");
		
		this.Ripetizione();
		
	}
	
	/**
	 * Funzione che esegue l'operazione corrispondente in base al numero fornito.
	 */
	
	public void Switch(int config) {
		
		Performance_evaluation pe = new Performance_evaluation();
		
		switch (this.operazione) {
		
		case 1: 	/** Operazione di INSERIMENTO. */
			
			this.setMs(config-1, pe.Insert(this.all_gioc, this.all_por, config));
			
			break;
		case 2:		/** Operazione di UPDATE NUOVA STAGIONE. */
			
			this.setMs(config-1, pe.Update_new_season(this.all_gioc, this.all_por, config));
			
			break;
		case 3:		/** Operazione di UPDATE DELLA VECCHIA STAGIONE */
			
			this.setMs(config-1, pe.Update_last_season(this.all_gioc, this.all_por, config));
	
			break;
		case 4:		/** Operazione di RICERCA GIOCATORI. */
			
			this.setMs(config-1, pe.Find_players(this.all_gioc, this.all_por, config));
	
			break;
		case 5:		/** Operazione di RICERCA DI UNA STATISTICA. */
			
			this.setMs(config-1, pe.Find_field(this.all_gioc, this.all_por, config));
	
			break;
		case 6:		/** Operazione di CALCOLO MEDIA. */
			
			this.setMs(config-1, pe.Average(this.all_gioc, this.all_por, config));
	
			break;
		case 7: 	/** Operazione di CANCELLAZIONE STATISTICA. */
			
			this.setMs(config-1, pe.Delete_field(this.all_gioc, this.all_por, config));
	
			break;
		case 8:		/** Operazione di CANCELLAZIONE CALCIATORE. */
			
			this.setMs(config-1, pe.Delete_players(this.all_gioc, this.all_por, config));
	
			break;
		case 9:		/** Operazione di CANCELLAZIONE STAGIONE. */
			
			this.setMs(config-1, pe.Delete_season(this.all_gioc, this.all_por, config));
	
			break;
		case 10:	/** Operazione di RICERCA CALCIATORE CON STATISTICA PIU' GRANDE DI UN CERTO VALORE. */
					
			this.setMs(config-1, pe.Greater_than(this.all_gioc, this.all_por, config, this.random_goals, this.random_saves));
	
			break;
		case 11:	/** Operazione di RICERCA GIOCATORI PER SQUADRA. */
			
			this.setMs(config-1, pe.Find_by_Squad(this.all_gioc, this.all_por, config, this.random_squad));
	
			break;
		case 12:	/** Operazione di VISUALIZZAZIONE STAGIONE. */
			
			this.setMs(config-1, pe.View_season(this.all_gioc, this.all_por, config));
	
			break;
		case 13:	/** Operazione di SOMMA. */
			
			this.setMs(config-1, pe.Sum(this.all_gioc, this.all_por, config));
	
			break;
		case 14:	/** Operazione di AGGIUNTA STATISTICA. */
			
			this.setMs(config-1, pe.Add_field(this.all_gioc, this.all_por, config));
	
			break;
			
		default:
			break;
		}
		
	}
	
	/**
	 * Funzione che gestisce la chiusura o una nuova esecuzione dell'applicazione.
	 */
	
	public void Ripetizione() {
		
		System.out.println("\nVUOI ESEGUIRE UNA NUOVA OPERAZIONE? [Y/N]");
		
		this.setInput();
		this.setScelta();
		
		if(this.scelta.toUpperCase().equals("Y")) {
			
			this.Home();
			
		} else if(this.scelta.toUpperCase().equals("N")) {
			
			System.out.println("\nAPPLICAZIONE TERMINATA.");
			
			System.exit(1); 	/** Uscita dal programma.*/
			
		} else {
			
			System.out.println("\nInserimento errato. Inserire Y per YES, N per NO.\n");
			
			this.Ripetizione();
			
		}
		
	}
	
	/**
	 * Funzione che genera un array di dimensione della somma delle grandezze di 'all_gioc' e 'all_por' con numeri random che rappresentano i 'goals'.
	 * @param all_gioc
	 * @param all_por
	 * @return random_goals
	 */
	
	public int[] Random_goals(ArrayList<Giocatore> all_gioc, ArrayList<Portiere> all_por) {
		
		int[] random_goals = new int[(all_por.size() + all_gioc.size())];
		
		Random r = new Random();	
		
		for (int i = 0; i < random_goals.length; i++) {
			
			random_goals[i] = r.ints(1, 25).findFirst().getAsInt();
			
		}
		
		return random_goals;
		
	}
	
	/**
	 * Funzione che genera un array di dimensione della somma delle grandezze di 'all_gioc' e 'all_por' con numeri random che rappresentano i 'saves'.
	 * @param all_gioc
	 * @param all_por
	 * @return random_saves
	 */
	
	public int[] Random_saves(ArrayList<Giocatore> all_gioc, ArrayList<Portiere> all_por) {
		
		int[] random_saves = new int[(all_por.size() + all_gioc.size())];
		
		Random r = new Random();	
		
		for (int i = 0; i < random_saves.length; i++) {
			
			random_saves[i] = r.ints(1, 30).findFirst().getAsInt();
			
		}
		
		return random_saves;
		
	}
	
	/**
	 * Funzione che genera un array di dimensione della somma delle grandezze di 'all_gioc' e 'all_por', di squadre random.
	 * @param all_gioc
	 * @param all_por
	 * @return squads
	 */
	
	@SuppressWarnings("resource")
	public String[] Random_squad(ArrayList<Giocatore> all_gioc, ArrayList<Portiere> all_por) {
		
		int size = all_gioc.size() + all_por.size();
		
		String[] squads = new String[size];
		
		ArrayList<String> arr_s = new ArrayList<String>();
		
		try {
			
			BufferedReader reader_info_squad = new BufferedReader(new FileReader("C:\\Users\\andre\\git\\database-test-performace\\Database-test-performance\\database-test-performance\\Info squadre.txt"));
			
			String line_info = reader_info_squad.readLine();
			
			while(line_info != null) {
				
				String[] split = line_info.split(",");
				
				arr_s.add(split[1]);
				
				line_info = reader_info_squad.readLine(); 
				
			}
			
			for (int i = 0; i < squads.length; i++) {
				
				Random r = new Random();
				
				int index = r.ints(0, arr_s.size()).findFirst().getAsInt();
				
				squads[i] = arr_s.get(index);
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Errore in Menu - Random Squad");
			
		}
		
		return squads;
		
	}
	

	/**
	 * @return the input
	 */
	public Scanner getInput() {
		return this.input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput() {
		this.input = new Scanner(System.in);
		
	}

	/**
	 * @return the operazione
	 */
	public int getOperazione() {
		return this.operazione;
	}

	/**
	 * @param operazione the operazione to set
	 */
	public void setOperazione() {
		this.operazione = this.input.nextInt();
		
	}

	/**
	 * @return the numero_calciatori
	 */
	public int getNumero_calciatori() {
		return this.numero_calciatori;
	}

	/**
	 * @param numero_calciatori the numero_calciatori to set
	 */
	public void setNumero_calciatori() {
		this.numero_calciatori = this.input.nextInt();
	}

	/**
	 * @return the all_gioc
	 */
	public ArrayList<Giocatore> getAll_gioc() {
		return this.all_gioc;
	}

	/**
	 * @param all_gioc the all_gioc to set
	 */
	public void setAll_gioc(ArrayList<Giocatore> all_gioc) {
		this.all_gioc = all_gioc;
	}

	/**
	 * @return the all_por
	 */
	public ArrayList<Portiere> getAll_por() {
		return this.all_por;
	}

	/**
	 * @param all_por the all_por to set
	 */
	public void setAll_por(ArrayList<Portiere> all_por) {
		this.all_por = all_por;
	}

	/**
	 * @return the ms
	 */
	public double[] getMs() {
		return this.ms;
	}

	/**
	 * @return the somma_DB1
	 */
	public double getSomma_DB1() {
		return somma_DB1;
	}

	/**
	 * @return the somma_DB2
	 */
	public double getSomma_DB2() {
		return somma_DB2;
	}

	/**
	 * @return the somma_DB3
	 */
	public double getSomma_DB3() {
		return somma_DB3;
	}

	/**
	 * @param ms the ms to set
	 */
	public void setMs(int index, double ms_value) {
		this.ms[index] = ms_value;
	}

	/**
	 * @param somma_DB1 the somma_DB1 to set
	 */
	public void setSomma_DB1(double somma_DB1) {
		this.somma_DB1 = this.somma_DB1 + somma_DB1;
	}

	/**
	 * @param somma_DB2 the somma_DB2 to set
	 */
	public void setSomma_DB2(double somma_DB2) {
		this.somma_DB2 = this.somma_DB2 + somma_DB2;
	}

	/**
	 * @param somma_DB3 the somma_DB3 to set
	 */
	public void setSomma_DB3(double somma_DB3) {
		this.somma_DB3 = this.somma_DB3 + somma_DB3;
	}

	/**
	 * @return the random_goals
	 */
	public int[] getRandom_goals() {
		return this.random_goals;
	}

	/**
	 * @return the random_saves
	 */
	public int[] getRandom_saves() {
		return this.random_saves;
	}

	/**
	 * @param random_goals the random_goals to set
	 */
	public void setRandom_goals(int[] random_goals) {
		this.random_goals = random_goals;
	}

	/**
	 * @param random_saves the random_saves to set
	 */
	public void setRandom_saves(int[] random_saves) {
		this.random_saves = random_saves;
	}

	/**
	 * @return the random_squad
	 */
	public String[] getRandom_squad() {
		return random_squad;
	}

	/**
	 * @param random_squad the random_squad to set
	 */
	public void setRandom_squad(String[] random_squad) {
		this.random_squad = random_squad;
	}

	/**
	 * @return the scelta
	 */
	public String getScelta() {
		return this.scelta;
	}

	/**
	 * @param scelta the scelta to set
	 */
	public void setScelta() {
		this.scelta = this.input.nextLine();
	}

}

package random;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import classi.Giocatore;
import classi.Portiere;
import classi.Stagione_Calciatore;
import mongo_db.Mongo_Import;

public class Generatore_calciatore {
	
	private ArrayList<String> nomi = null;
	private ArrayList<String> cognomi = null;
	private ArrayList<String> nazionalità = null;
	private ArrayList<Giocatore> all_gioc = null;
	private ArrayList<Portiere> all_por = null;
	private ArrayList<String> info_squad = null;
	
	/**
	 * Costruttore della classe 'Generatore_calciatore'.
	 */
	
	public Generatore_calciatore(ArrayList<String> nomi, ArrayList<String> cognomi, ArrayList<String> nazionalità, ArrayList<Giocatore> all_gioc, ArrayList<Portiere> all_por, ArrayList<String> info_squad) {
		
		this.setNomi(nomi);
		this.setCognomi(cognomi);
		this.setNazionalità(nazionalità);
		this.setAll_gioc(all_gioc);
		this.setAll_por(all_por);
		this.setInfo_squad(info_squad);
		
	}
	
	/**
	 * Funzione che genera un calciatore randomicamente.
	 */
	
	public void Crea_Calciatore(int numero_calciatori) {
		
		int size_portieri = 0;
		int size_giocatori = 0;
		
		if(numero_calciatori < 11) {
			
			size_portieri = 1;
			size_giocatori = 10;
			
		} else {
			
			size_portieri = numero_calciatori/11;
			size_giocatori = numero_calciatori - size_portieri;
			
		}
		
		String nome = this.Random_Nome_Calciatore();
		String link = this.Link_Generator(nome);
		String ruolo = this.Random_Ruolo();
		String data = this.Random_Data();
		String nazionalità = this.Random_Nazionalità();
		ArrayList<Stagione_Calciatore> stag_c = this.Random_Stagione(ruolo, data);
		
		if(!ruolo.equals("GK")) {
			
			Giocatore giocatore = new Giocatore(nome, link, stag_c);
			giocatore.setData_nascita(data);
			giocatore.setNazionalita(nazionalità);
			giocatore.setRuolo(ruolo);
			
			if(this.all_gioc.size() < size_giocatori) {
			
				all_gioc.add(giocatore);	
			
			}
			
		}else {
			
			Portiere portiere = new Portiere(nome, link, stag_c);
			portiere.setData_nascita(data);
			portiere.setNazionalita(nazionalità);
			portiere.setRuolo(ruolo);
			
			if(this.all_por.size() < size_portieri) {
				
				all_por.add(portiere);
			
			}
		}
		
	}
	
	/**
	 * Funzione che genera randomicamente un il nome di un calciatore (nome + cognome);
	 * @return nome completo del calciatore
	 */
	
	public String Random_Nome_Calciatore() {
		
		Random r = new Random();
		
		int index_nomi = r.nextInt(this.nomi.size());
		int index_cognomi = r.nextInt(this.cognomi.size());
		
		String nome = this.nomi.get(index_nomi);
		String cognome = this.cognomi.get(index_cognomi);
		
		return nome + " " + cognome;
		
	}
	
	/**
	 * Funzione che genera un data di nascita randomica.
	 * @return data di nascita
	 */
	
	public String Random_Data() {
		
		Random r = new Random();
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		int index = r.nextInt(months.length);
		
		int day_ = 0;
		
		switch(index) {
		
			case 0:
				day_ = r.ints(1, 31).findFirst().getAsInt();
				break;
				
			case 1:
				day_ = r.ints(1, 28).findFirst().getAsInt();
				break;
				
			case 2:
				day_ = r.ints(1, 31).findFirst().getAsInt();
				break;
				
			case 3:
				day_ = r.ints(1, 30).findFirst().getAsInt();
				break;
		
			case 4:
				day_ = r.ints(1, 31).findFirst().getAsInt();
				break;
		
			case 5:
				day_ = r.ints(1, 30).findFirst().getAsInt();
				break;
		
			case 6:
				day_ = r.ints(1, 31).findFirst().getAsInt();
				break;
				
			case 7:
				day_ = r.ints(1, 31).findFirst().getAsInt();
				break;
				
			case 8:
				day_ = r.ints(1, 30).findFirst().getAsInt();
				break;
			
			case 9:
				day_ = r.ints(1, 31).findFirst().getAsInt();
				break;
		
			case 10:
				day_ = r.ints(1, 30).findFirst().getAsInt();
				break;
		
			case 11:
				day_ = r.ints(1, 31).findFirst().getAsInt();;
				break;
		
		}
		
		int year_ = r.ints(1975, 2002).findFirst().getAsInt();
		
		String year = Integer.toString(year_);
		String month = months[index]; 
		String day = Integer.toString(day_);
		
		return month + " " + day + "," + " " + year;
		
	}
	
	/**
	 * Funzione che genera una nazionalità randomica.
	 * @return la nazionalità
	 */
	
	public String Random_Nazionalità() {
		
		Random r = new Random();
		
		int index = r.nextInt(this.nazionalità.size());
		
		return nazionalità.get(index);
		
	}
	
	/**
	 * Funzione che genera, a partire dal nome, il link del calciatore.
	 * @param nome
	 * @return link calciatore.
	 */
	
	public String Link_Generator(String nome) {
		
		String sito = "https://fbref.com/en/players/";
		String nome_link = nome.replace(" ", "-");	
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghilmnopqrstuvzjkxyw";
		String link = null;

		int count = 8; //Lunghezza codice alfanumerico

		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < 10000; i++) {
			
			while (count-- != 0) {
	
				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	
			}
	
			String code = builder.toString(); //Codice alfanumerico
			
			link = sito + code + "/" + nome_link;
			
			if(!this.Check_Link(link)) {
				
				break;
				
			}
		
		}
		
		return link ;	
		
	}
	
	/**
	 * Funzione che ritorna il ruolo randomico.
	 * @return il ruolo
	 */
	
	public String Random_Ruolo() {
		
		Random r = new Random();
		
		String[] ruoli = {"GK", "DF", "MF", "FW"};
		
		int index = r.nextInt(ruoli.length);
		
		return ruoli[index];
		
	}
	
	/**
	 * Funzione che genera randomicamente le stagioni.
	 * @param eta
	 * @param ruolo
	 * @return stag_c
	 */
	
	public ArrayList<Stagione_Calciatore> Random_Stagione(String ruolo, String data_nascita) {
		
		ArrayList<Stagione_Calciatore> stag_c = new ArrayList<Stagione_Calciatore>();
		Generatore_stagioni gs = new Generatore_stagioni(stag_c, this.info_squad);
		
		int numero_stagioni = this.Random_numero_stagioni(data_nascita);
		
		LocalDate now = LocalDate.now();
		int year_now = now.getYear();
		int year_start = year_now - numero_stagioni;
		
		String[] a = data_nascita.split(",");
		int anno_nascita = Integer.parseInt(a[1].trim());
		
		for (int i = 0; i < numero_stagioni; i++) {
			
			int y_a = year_start + i;
			int age = y_a + 1 - anno_nascita;
			
			String season_a = String.valueOf(y_a);
			String season_b = String.valueOf(y_a + 1);
			String season = season_a + "-" + season_b;
			
			gs.Random_stagione_giocatore(ruolo, season, age);
			
		}
		
		return stag_c;
		
	}
	
	/**
	 * Funzione che calcola il numero di stagioni randomicamente in base alla data di nascita.
	 * @param data_nascita
	 * @return numero_stagioni.
	 */
	
	public int Random_numero_stagioni(String data_nascita) {
		
		int numero_stagioni = 0;
		
		try {
				
			LocalDate now = LocalDate.now();
			
			Random r = new Random();
			
			String[] data = data_nascita.split(",");
			String anno = data[1].trim();
			
			int year = Integer.parseInt(anno);
			int year_18 = year + 18;
			
			int eta = now.getYear() - year;
			
			if(eta>=18&&eta<=22) {
				
				if(now.getYear() - year_18 <= 1) {
					
					numero_stagioni = 1;
					
				} else {
				
					numero_stagioni = r.ints(1, now.getYear() - year_18).findFirst().getAsInt();
				
				}
					
			} else if(eta>=23&&eta<=30) {
				
				if(now.getYear() - year_18 <= 4) {
				
					numero_stagioni = 4;
				
				} else {
					
					numero_stagioni = r.ints(4, now.getYear() - year_18).findFirst().getAsInt();
					
				}
				
			} else {
				
				if(now.getYear() - year_18 <= 7) {
			
				numero_stagioni = 7;
				
				} else {
					
					numero_stagioni = r.ints(7, now.getYear() - year_18).findFirst().getAsInt();
					
				}
			
			}
			
		} catch (Exception e) {
			System.out.println("Errore in Generatore_calciatore - Random_numero_stagioni");
		}
		
		return numero_stagioni;
		
	}
	
	/**
	 * Funzione che controlla se esiste un calciatore con lo stesso link.
	 * @param link
	 * @return flag
	 */
	
	public boolean Check_Link(String link) {
		
		Mongo_Import mongo_import = new Mongo_Import();
		boolean flag = false;
		
		for(Giocatore gioc : this.all_gioc) {

			if(gioc.getLink_calciatore().equals(link)) {
				
				flag = true;
				
			}
			
		}
		
		for(Portiere por : this.all_por) {
			
			if(por.getLink_calciatore().equals(link)) {
				
				flag = true;
				
			}
			
		}
		
		if(mongo_import.Check_Link(link)) {
			
			flag = true;
			
		}
		
		return flag;
		
	}

	/**
	 * Funzione che ritorna 'nomi'.
	 * @return nomi
	 */
	
	public ArrayList<String> getNomi() {
		
		return this.nomi;
		
	}

	/**
	 * Funzione che setta 'nomi', inizializzando un nuovo arraylist.
	 */
	
	public void setNomi(ArrayList<String> nomi) {
		
		this.nomi = nomi;
		
	}

	/**
	 * Funzione che ritorna 'cognomi'.
	 * @return cognomi
	 */
	
	public ArrayList<String> getCognomi() {
		
		return this.cognomi;
		
	}

	/**
	 * Funzione che setta 'cognomi', inizializzando un nuovo arraylist.
	 */
	public void setCognomi(ArrayList<String> cognomi) {
		
		this.cognomi = cognomi;
		
	}

	/**
	 * Funzione che ritorna 'nazionalità'.
	 * @return nazionalità
	 */
	public ArrayList<String> getNazionalità() {
		
		return this.nazionalità;
		
	}

	/**
	 * Funzione che setta 'nazionalità', inizializzando un nuovo arraylist.
	 */
	
	public void setNazionalità(ArrayList<String> nazionalità) {
		
		this.nazionalità = nazionalità;
		
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

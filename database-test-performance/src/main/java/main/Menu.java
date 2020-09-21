package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import classi.Giocatore;
import classi.Portiere;

public class Menu {
	
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

}

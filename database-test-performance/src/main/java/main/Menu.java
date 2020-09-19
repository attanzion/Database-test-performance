package main;

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

}

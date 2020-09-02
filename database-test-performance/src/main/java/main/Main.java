package main;

import java.util.ArrayList;

import org.bson.Document;

import classi.Giocatore;
import classi.Portiere;
import mongo_db.Mongo_Export;
import mongo_db.Mongo_Export_2;
import mongo_db.Mongo_Export_3;
import performance.Performance_evaluation;



public class Main {

	public static void main(String[] args) {

		Performance_evaluation pe = new Performance_evaluation();
		
		pe.Update_new_season(1000);

		
	}

}

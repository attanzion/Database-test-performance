package main;

import org.bson.Document;

import mongo_db.Mongo_Export;
import mongo_db.Mongo_Export_2;
import performance.Performance_evaluation;


public class Main {

	public static void main(String[] args) {

//		Performance_evaluation pe = new Performance_evaluation();
//		
//		pe.Inserimento(1);
		
		Document d = new Document();
		d.put("Prova", 10);
		
		Mongo_Export_2 me = new Mongo_Export_2();
		
		me.Insert_new_season("", "https://fbref.com/en/players/NHFlV9RM/Gemino-Dunford", d);
		
	}

}

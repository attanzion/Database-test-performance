package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.bson.Document;


import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import classi.Giocatore;
import classi.Portiere;
import mongo_db.Mongo;
import mongo_db.DB1;
import mongo_db.DB2;
import mongo_db.DB3;
import performance.Performance_evaluation;
import random.Generatore;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.*;


public class Main {

	@SuppressWarnings("null")
	public static void main(String[] args) throws InterruptedException {

		

		Generatore gen = new Generatore();
		Performance_evaluation pe = new Performance_evaluation();
		Mongo mongo = new Mongo();
		
		gen.Genera_Calciatori(3000);
		
		ArrayList<Giocatore> all_gioc = gen.getAll_gioc();
		ArrayList<Portiere> all_por = gen.getAll_por();
		
		mongo.Drop_database("FootballStats");
		mongo.Drop_database("FootballStats_2");
		mongo.Drop_database("FootballStats_3");
		
	try {	
		
		FileWriter myWriter = new FileWriter("TEMPI.txt");
		long[] ms = {0,0,0,0};
			
		for(int j= 0 ; j < 10; j++) {
			
			for (int i = 0; i <= 3; i++) {	
			
			ms[i] = pe.Insert(all_gioc, all_por, i);		
				
		}
			mongo.Drop_database("FootballStats_null");	
	
						
		for (int i = 1; i < ms.length; i++) {
			
				myWriter.write("Time " + (i) + ": " + ms[i] + " ms\n");		
			
		}	
		
		myWriter.write("\n");
		
		}
		myWriter.close();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		

 
	
}

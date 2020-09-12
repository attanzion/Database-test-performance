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
		
		gen.Genera_Calciatori(1000);
		
		ArrayList<Giocatore> all_gioc = gen.getAll_gioc();
		ArrayList<Portiere> all_por = gen.getAll_por();
		
		mongo.Drop_database("FootballStats");
		mongo.Drop_database("FootballStats_2");
		mongo.Drop_database("FootballStats_3");
		
	try {	
		
		FileWriter myWriter = new FileWriter("TEMPI.txt");
		long[] ms = {0,0,0};
		long somma_1 = 0;
		long somma_2 = 0;
		long somma_3 = 0;
		int volte = 5;
			
		for(int j= 0 ; j < volte; j++) {
			
			for (int i = 0; i <= 2; i++) {	
			
			ms[i] = pe.Update_last_season(all_gioc, all_por, i+1);		
				
		}	
						
		for (int i = 0; i < ms.length; i++) {
			
				myWriter.write("Time " + (i) + ": " + ms[i] + " ms\n");					
			
		}	
		
		somma_1 = somma_1+ ms[0];
		somma_2 = somma_2+ ms[1];
		somma_3 = somma_3+ ms[2];
		
		myWriter.write("\n");
		
		}
		myWriter.close();
		
		System.out.println("\nAVG 1: " + somma_1/(volte-1) + " ms");
		System.out.println("AVG 2: " + somma_2/(volte-1) + " ms");
		System.out.println("AVG 3: " + somma_3/(volte-1) + " ms");
		
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
		
		
		

 
	


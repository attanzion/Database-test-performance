package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;

import casmi.graphics.object.Projection;
import classi.Giocatore;
import classi.Portiere;
import mongo_db.Mongo;
import mongo_db.Mongo_Export_2;
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
import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;


public class Main {

	public static void main(String[] args) throws InterruptedException {

		

		Generatore gen = new Generatore();
		Performance_evaluation pe = new Performance_evaluation();
		Mongo mongo = new Mongo();
		
		gen.Genera_Calciatori(500);
		
		ArrayList<Giocatore> all_gioc = gen.getAll_gioc();
		ArrayList<Portiere> all_por = gen.getAll_por();
		
		mongo.Drop_database("FootballStats");
		mongo.Drop_database("FootballStats_2");
		mongo.Drop_database("FootballStats_3");
		
	try {	
		
		FileWriter myWriter = new FileWriter("TEMPI.txt");
		double[] ms = {0,0,0};
		double somma_1 = 0;
		double somma_2 = 0;
		double somma_3 = 0;
		int volte = 15;
			
		for(int j= 0 ; j < volte; j++) {
			
			for (int i = 0; i <= 2; i++) {	
			
			ms[i] = pe.Delete_players(all_gioc, all_por, i+1);		
				
		}	
						
		for (int i = 0; i < ms.length; i++) {
			
				myWriter.write("Time " + (i) + ": " + ms[i] + " ms\n");					
			
		}	
		
		if(j>2) {
			
			somma_1 = somma_1+ ms[0];
			somma_2 = somma_2+ ms[1];
			somma_3 = somma_3+ ms[2];
		
		}
		
		myWriter.write("\n");
		
		}
		myWriter.close();
		
		System.out.println("\nAVG 1: " + somma_1/(volte-3) + " ms");
		System.out.println("AVG 2: " + somma_2/(volte-3) + " ms");
		System.out.println("AVG 3: " + somma_3/(volte-3) + " ms");
		
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
		
		
		

 
	


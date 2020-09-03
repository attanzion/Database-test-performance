package main;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import classi.Giocatore;
import classi.Portiere;
import mongo_db.Mongo;
import mongo_db.Mongo_Export;
import mongo_db.Mongo_Export_2;
import mongo_db.Mongo_Export_3;
import performance.Performance_evaluation;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.*;


public class Main {

	public static void main(String[] args) {

		Performance_evaluation pe = new Performance_evaluation();
		
		pe.Update_last_season(1000);
		
	}

}

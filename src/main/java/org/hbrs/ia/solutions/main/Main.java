package org.hbrs.ia.solutions.main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.hbrs.ia.solutions.model.control.ManagePersonalImpl;
import org.bson.Document;

public class Main {

    private final static String DB_NAME = "highperformanceExe1";

    private MongoClient client;
    private MongoDatabase supermongo;
    private MongoCollection<Document> salesmenCollection;

    public ManagePersonalImpl getManagePersonal() {
        return managePersonal;
    }

    private  ManagePersonalImpl managePersonal;

    public MongoCollection<Document> getSalesmen() {
        return salesmenCollection;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initDB();
    }

    public void initDB() {
        // Setting up the connection to a local MongoDB with standard port 27017
        // must be started within a terminal with command 'mongod'.
        client = new MongoClient("localhost", 27017);

        // Get database 'highperformance' (creates one if not available)
        supermongo = client.getDatabase(Main.DB_NAME);

        // Get Collection 'salesmen' (creates one if not available)
        salesmenCollection = supermongo.getCollection("salesmenTest");

        this.managePersonal = new ManagePersonalImpl();
        managePersonal.setCollection( salesmenCollection );
    }

    public void closeConection() {
        client.close();
    }

}

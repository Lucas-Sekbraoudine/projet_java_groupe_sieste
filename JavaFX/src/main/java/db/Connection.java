package db;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoSocketException;

public class Connection {
    private MongoClient mongoClient = null;

    public MongoClient getMongoClient(){
        return mongoClient;
    }
    public MongoClient initConnection() {
        try{
            MongoClientURI uri = new MongoClientURI("mongodb://root:root@localhost:27017/?authSource=admin");
            mongoClient = new MongoClient(uri);
            System.out.println("Je suis connecté");
        }catch (MongoSocketException e){
         System.err.println("Erreur de connexion" + e);
        }
        return mongoClient;
    }
}
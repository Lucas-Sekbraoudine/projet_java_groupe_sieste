package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDbProvider {

    private MongoClient mongoClient = null;
    public MongoClient getMongoClient() {
        return mongoClient;
    }
    public void initConnection() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://root:root@mongo:27017/");
            mongoClient = new MongoClient(uri);
            System.out.println("La connexion à été établie !");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
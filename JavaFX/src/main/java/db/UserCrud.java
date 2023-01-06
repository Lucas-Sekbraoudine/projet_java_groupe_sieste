package db;

import beans.User;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class UserCrud {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<User> collection;

    public void init(MongoDbProvider mongoDbProvider){

        mongoClient = mongoDbProvider.getMongoClient();

        mongoDatabase = mongoClient.getDatabase("JavaFX");
        collection = mongoDatabase.getCollection("User", User.class);
    }

    public void insertOne(User user){
        collection.insertOne(user);

    }
}

package Models;


import beans.User;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import db.Connection;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;



public class UserModel {

    Connection connection = new Connection();
    private MongoCollection<User> userCollection;

    public void init(){
        MongoClient mongoClient = connection.initConnection();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase database = mongoClient.getDatabase("JavaFX").withCodecRegistry(pojoCodecRegistry);
        userCollection = database.getCollection("user", User.class);
        // On créer une contrainte sur le userName pour qu'il soit unique
        userCollection.createIndex(new BasicDBObject("userName", 1), new IndexOptions().unique(true));

    }

    public void createUser(User user){

        userCollection.insertOne(user);
    }
    public List<User> readAllUser(){
        List<User> userList = new ArrayList<>();
        userCollection.find().iterator().forEachRemaining(System.out::println);
        return  userList;
    }

    public void registerUser(String userName, String passWord){
        BasicDBObject userNameId = new BasicDBObject();
        userNameId.put("userName", userName);
        FindIterable<User> cursor = userCollection.find(userNameId);
        cursor.iterator().forEachRemaining((name) -> System.out.println(name.getFirstName()));

    }

}

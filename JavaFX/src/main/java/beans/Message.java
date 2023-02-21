package beans;

import org.bson.types.ObjectId;

public class Message {
    private ObjectId id;
    private String message;
    private User authorMessage;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthorMessage() {
        return authorMessage;
    }

    public void setAuthorMessage(User authorMessage) {
        this.authorMessage = authorMessage;
    }


    public Message(String message, User authorMessage){
        this.message = message;
        this.authorMessage = authorMessage;
    }

    public Message(){

    }
}

package beans;

import org.bson.types.ObjectId;

public class Conversation {
    private ObjectId id;
    private User hostUser;
    private User guestUser;
    private Message message;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getHostUser() {
        return hostUser;
    }

    public void setHostUser(User hostUser) {
        this.hostUser = hostUser;
    }

    public User getGuestUser() {
        return guestUser;
    }

    public void setGuestUser(User guestUser) {
        this.guestUser = guestUser;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Conversation(User hostUser, User guestUser, Message message){
        this.hostUser = hostUser;
        this.guestUser = guestUser;
        this.message = message;
    }

    public Conversation(){

    }
}

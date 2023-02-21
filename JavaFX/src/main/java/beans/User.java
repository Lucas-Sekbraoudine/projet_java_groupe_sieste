package beans;

import org.bson.types.ObjectId;


public class User {
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String saltPassWord;

    private Boolean isConnected = false;

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String userName, String passWord, Boolean isConnected) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
        this.isConnected = isConnected;
    }
    public User(){

    }

    public String getSaltPassWord() {
        return saltPassWord;
    }

    public void setSaltPassWord(String saltPassWord) {
        this.saltPassWord = saltPassWord;
    }
}

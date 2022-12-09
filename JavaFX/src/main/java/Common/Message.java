package Common;

import java.io.Serializable;

public class Message implements Serializable{
	private String sender,content;
	public static final long serialVersionUID = 1L;
	
	public Message(String sender, String content){
		this.sender = sender;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return sender + " : " + content;
	}

	public String getContent() {
		return content;
	}

	public void setSender(String sender) {
		this.sender = sender;
		
	}
}

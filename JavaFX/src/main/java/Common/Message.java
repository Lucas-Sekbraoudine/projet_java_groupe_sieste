package Common;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = -6835194907138499736L;
	private String mess;
	private String sender;

	public Message(String sender, String mess) {
		this.sender = sender;
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getSender() {
		return sender;
	}

	public void setSENDER(String sender) {
		this.sender = sender;
	}

	public String toString() {
		return "Sender : " + sender + " Message : " + mess;
	}

}
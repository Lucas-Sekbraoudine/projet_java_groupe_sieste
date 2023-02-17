package Common;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = -6835194907138499736L;
	private String mess;
	private String action;

	public Message(String action, String mess) {
		this.action = action;
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String toString() {
		return "Action : " + action + " Message : " + mess;
	}

}

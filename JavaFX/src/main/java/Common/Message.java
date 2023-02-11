import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = -6835194907138499736L;
	private String sender;
	private String action;
	private Object data;

	public Message(String action, Object data) {
		this.action = action;
		this.data = data;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

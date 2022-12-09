package Client;

import java.io.IOException;

import Common.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ClientPanel extends Parent {

	private TextArea textToSend;
	private ScrollPane scrollReceivedText;
	private TextFlow receivedText;
	private Button clearBtn;
	private Button sendBtn;
	private Client client;
	
	public void setClient(Client client) {
		this.client = client;
	}

	public ClientPanel() {
		textToSend = new TextArea();
		scrollReceivedText = new ScrollPane();
		receivedText = new TextFlow();
		clearBtn = new Button();
		sendBtn = new Button();
		
		receivedText.setLayoutX(0);
		receivedText.setLayoutY(0);
		receivedText.setPrefWidth(400);
		receivedText.setPrefHeight(350);
		receivedText.setVisible(true);
		
		scrollReceivedText.setLayoutX(50);
		scrollReceivedText.setLayoutY(50);
		scrollReceivedText.setPrefWidth(400);
		scrollReceivedText.setPrefHeight(350);
		scrollReceivedText.setContent(receivedText);
		scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
		
		textToSend.setLayoutX(50);
		textToSend.setLayoutY(400);
		textToSend.setPrefHeight(50);
		textToSend.setPrefWidth(350);
		
		sendBtn.setLayoutX(400);
		sendBtn.setLayoutY(400);
		sendBtn.setPrefHeight(25);
		sendBtn.setPrefWidth(50);
		sendBtn.setVisible(true);
		sendBtn.setText("Send");
		sendBtn.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 Message message = new Message("moi", textToSend.getText());
				 printNewMessage(message);
				 textToSend.setText("");
				 try {
					client.sendMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			 }
		});

		
		clearBtn.setLayoutX(400);
		clearBtn.setLayoutY(425);
		clearBtn.setPrefHeight(25);
		clearBtn.setPrefWidth(50);
		clearBtn.setVisible(true);
		clearBtn.setText("Clear");
		clearBtn.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 textToSend.setText("");
			 }
		});

		
		
		
		this.getChildren().add(scrollReceivedText);
		this.getChildren().add(textToSend);
		this.getChildren().add(clearBtn);
		this.getChildren().add(sendBtn); 
	}
	
	public void printNewMessage(Message mess) {
		Platform.runLater(new Runnable() {
			@Override
		 	public void run() {
				Label text = new Label("\n" + mess.toString());
			 	text.setPrefWidth(receivedText.getPrefWidth() - 20);
			 	text.setAlignment(Pos.CENTER_LEFT);
			 	receivedText.getChildren().add(text);
			}
		});
	}
	
}
